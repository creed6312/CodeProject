package com.example.shanesardinha.codeproject.Utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.shanesardinha.codeproject.R;

/**
 * Created by shanesardinha on 2016/08/10.
 */
public class DialogHelper {

    public static void createDialog(Context context, String message, DialogInterface.OnClickListener onPositiveClick, DialogInterface.OnClickListener onNegativeClick)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setMessage(message);
        dialogBuilder.setCancelable(true);
        dialogBuilder.setPositiveButton(R.string.retry,onPositiveClick);
        dialogBuilder.setNegativeButton(R.string.cancel, onNegativeClick);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
}
