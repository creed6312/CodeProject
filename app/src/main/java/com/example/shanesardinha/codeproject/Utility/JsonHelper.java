package com.example.shanesardinha.codeproject.Utility;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.shanesardinha.codeproject.Interfaces.IWebRequest;

import org.json.JSONObject;

/**
 * Created by shanesardinha on 2016/08/10.
 */
public class JsonHelper {

    public void jsonRequest(final IWebRequest webRequest,Context context, String url) {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        webRequest.onResponseCompleted(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        webRequest.onResponseError(error.getMessage());
                        ProgressUtility.progressDone();
                    }
                });
        queue.add(jsObjRequest);
    }
}
