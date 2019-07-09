package com.example.discount.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.discount.R;

public class PoliciesActivity extends Activity {

    private TextView tv_terms, tv_privacy, tv_policy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policies);

        // set the status bar and navigation bar color as ColorLightPink
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorDarkBlue));
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.colorBlue));

        tv_terms = findViewById(R.id.tv_terms);
        tv_privacy = findViewById(R.id.tv_privacy);
        tv_policy = findViewById(R.id.tv_policy);

        tv_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_terms.setBackground(getResources().getDrawable(R.drawable.bkg_selected_terms));
                tv_terms.setTextColor(getResources().getColor(R.color.colorWhite));
                tv_privacy.setBackground(getResources().getDrawable(R.drawable.bkg_unselected_privacy));
                tv_privacy.setTextColor(getResources().getColor(R.color.colorBlue));

                tv_policy.setText("term clicked");
            }
        });

        tv_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_privacy.setBackground(getResources().getDrawable(R.drawable.bkg_selected_privacy));
                tv_privacy.setTextColor(getResources().getColor(R.color.colorWhite));
                tv_terms.setBackground(getResources().getDrawable(R.drawable.bkg_unselected_terms));
                tv_terms.setTextColor(getResources().getColor(R.color.colorBlue));
                tv_policy.setText("privacy clicked");
            }
        });
    }
}

