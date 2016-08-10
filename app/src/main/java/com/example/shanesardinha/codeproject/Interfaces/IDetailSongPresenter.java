package com.example.shanesardinha.codeproject.Interfaces;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created by shanesardinha on 2016/08/08.
 */
public interface IDetailSongPresenter {

    void fetchSongDetails(String artist,String name);

    void parseSongDetailJson(JSONObject json);

    void jsonRequest(String url);

    Context getContext();

}
