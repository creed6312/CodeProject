package com.example.shanesardinha.codeproject.Utility;

import android.text.Html;

/**
 * Created by shanesardinha on 2016/08/10.
 */
public class Utility {

    public static String getHtml(String html)
    {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
            return Html.fromHtml(html,Html.FROM_HTML_MODE_LEGACY).toString();
         else
             return Html.fromHtml(html).toString();
    }
}
