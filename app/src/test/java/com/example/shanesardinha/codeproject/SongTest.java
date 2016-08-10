package com.example.shanesardinha.codeproject;

import android.content.Context;

import com.example.shanesardinha.codeproject.Utility.DateTimeUtility;
import com.example.shanesardinha.codeproject.Utility.ProgressUtility;
import com.example.shanesardinha.codeproject.Views.SongView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by shanesardinha on 2016/08/10.
 */
@RunWith(MockitoJUnitRunner.class)
public class SongTest {

    @Mock
    Context mMockContext;

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testGetDateTimeUtility() throws Exception {
        assertEquals("01:52", DateTimeUtility.getMinuteAndSeconds(112000));
    }

    @Test
    public void testProgressDialogCreation(){
        ProgressUtility.createProgressDialog(mMockContext,"test");
        ProgressUtility.showProgress();
        ProgressUtility.progressDone();
    }

    @Test

}