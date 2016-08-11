package com.example.shanesardinha.codeproject.Presenters;

import android.content.Context;
import android.content.DialogInterface;

import com.example.shanesardinha.codeproject.Constants.Constants;
import com.example.shanesardinha.codeproject.Interfaces.IArtistPresenter;
import com.example.shanesardinha.codeproject.Interfaces.IDetailSongPresenter;
import com.example.shanesardinha.codeproject.Interfaces.IWebRequest;
import com.example.shanesardinha.codeproject.Models.Artist;
import com.example.shanesardinha.codeproject.Models.SongDetail;
import com.example.shanesardinha.codeproject.R;
import com.example.shanesardinha.codeproject.Utility.DialogHelper;
import com.example.shanesardinha.codeproject.Utility.JsonHelper;
import com.example.shanesardinha.codeproject.Utility.ProgressUtility;
import com.example.shanesardinha.codeproject.Views.DetailSongView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shanesardinha on 2016/08/08.
 */
public class DetailSongPresenter implements IDetailSongPresenter, IWebRequest {

    private static final String TAG = DetailSongPresenter.class.getSimpleName();
    private DetailSongView detailSongView;
    private String artist;
    private String name;
    private SongDetail songDetail;

    public DetailSongPresenter(Context context) {
        if (context instanceof DetailSongView)
            detailSongView = (DetailSongView) context;
    }

    public SongDetail getSongDetail()
    {
        return songDetail;
    }

    @Override
    public void parseJson(JSONObject json) {
        try {
            JSONObject jsonTrack = json.getJSONObject("track");
            Gson gson = new Gson();
            songDetail = gson.fromJson(jsonTrack.toString(), SongDetail.class);

            IArtistPresenter artistPresenter = new ArtistPresenter(this);
            artistPresenter.getArtistInfo(artist);
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            ProgressUtility.progressDone();
        }
    }

    @Override
    public void fetchSongDetails(String artist, String name) {
        this.artist = artist;
        this.name = name;
        ProgressUtility.createProgressDialog(getContext(), getContext().getString(R.string.fetching_detail_artist));
        ProgressUtility.showProgress();
        String url = getContext().getResources().getString(R.string.api_get_track)
                + getContext().getResources().getString(R.string.api_key)
                + Constants.API_KEY
                + getContext().getResources().getString(R.string.api_artist)
                + artist
                + getContext().getResources().getString(R.string.api_track)
                + name
                + getContext().getResources().getString(R.string.api_format);
        url = url.replace(" ", "%20");
        new JsonHelper().jsonRequest(this, getContext(), url);
    }

    @Override
    public void updateArtistInfo(Artist artist) {
        songDetail.setArtist(artist);
        detailSongView.updateSongInfo(songDetail);
    }

    @Override
    public Context getContext() {
        return (Context) detailSongView;
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
                fetchSongDetails(artist, name);
            }
        };

        DialogHelper.createDialog(getContext(),
                String.format("%s\n%s", getContext().getString(R.string.connection_problem), message), onPositiveClick);
    }
}
