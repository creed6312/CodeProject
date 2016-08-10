package com.example.shanesardinha.codeproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.shanesardinha.codeproject.Adapters.SongListAdapter;
import com.example.shanesardinha.codeproject.Interfaces.IBaseActivity;
import com.example.shanesardinha.codeproject.Interfaces.ISongPresenter;
import com.example.shanesardinha.codeproject.Models.Song;
import com.example.shanesardinha.codeproject.Presenters.SongPresenter;
import com.example.shanesardinha.codeproject.Views.SongView;

import java.util.List;

public class SongActivity extends AppCompatActivity implements SongView, IBaseActivity
{
    private RecyclerView recyclerViewSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpViews();
        createPresenter();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setUpViews()
    {
        recyclerViewSongs = (RecyclerView) findViewById(R.id.recyclerview_simple_song_list);
    }

    @Override
    public void createPresenter()
    {
        ISongPresenter songPresenter = new SongPresenter(this);
        songPresenter.fetchSongList();
    }

    @Override
    public void updateSongList(List<Song> songList) {
        SongListAdapter songListAdapter = new SongListAdapter(this, songList);
        recyclerViewSongs.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSongs.setAdapter(songListAdapter);
        songListAdapter.notifyDataSetChanged();
    }
}
