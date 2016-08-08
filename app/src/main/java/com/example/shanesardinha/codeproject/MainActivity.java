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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView
{
    private IMainPresenter mainPresenter;
    private RecyclerView recyclerViewSongs;
    private SongListAdapter songListAdapter;
    private List<Song> songList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        setUpViews();
        setUpAdapters();

        addItemToView("Test Song","Im Awesome","Beyonce","https://media4.popsugar-assets.com/files/2016/03/23/907/n/1922398/554adbfc_beyonce-4-deluxe.xxxlarge.jpg");
        addItemToView("Test Song","Im Awesome","Beyonce","https://media4.popsugar-assets.com/files/2016/03/23/907/n/1922398/554adbfc_beyonce-4-deluxe.xxxlarge.jpg");
        addItemToView("Test Song","Im Awesome","Beyonce","https://media4.popsugar-assets.com/files/2016/03/23/907/n/1922398/554adbfc_beyonce-4-deluxe.xxxlarge.jpg");

        mainPresenter = new MainPresenter(this);

    }

    private void setUpViews()
    {
        recyclerViewSongs = (RecyclerView) findViewById(R.id.recyclerview_simple_song_list);
    }

    private void setUpAdapters()
    {
        songList = new ArrayList<>();
        songListAdapter = new SongListAdapter(songList);
        recyclerViewSongs.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSongs.setAdapter(songListAdapter);
    }

    private void addItemToView(String name,String album,String artist, String albumArt)
    {
        Song song = new Song();
        song.setName(name);
        song.setAlbum(album);
        song.setArtist(artist);
        song.setAlbumArt(albumArt);
        songList.add(song);
        songListAdapter.notifyDataSetChanged();
    }

    @Override
    public void UpdateUI() {

    }

}
