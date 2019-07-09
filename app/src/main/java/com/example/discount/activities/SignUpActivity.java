package com.example.discount.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.discount.R;
import com.example.discount.utils.Constants;

public class SignUpActivity extends Activity implements View.OnClickListener{

    private TextView tv_login;
    private EditText edt_email, edt_fullname, edt_password, edt_password_again;
    private ImageView img_password_show_hide, img_password_again_show_hide;
    private Button btn_Register;
    Boolean bPasswordShow, bPasswordAgainShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //init EditText

        edt_email = findViewById(R.id.edt_email);
        edt_fullname = findViewById(R.id.edt_fullname);
        edt_password = findViewById(R.id.edt_password);
        edt_password_again= findViewById(R.id.edt_password_again);


        // set the status bar and navigation bar color as ColorLightPink
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorBlue));
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.colorBlue));

        // set login hyperlink
        tv_login = findViewById(R.id.tv_login);
        SpannableString ss = new SpannableString(getString(R.string.sign_up_control));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan, 18, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv_login.setText(ss);
        tv_login.setMovementMethod(LinkMovementMethod.getInstance());
        tv_login.setHighlightColor(Color.TRANSPARENT);

        // password show/hide
        img_password_show_hide = findViewById(R.id.img_password_show_hide);
        bPasswordShow = false;
        img_password_show_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bPasswordShow == false) { // show password
                    bPasswordShow = true;
                    img_password_show_hide.setImageDrawable(getResources().getDrawable(R.drawable.password_hide));
                    edt_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else { // hide password
                    bPasswordShow = false;
                    img_password_show_hide.setImageDrawable(getResources().getDrawable(R.drawable.password_show));
                    edt_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        // Password Again show/hide

        img_password_again_show_hide = findViewById(R.id.img_password_again_show_hide);
        bPasswordAgainShow = false;
        img_password_again_show_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bPasswordAgainShow == false) { // show password
                    bPasswordAgainShow = true;
                    img_password_again_show_hide.setImageDrawable(getResources().getDrawable(R.drawable.password_hide));
                    edt_password_again.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else { // hide password
                    bPasswordAgainShow = false;
                    img_password_again_show_hide.setImageDrawable(getResources().getDrawable(R.drawable.password_show));
                    edt_password_again.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        // Register Button clicked
        btn_Register = findViewById(R.id.btn_Register);
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = edt_password.getText().toString();
                String password_again = edt_password_again.getText().toString();
                String email = edt_email.getText().toString();

                // password don't match
                if (!password.equals(password_again)) {
                    Constants.showAlertDialog(SignUpActivity.this, "Warning", "The two passwords don't match");
                    return;
                }

                // password length is lower 6
                if (password.length() < 6) {
                    Constants.showAlertDialog(SignUpActivity.this, "Warning", "The password must be at least 6 characters long");
                    return;
                }

                // email already exits

                // with only ending @ibs-b.hu

                if (!email.contains("@ibs-b.hu")) {
                    Constants.showAlertDialog(SignUpActivity.this,"Warning", "You can only register with your email address ending @ibs-b.hu");
                    return;
                }

                // do register
                startActivity(new Intent(SignUpActivity.this, PoliciesActivity.class));
            }
        });
    }

    @Override
    public void onClick(View v) {

    }



}
