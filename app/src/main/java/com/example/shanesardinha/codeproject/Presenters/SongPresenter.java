package com.example.shanesardinha.codeproject.Presenters;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.shanesardinha.codeproject.Constants.Constants;
import com.example.shanesardinha.codeproject.Interfaces.ISongPresenter;
import com.example.shanesardinha.codeproject.Models.Song;
import com.example.shanesardinha.codeproject.R;
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
public class SongPresenter implements ISongPresenter {

    private SongView songView;

    public SongPresenter(Context context) {
        this.songView = (SongView) context;
        ProgressUtility.createProgressDialog(context,context.getString(R.string.fetching_song_list));
    }

    public Context getContext()
    {
        return (Context)songView;
    }

    public void fetchSongList() {
        try {
            ProgressUtility.showProgress();
            String url = getContext().getResources().getString(R.string.api_get_top_tracks)
                    + getContext().getResources().getString(R.string.api_key)
                    + Constants.API_KEY
                    + getContext().getResources().getString(R.string.api_format);
            url = url.replace(" ","%20");
            jsonRequest(url);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void parseJson(JSONObject json)
    {
        List<Song> songList = new ArrayList<>();
        try {
            JSONObject track = json.getJSONObject("tracks");
            JSONArray jsonTrack = track.getJSONArray("track");
            Gson gson = new Gson();

            for (int i = 0 ; i < jsonTrack.length() ; i++)
            {
                JSONObject jsonObject = jsonTrack.getJSONObject(i);
                Song song = gson.fromJson(jsonObject.toString(), Song.class);
                JSONArray imageArray = jsonObject.getJSONArray("image");
                for (int j = 0 ; j < imageArray.length() ; j++)
                    song.getImage()[j].setText(imageArray.getJSONObject(j).getString("#text"));
                songList.add(song);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        finally {
            ProgressUtility.progressDone();
            songView.updateSongList(songList);
        }
    }

    @Override
    public void jsonRequest(String url) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                       parseJson(response);
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
}
