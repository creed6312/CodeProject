package com.example.shanesardinha.codeproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.shanesardinha.codeproject.AboutActivity;
import com.example.shanesardinha.codeproject.BuildConfig;
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

import static junit.framework.Assert.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by shanesardinha on 2016/08/11.
 */

@RunWith(CustomRobolectricRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AboutActivityTest {

    private AboutActivity mAboutActivity;

    private static TextView tvAbout ;
    private static TextView tvAboutLastFm ;

    @Before
    public void setUp() throws Exception {
        // Use the debug configuration
        ReflectionHelpers.setStaticField(BuildConfig.class, "DEBUG", true);

        Intent intent = new Intent();
        // Build the Activity
        Bundle savedInstanceState = new Bundle();
        mAboutActivity = Robolectric.buildActivity(AboutActivity.class)
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
        assertNotNull("mAboutActivity is null", mAboutActivity);
        assertThat(mAboutActivity.getClass().getSimpleName()).isEqualTo(AboutActivity.class.getSimpleName());
    }

    @Test
    public void test_a_InitialiseControls() {
        tvAbout = (TextView) mAboutActivity.findViewById(R.id.tv_about);
        tvAboutLastFm = (TextView) mAboutActivity.findViewById(R.id.tv_about_last_fm);
    }

    @Test
    public void test_b_CheckControls() {
        assertNotNull("tvDetailName is null", tvAbout);
        assertNotNull("tvDetailArtist is null", tvAboutLastFm);
    }
}