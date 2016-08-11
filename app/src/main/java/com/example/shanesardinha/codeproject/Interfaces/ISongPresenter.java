package com.example.shanesardinha.codeproject.Interfaces;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created by shanesardinha on 2016/08/08.
 */
public interface ISongPresenter {

    void parseJson(JSONObject json) ;

    void fetchSongList();

    Context getContext();

    String getRequestPresenter() ;
}
