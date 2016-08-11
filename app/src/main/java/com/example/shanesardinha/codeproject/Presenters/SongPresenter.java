package com.example.shanesardinha.codeproject.Presenters;

import android.content.Context;
import android.content.DialogInterface;

import com.example.shanesardinha.codeproject.Constants.Constants;
import com.example.shanesardinha.codeproject.Interfaces.ISongPresenter;
import com.example.shanesardinha.codeproject.Interfaces.IWebRequest;
import com.example.shanesardinha.codeproject.Models.Song;
import com.example.shanesardinha.codeproject.R;
import com.example.shanesardinha.codeproject.Utility.DialogHelper;
import com.example.shanesardinha.codeproject.Utility.JsonHelper;
import com.example.shanesardinha.codeproject.Utility.ProgressUtility;
import com.example.shanesardinha.codeproject.Views.SongView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanesardinha on 2016/08/08.
 */
public class SongPresenter implements ISongPresenter, IWebRequest {

    private static final String TAG = SongPresenter.class.getSimpleName();
    private SongView songView;

    public SongPresenter(Context context) {
        if (context instanceof SongView)
            this.songView = (SongView) context;
    }

    public Context getContext() {
        return (Context) songView;
    }

    public void fetchSongList() {
        try {
            ProgressUtility.createProgressDialog(getContext(), getContext().getString(R.string.fetching_song_list));
            ProgressUtility.showProgress();
            String url = getContext().getResources().getString(R.string.api_get_top_tracks)
                    + getContext().getResources().getString(R.string.api_key)
                    + Constants.API_KEY
                    + getContext().getResources().getString(R.string.api_format);
            url = url.replace(" ", "%20");
            new JsonHelper().jsonRequest(this, getContext(), url);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void parseJson(JSONObject json) {
        List<Song> songList = new ArrayList<>();
        try {
            JSONObject track = json.getJSONObject("tracks");
            JSONArray jsonTrack = track.getJSONArray("track");
            Gson gson = new Gson();

            for (int i = 0; i < jsonTrack.length(); i++) {
                JSONObject jsonObject = jsonTrack.getJSONObject(i);
                Song song = gson.fromJson(jsonObject.toString(), Song.class);
                JSONArray imageArray = jsonObject.getJSONArray("image");
                for (int j = 0; j < imageArray.length(); j++)
                    song.getImage()[j].setText(imageArray.getJSONObject(j).getString("#text"));
                songList.add(song);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {
            ProgressUtility.progressDone();
            songView.updateSongList(songList);
        }
    }

    @Override
    public String getRequestPresenter() {
        return TAG;
    }

    @Override
    public void onResponseCompleted(JSONObject response) {
        parseJson(response);
    }

    @Override
    public void onResponseError(String message) {
        System.err.println(message);
        ProgressUtility.progressDone();

        DialogInterface.OnClickListener onPositiveClick = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                fetchSongList();
            }
        };

        DialogHelper.createDialog(getContext(),
                String.format("%s\n%s", getContext().getString(R.string.connection_problem), message), onPositiveClick);
    }
}
