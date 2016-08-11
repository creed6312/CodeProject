package com.example.shanesardinha.codeproject.Adapter;

import android.content.Intent;
import android.os.Bundle;

import com.example.shanesardinha.codeproject.Adapters.SongListAdapter;
import com.example.shanesardinha.codeproject.BuildConfig;
import com.example.shanesardinha.codeproject.Models.Song;
import com.example.shanesardinha.codeproject.SongActivity;
import com.example.shanesardinha.codeproject.TestHelpers.CustomRobolectricRunner;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;
import org.robolectric.util.ReflectionHelpers;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by shanesardinha on 2016/08/11.
 */
@RunWith(CustomRobolectricRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SongListAdapterTest {

    private SongActivity mSongActivity;

    @Before
    public void setUp() throws Exception {
        // Use the debug configuration
        ReflectionHelpers.setStaticField(BuildConfig.class, "DEBUG", true);

        Intent intent = new Intent();
        // Build the Activity
        Bundle savedInstanceState = new Bundle();
        mSongActivity = Robolectric.buildActivity(SongActivity.class)
                .withIntent(intent)
                .create(savedInstanceState)
                .start()
                .resume()
                .pause()
                .destroy()
                .visible()
                .get();
    }

    @Test
    public void test_AdapterCreation()
    {
        List<Song> songList = new ArrayList<>();
        SongListAdapter songListAdapter = new SongListAdapter(mSongActivity,songList);
        assertNotNull("songListAdapter is null", songListAdapter);
    }

    @Test
    public void test_AdapterItemCount()
    {
        List<Song> songList = new ArrayList<>();
        Song song = new Song();
        songList.add(song);
        songList.add(song);
        songList.add(song);
        SongListAdapter songListAdapter = new SongListAdapter(mSongActivity,songList);
        assertEquals(3, songListAdapter.getItemCount());
    }
}
