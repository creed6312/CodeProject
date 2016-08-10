package com.example.shanesardinha.codeproject.Interfaces;

import android.content.Context;

/**
 * Created by shanesardinha on 2016/08/10.
 */
public interface IArtistPresenter {
    void jsonRequest(String url);

    void getArtistInfo(String artist);

    Context getContext();
}
