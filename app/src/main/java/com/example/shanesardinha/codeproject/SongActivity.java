package com.example.shanesardinha.codeproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.shanesardinha.codeproject.Adapters.SongListAdapter;
import com.example.shanesardinha.codeproject.Interfaces.IMainPresenter;
import com.example.shanesardinha.codeproject.Models.Song;
import com.example.shanesardinha.codeproject.Presenters.MainPresenter;
import com.example.shanesardinha.codeproject.Views.MainView;

import java.util.List;

public class SongActivity extends AppCompatActivity implements MainView
{
    private IMainPresenter mainPresenter;
    private RecyclerView recyclerViewSongs;
    private SongListAdapter songListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        setUpViews();

        mainPresenter = new MainPresenter(this);
        mainPresenter.fetchSongList();
    }

    private void setUpViews()
    {
        recyclerViewSongs = (RecyclerView) findViewById(R.id.recyclerview_simple_song_list);
    }

    @Override
    public void updateSongList(List<Song> songList) {
        songListAdapter = new SongListAdapter(this,songList);
        recyclerViewSongs.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSongs.setAdapter(songListAdapter);
        songListAdapter.notifyDataSetChanged();
    }
}
