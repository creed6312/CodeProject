package com.example.shanesardinha.codeproject.Presenters;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.shanesardinha.codeproject.Constants.Constants;
import com.example.shanesardinha.codeproject.Interfaces.IMainPresenter;
import com.example.shanesardinha.codeproject.Models.Song;
import com.example.shanesardinha.codeproject.R;
import com.example.shanesardinha.codeproject.Views.MainView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanesardinha on 2016/08/08.
 */
public class MainPresenter implements IMainPresenter {

    private MainView mainView;
    private Context context;
    private ProgressDialog progressDialog;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        this.context = (Context) mainView;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(R.string.fetching_song_list));
        progressDialog.setCancelable(false);
    }

    public void fetchSongList() {
        try {
            showProgress();
            String url = context.getResources().getString(R.string.api_get_top_tracks)
                    + context.getResources().getString(R.string.api_key)
                    + Constants.apiKey + context.getResources().getString(R.string.api_format);
            jsonRequest(url);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void parseJson(JSONObject json)
    {
        List<Song> songList = new ArrayList<>();
        try {
            JSONObject track = json.getJSONObject("tracks");
            JSONArray trackList = track.getJSONArray("track");

            for (int i = 0 ; i < trackList.length() ; i++)
            {
                Song song = new Song();
                JSONObject jsonTrack = trackList.getJSONObject(i);
                JSONObject jsonArtist = jsonTrack.getJSONObject("artist");
                JSONArray jsonImageArray = jsonTrack.getJSONArray("image");
                JSONObject jsonImage = jsonImageArray.getJSONObject(2);
                song.setName(jsonTrack.getString("name"));
                song.setArtist(jsonArtist.getString("name"));
                song.setListeners(jsonTrack.getInt("listeners"));
                song.setPlayCount(jsonTrack.getInt("playcount"));
                song.setAlbumArt(jsonImage.getString("#text"));
                songList.add(song);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        finally {
            hideProgress();
            mainView.updateSongList(songList);
        }
    }

    private void jsonRequest(String url) {
        RequestQueue queue = Volley.newRequestQueue(context);
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
                hideProgress();
            }
        });
        queue.add(jsObjRequest);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void hideProgress() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    public void showProgress() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }
}
