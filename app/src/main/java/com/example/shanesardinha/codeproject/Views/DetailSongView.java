package com.example.shanesardinha.codeproject.Views;

import com.example.shanesardinha.codeproject.Models.Artist;
import com.example.shanesardinha.codeproject.Models.SongDetail;

/**
 * Created by shanesardinha on 2016/08/08.
 */
public interface DetailSongView {

    void updateSongInfo(SongDetail songDetails);

    void updateArtistInfo(Artist artist);

}
