package com.example.discount.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Constants {
    static public void showAlertDialog(Context context, String title, String msg) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE://Yes button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(msg).setPositiveButton("OK", dialogClickListener);
        AlertDialog alert = builder.create();
        alert.show();
    }
}
