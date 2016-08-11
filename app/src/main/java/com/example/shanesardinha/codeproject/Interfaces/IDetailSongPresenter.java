package com.example.shanesardinha.codeproject.Interfaces;

import android.content.Context;

import com.example.shanesardinha.codeproject.Models.Artist;
import com.example.shanesardinha.codeproject.Models.SongDetail;

import org.json.JSONObject;

/**
 * Created by shanesardinha on 2016/08/08.
 */
public interface IDetailSongPresenter {

    void parseJson(JSONObject json);

    void fetchSongDetails(String artist,String name);

    void updateArtistInfo(Artist artist);

    SongDetail getSongDetail();

    Context getContext();

    String getRequestPresenter() ;
}
