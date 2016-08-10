package com.example.shanesardinha.codeproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shanesardinha.codeproject.Constants.Constants;
import com.example.shanesardinha.codeproject.Interfaces.IArtistPresenter;
import com.example.shanesardinha.codeproject.Interfaces.IBaseActivity;
import com.example.shanesardinha.codeproject.Interfaces.IDetailSongPresenter;
import com.example.shanesardinha.codeproject.Models.Artist;
import com.example.shanesardinha.codeproject.Models.SongDetail;
import com.example.shanesardinha.codeproject.Presenters.ArtistPresenter;
import com.example.shanesardinha.codeproject.Presenters.DetailSongPresenter;
import com.example.shanesardinha.codeproject.Utility.DateTimeUtility;
import com.example.shanesardinha.codeproject.Utility.Utility;
import com.example.shanesardinha.codeproject.Views.DetailSongView;

/**
 * Created by shanesardinha on 2016/08/08.
 */
public class DetailSongActivity extends AppCompatActivity implements DetailSongView, IBaseActivity {
    private TextView tvDetailName;
    private TextView tvDetailArtist;
    private TextView tvDetailDuration;
    private TextView tvDetailListeners;
    private TextView tvDetailPlayCount;
    private TextView tvDetailSongUrl;
    private TextView tvDetailWikiPublished;
    private TextView tvDetailWikiSummary;
    private TextView tvOnTour;
    private ImageView ivDetailImage;
    private LinearLayout llTopTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpViews();
        createPresenter();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setUpViews() {
        tvDetailName = (TextView) findViewById(R.id.tv_detail_name);
        tvDetailArtist = (TextView) findViewById(R.id.tv_detail_artist);
        tvDetailDuration = (TextView) findViewById(R.id.tv_detail_duration);
        ivDetailImage = (ImageView) findViewById(R.id.iv_detail_image);
        tvDetailListeners = (TextView) findViewById(R.id.tv_detail_listeners);
        tvDetailPlayCount = (TextView) findViewById(R.id.tv_detail_play_count);
        tvDetailSongUrl = (TextView) findViewById(R.id.tv_detail_url);
        tvDetailWikiPublished = (TextView) findViewById(R.id.tv_detail_wiki_published);
        tvDetailWikiSummary = (TextView) findViewById(R.id.tv_detail_wiki_summary);
        tvOnTour = (TextView) findViewById(R.id.tv_detail_on_tour);
        llTopTag = (LinearLayout) findViewById(R.id.song_detail_tag_layout);
    }

    @Override
    public void createPresenter() {
        String artist = getIntent().getStringExtra("songArtist");
        String name = getIntent().getStringExtra("songName");

        IDetailSongPresenter detailSongPresenter = new DetailSongPresenter(this);
        detailSongPresenter.fetchSongDetails(artist, name);

        IArtistPresenter artistPresenter = new ArtistPresenter(this);
        artistPresenter.getArtistInfo(artist);
    }

    @Override
    public void updateSongInfo(SongDetail songDetails) {
        tvDetailName.setText(String.format("Song: %s", songDetails.getName()));
        tvDetailDuration.setText(String.format("Duration: %s", DateTimeUtility.getMinuteAndSeconds(songDetails.getDuration())));
        tvDetailListeners.setText(String.format("Listeners: %s", songDetails.getListeners()));
        tvDetailPlayCount.setText(String.format("Play Count: %s", songDetails.getPlayCount()));
        tvDetailSongUrl.setText(String.format("Song Url: %s", songDetails.getUrl()));
        if (songDetails.getWiki() == null)
            tvDetailWikiPublished.setText(R.string.no_wiki_details);
        else {
            tvDetailWikiPublished.setText(String.format("Published: %s", songDetails.getWiki().getPublished()));
            tvDetailWikiSummary.setText(String.format("Summary: %s", Utility.getHtml(songDetails.getWiki().getSummary())));
        }

        for (int i = 0; i < songDetails.getToptag().getTag().length; i++) {
            TextView tvTagName = new TextView(llTopTag.getContext());
            tvTagName.setAutoLinkMask(Constants.AUTO_WEB_LINK);
            tvTagName.setText(String.format("Name: %s", songDetails.getToptag().getTag()[i].getName()));
            llTopTag.addView(tvTagName);

            TextView tvTagUrl = new TextView(llTopTag.getContext());
            tvTagUrl.setAutoLinkMask(Constants.AUTO_WEB_LINK);
            tvTagUrl.setText(String.format("Url: %s", songDetails.getToptag().getTag()[i].getUrl()));
            llTopTag.addView(tvTagUrl);
        }
    }

    @Override
    public void updateArtistInfo(Artist artist) {
        tvDetailArtist.setText(String.format("Artist: %s", artist.getName()));
        tvOnTour.setText(String.format("On Tour: %s", artist.isOnTour()));

        Glide.with(ivDetailImage.getContext()).load(artist.getImage()[Constants.HIGH_RES_IMAGE].getText()).centerCrop()
                .placeholder(R.drawable.no_art).crossFade().into(ivDetailImage);

    }
}
