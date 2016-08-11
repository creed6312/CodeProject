package com.example.shanesardinha.codeproject.Presenters;

import android.content.Context;
import android.content.DialogInterface;

import com.example.shanesardinha.codeproject.Constants.Constants;
import com.example.shanesardinha.codeproject.Interfaces.IArtistPresenter;
import com.example.shanesardinha.codeproject.Interfaces.IWebRequest;
import com.example.shanesardinha.codeproject.Models.Artist;
import com.example.shanesardinha.codeproject.R;
import com.example.shanesardinha.codeproject.Utility.DialogHelper;
import com.example.shanesardinha.codeproject.Utility.JsonHelper;
import com.example.shanesardinha.codeproject.Utility.ProgressUtility;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shanesardinha on 2016/08/10.
 */
public class ArtistPresenter implements IArtistPresenter, IWebRequest {

    private static final String TAG = ArtistPresenter.class.getSimpleName();
    private DetailSongPresenter detailSongPresenter;
    private String artist;

    public ArtistPresenter(DetailSongPresenter context) {
        if (context != null)
            this.detailSongPresenter = context;
    }

    @Override
    public void parseJson(JSONObject json) {
        try {
            JSONObject jsonArtist = json.getJSONObject("artist");
            Gson gson = new Gson();
            Artist artist = gson.fromJson(jsonArtist.toString(), Artist.class);
            JSONArray imageArray = jsonArtist.getJSONArray("image");
            for (int j = 0; j < imageArray.length(); j++)
                artist.getImage()[j].setText(imageArray.getJSONObject(j).getString("#text"));
            detailSongPresenter.updateArtistInfo(artist);
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            ProgressUtility.progressDone();
        }
    }

    public void getArtistInfo(String artist) {
        this.artist = artist;
        ProgressUtility.createProgressDialog(getContext(), getContext().getString(R.string.fetching_detail_song));
        ProgressUtility.showProgress();
        String url = getContext().getResources().getString(R.string.api_get_artist)
                + getContext().getResources().getString(R.string.api_key)
                + Constants.API_KEY
                + getContext().getResources().getString(R.string.api_artist)
                + artist
                + getContext().getResources().getString(R.string.api_format);
        url = url.replace(" ", "%20");
        new JsonHelper().jsonRequest(this, getContext(), url);
    }

    @Override
    public Context getContext() {
        return detailSongPresenter.getContext();
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
                getArtistInfo(artist);
            }
        };

        DialogInterface.OnClickListener onNegativeClick = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        };

        DialogHelper.createDialog(getContext(),
                String.format("%s\n%s", getContext().getString(R.string.connection_problem), message)
                , onPositiveClick, onNegativeClick);
    }
}
