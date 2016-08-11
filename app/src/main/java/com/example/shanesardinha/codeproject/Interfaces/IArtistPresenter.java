package com.example.shanesardinha.codeproject.Interfaces;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created by shanesardinha on 2016/08/10.
 */
public interface IArtistPresenter {

    void parseJson(JSONObject json);

    void getArtistInfo(String artist);

    Context getContext();

    String getRequestPresenter() ;
}
