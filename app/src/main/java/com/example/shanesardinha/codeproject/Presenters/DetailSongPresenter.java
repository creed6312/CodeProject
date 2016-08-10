package com.example.shanesardinha.codeproject.Presenters;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.shanesardinha.codeproject.Constants.Constants;
import com.example.shanesardinha.codeproject.Interfaces.IDetailSongPresenter;
import com.example.shanesardinha.codeproject.Models.SongDetail;
import com.example.shanesardinha.codeproject.R;
import com.example.shanesardinha.codeproject.Utility.ProgressUtility;
import com.example.shanesardinha.codeproject.Views.DetailSongView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shanesardinha on 2016/08/08.
 */
public class DetailSongPresenter implements IDetailSongPresenter {

    private DetailSongView detailSongView;

    public DetailSongPresenter(Context context)
    {
        detailSongView = (DetailSongView) context;
        ProgressUtility.createProgressDialog(context,context.getString(R.string.fetching_detail_song));
    }

    @Override
    public void fetchSongDetails(String artist,String name) {
        ProgressUtility.showProgress();
        String url = getContext().getResources().getString(R.string.api_get_track)
                + getContext().getResources().getString(R.string.api_key)
                + Constants.API_KEY
                + getContext().getResources().getString(R.string.api_artist)
                + artist
                + getContext().getResources().getString(R.string.api_track)
                + name
                + getContext().getResources().getString(R.string.api_format);
        url = url.replace(" ","%20");
        jsonRequest(url);
    }
    @Override
    public void parseSongDetailJson(JSONObject json)
    {
        try {
            JSONObject jsonTrack = json.getJSONObject("track");
            Gson gson = new Gson();
            SongDetail songDetail = gson.fromJson(jsonTrack.toString(), SongDetail.class);
            detailSongView.updateSongInfo(songDetail);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        finally {
            ProgressUtility.progressDone();
        }
    }

    @Override
    public void jsonRequest(String url) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseSongDetailJson(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.err.println(error.getMessage());
                        ProgressUtility.progressDone();
                    }
                });
        queue.add(jsObjRequest);
    }

    @Override
    public Context getContext() {
        return (Context) detailSongView;
    }
}
