package com.example.discount.activities;

import android.app.Activity;
import android.app.usage.ConfigurationStats;
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

public class LoginActivity extends Activity {

    private TextView tv_login_control, tv_forgot_password;
    private ImageView img_password_show_hide;
    private EditText edt_email, edt_password;
    private Button btn_login;
    Boolean bPasswordShow = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // init components
        img_password_show_hide = findViewById(R.id.img_password_show_hide);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_Login);

        // set the status bar and navigation bar color as ColorLightPink
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorBlue));
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.colorBlue));

        // set sign up hyperlink
        tv_login_control = findViewById(R.id.tv_login_control);
        SpannableString ss = new SpannableString(getString(R.string.login_control));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan, 18, 26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv_login_control.setText(ss);
        tv_login_control.setMovementMethod(LinkMovementMethod.getInstance());
        tv_login_control.setHighlightColor(Color.TRANSPARENT);

        // set forgot password hyperlink
        tv_forgot_password = findViewById(R.id.tv_Forgot_password);
        tv_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });

        // show and hide password
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

        // Login Button Clicked
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_email.getText().toString();
                String password = edt_password.getText().toString();
                // email not our system
                if (!email.contains("@ibs-b.hu"))
                {
                    Constants.showAlertDialog(LoginActivity.this, "Warning", "This email is not in our system. Try to register it");
                    return;
                }

                // email or password incorrect
                if (!password.equals("123")) {
                    Constants.showAlertDialog(LoginActivity.this, "Warning",
                            "Either the password or the email address is incorrect");
                    return;
                }

                startActivity(new Intent(LoginActivity.this, PoliciesActivity.class));
            }
        });
    }
}
