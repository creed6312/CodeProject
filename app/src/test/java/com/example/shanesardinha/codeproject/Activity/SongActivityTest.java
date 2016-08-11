package com.example.shanesardinha.codeproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.shanesardinha.codeproject.BuildConfig;
import com.example.shanesardinha.codeproject.Interfaces.ISongPresenter;
import com.example.shanesardinha.codeproject.Presenters.SongPresenter;
import com.example.shanesardinha.codeproject.R;
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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by shanesardinha on 2016/08/11.
 */

@RunWith(CustomRobolectricRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SongActivityTest  {

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
    public void test_ActivityStarted() {
        // Ensure the Activity has been created
        assertNotNull("mSongActivity is null", mSongActivity);
        assertThat(mSongActivity.getClass().getSimpleName()).isEqualTo(SongActivity.class.getSimpleName());
    }

    @Test
    public void test_PresenterContext()
    {
        ISongPresenter songPresenter =  new SongPresenter(mSongActivity);
        assertEquals("com.example.shanesardinha.codeproject",songPresenter.getContext().getPackageName());
    }

    @Test
    public void test_RecyclerViewNotNull()
    {
        RecyclerView recyclerViewSongs = (RecyclerView) mSongActivity.findViewById(R.id.recyclerview_simple_song_list);
        assertNotNull("mSongActivity is null", recyclerViewSongs);
    }
}

