package com.example.shanesardinha.codeproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shanesardinha.codeproject.BuildConfig;
import com.example.shanesardinha.codeproject.DetailSongActivity;
import com.example.shanesardinha.codeproject.Interfaces.IArtistPresenter;
import com.example.shanesardinha.codeproject.Interfaces.IDetailSongPresenter;
import com.example.shanesardinha.codeproject.Presenters.ArtistPresenter;
import com.example.shanesardinha.codeproject.Presenters.DetailSongPresenter;
import com.example.shanesardinha.codeproject.R;
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
public class DetailSongActivityTest {

    private DetailSongActivity mDetailSongActivity;

    private static TextView tvDetailName;
    private static TextView tvDetailArtist;
    private static TextView tvDetailDuration;
    private static TextView tvDetailListeners;
    private static TextView tvDetailPlayCount;
    private static TextView tvDetailSongUrl;
    private static TextView tvDetailWikiPublished;
    private static TextView tvDetailWikiSummary;
    private static TextView tvOnTour;
    private static ImageView ivDetailImage;
    private static LinearLayout llTopTag;

    @Before
    public void setUp() throws Exception {
        // Use the debug configuration
        ReflectionHelpers.setStaticField(BuildConfig.class, "DEBUG", true);

        Intent intent = new Intent();
        // Build the Activity
        Bundle savedInstanceState = new Bundle();
        mDetailSongActivity = Robolectric.buildActivity(DetailSongActivity.class)
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
        assertNotNull("mDetailSongActivity is null", mDetailSongActivity);
        assertThat(mDetailSongActivity.getClass().getSimpleName()).isEqualTo(DetailSongActivity.class.getSimpleName());
    }

    @Test
    public void test_DetailSongPresenterTag() {
        IDetailSongPresenter detailSongPresenter = new DetailSongPresenter(mDetailSongActivity);
        assertEquals("DetailSongPresenter", detailSongPresenter.getRequestPresenter());
    }

    @Test
    public void test_ArtistPresenterTag() {
        DetailSongPresenter detailSongPresenter = new DetailSongPresenter(mDetailSongActivity);
        IArtistPresenter artistPresenter = new ArtistPresenter(detailSongPresenter);
        assertEquals("ArtistPresenter", artistPresenter.getRequestPresenter());
    }

    @Test
    public void test_a_InitialiseControls() {
        tvDetailName = (TextView) mDetailSongActivity.findViewById(R.id.tv_detail_name);
        tvDetailArtist = (TextView) mDetailSongActivity.findViewById(R.id.tv_detail_artist);
        tvDetailDuration = (TextView) mDetailSongActivity.findViewById(R.id.tv_detail_duration);
        ivDetailImage = (ImageView) mDetailSongActivity.findViewById(R.id.iv_detail_image);
        tvDetailListeners = (TextView) mDetailSongActivity.findViewById(R.id.tv_detail_listeners);
        tvDetailPlayCount = (TextView) mDetailSongActivity.findViewById(R.id.tv_detail_play_count);
        tvDetailSongUrl = (TextView) mDetailSongActivity.findViewById(R.id.tv_detail_url);
        tvDetailWikiPublished = (TextView) mDetailSongActivity.findViewById(R.id.tv_detail_wiki_published);
        tvDetailWikiSummary = (TextView) mDetailSongActivity.findViewById(R.id.tv_detail_wiki_summary);
        tvOnTour = (TextView) mDetailSongActivity.findViewById(R.id.tv_detail_on_tour);
        llTopTag = (LinearLayout) mDetailSongActivity.findViewById(R.id.song_detail_tag_layout);
    }

    @Test
    public void test_b_CheckControls() {
        assertNotNull("tvDetailName is null", tvDetailName);
        assertNotNull("tvDetailArtist is null", tvDetailArtist);
        assertNotNull("tvDetailDuration is null", tvDetailDuration);
        assertNotNull("ivDetailImage is null", ivDetailImage);
        assertNotNull("tvDetailListeners is null", tvDetailListeners);
        assertNotNull("tvDetailPlayCount is null", tvDetailPlayCount);
        assertNotNull("tvDetailSongUrl is null", tvDetailSongUrl);
        assertNotNull("tvDetailWikiPublished is null", tvDetailWikiPublished);
        assertNotNull("tvDetailWikiSummary is null", tvDetailWikiSummary);
        assertNotNull("tvOnTour is null", tvOnTour);
        assertNotNull("llTopTag is null", llTopTag);
    }
}