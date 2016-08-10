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
import com.example.shanesardinha.codeproject.Interfaces.IArtistPresenter;
import com.example.shanesardinha.codeproject.Models.Artist;
import com.example.shanesardinha.codeproject.R;
import com.example.shanesardinha.codeproject.Utility.ProgressUtility;
import com.example.shanesardinha.codeproject.Views.DetailSongView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shanesardinha on 2016/08/10.
 */
public class ArtistPresenter implements IArtistPresenter {

    private DetailSongView detailSongView ;
    private ProgressDialog progressDialog;

    public ArtistPresenter(Context context) {
        this.detailSongView = (DetailSongView) context;
        ProgressUtility.createProgressDialog(context,context.getString(R.string.fetching_detail_artist));
    }

    public void parseArtistJson(JSONObject json)
    {
        try {
            JSONObject jsonArtist = json.getJSONObject("artist");
            Gson gson = new Gson();
            Artist artist = gson.fromJson(jsonArtist.toString(), Artist.class);
            JSONArray imageArray = jsonArtist.getJSONArray("image");
            for (int j = 0 ; j < imageArray.length() ; j++)
                artist.getImage()[j].setText(imageArray.getJSONObject(j).getString("#text"));
            detailSongView.updateArtistInfo(artist);
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
                        parseArtistJson(response);
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

    public void getArtistInfo(String artist)
    {
        ProgressUtility.showProgress();
        String url = getContext().getResources().getString(R.string.api_get_artist)
                + getContext().getResources().getString(R.string.api_key)
                + Constants.API_KEY
                + getContext().getResources().getString(R.string.api_artist)
                + artist
                + getContext().getResources().getString(R.string.api_format);
        url = url.replace(" ","%20");
        jsonRequest(url);
    }

    @Override
    public Context getContext() {
        return (Context) detailSongView;
    }
}
