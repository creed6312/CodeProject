package com.example.shanesardinha.codeproject.Utility;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by shanesardinha on 2016/08/10.
 */
public class ProgressUtility {
    private static int progressCount ;
    private static ProgressDialog progressDialog;

    public static void createProgressDialog(Context context, String message)
    {
        if (progressDialog == null)
        {
            progressCount = 0 ;
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(message);
            progressDialog.setCancelable(false);
        }
    }

    public static void progressDone()
    {
        progressCount--;
        if (progressCount <= 0)
            hideProgress();
    }

    public static void showProgress()
    {
        if (progressDialog != null)
        {
            progressCount ++;
            progressDialog.show();
        }
    }

    private static void hideProgress()
    {
        if (progressDialog != null)
        {
            progressDialog.cancel();
            progressDialog = null ;
        }
    }
}
