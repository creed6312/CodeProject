package com.example.shanesardinha.codeproject.Interfaces;

import org.json.JSONObject;

/**
 * Created by shanesardinha on 2016/08/10.
 */
public interface IWebRequest {

    void onResponseCompleted(JSONObject response);

    void onResponseError(String message);
}
