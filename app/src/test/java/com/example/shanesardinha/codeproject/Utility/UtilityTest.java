package com.example.shanesardinha.codeproject.Utility;

import android.content.Intent;
import android.os.Bundle;

import com.example.shanesardinha.codeproject.BuildConfig;
import com.example.shanesardinha.codeproject.SongActivity;
import com.example.shanesardinha.codeproject.TestHelpers.CustomRobolectricRunner;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;
import org.robolectric.util.ReflectionHelpers;

import static org.junit.Assert.assertEquals;

/**
 * Created by shanesardinha on 2016/08/10.
 */

@RunWith(CustomRobolectricRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UtilityTest {

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
    public void test_GetDateTimeUtility() throws Exception {
        assertEquals("01:52", DateTimeUtility.getMinuteAndSeconds(112000));
        assertEquals("00:02", DateTimeUtility.getMinuteAndSeconds(2000));
        assertEquals("00:20", DateTimeUtility.getMinuteAndSeconds(20000));
    }

    @Test
    public void test_ProgressDialogCreation() throws InterruptedException {
        ProgressUtility.createProgressDialog(mSongActivity,"Hello Test Dialog");
        ProgressUtility.showProgress();
        ProgressUtility.progressDone();
    }

    @Test
    public void test_GetHtml()
    {
        Assert.assertEquals("Test", Utility.getHtml("<a>Test</a>"));
        Assert.assertEquals("Test\n\n", Utility.getHtml("<p>Test</p>"));
    }
}