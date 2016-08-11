package com.example.shanesardinha.codeproject.Views;

import com.example.shanesardinha.codeproject.Models.Song;

import java.util.List;

/**
 * Created by shanesardinha on 2016/08/08.
 */
public interface SongView {

    void updateSongList(List<Song> songList);

    void cancel();
}
