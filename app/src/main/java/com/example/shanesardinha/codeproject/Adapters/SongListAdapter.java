package com.example.shanesardinha.codeproject.Adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shanesardinha.codeproject.Constants.Constants;
import com.example.shanesardinha.codeproject.DetailSongActivity;
import com.example.shanesardinha.codeproject.Models.Song;
import com.example.shanesardinha.codeproject.R;

import java.util.List;

/**
 * Created by shanesardinha on 2016/08/08.
 */
public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder> {

    private List<Song> songs ;
    private Activity songActivity ;

    public SongListAdapter(Activity songActivity, List<Song> songs) {
        this.songs = songs ;
        this.songActivity = songActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_simple_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Song song = songs.get(position);

        Glide.with(holder.mView.getContext()).load(song.getImage()[Constants.HIGH_RES_IMAGE].getText()).centerCrop()
                .placeholder(R.drawable.no_art).crossFade().into(holder.mAlbumArt);

        holder.mSongName.setText(String.format("Song: %s", song.getName()));
        holder.mSongArtist.setText(String.format("Artist: %s", song.getArtist().getName()));
        holder.mSongListeners.setText(String.format("Listeners: %s", song.getListeners()));
        holder.mSongPlayCount.setText(String.format("Play Count: %s", song.getPlayCount()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDetailSong = new Intent(songActivity, DetailSongActivity.class);
                intentDetailSong.putExtra("songName",song.getName());
                intentDetailSong.putExtra("songArtist",song.getArtist().getName());
                songActivity.startActivity(intentDetailSong,ActivityOptions.makeSceneTransitionAnimation(songActivity).toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mAlbumArt ;
        public final TextView mSongName ;
        public final TextView mSongArtist;
        public final TextView mSongListeners;
        public final TextView mSongPlayCount;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mAlbumArt = (ImageView) view.findViewById(R.id.iv_album_art);
            mSongName = (TextView) view.findViewById(R.id.tv_song_name);
            mSongArtist = (TextView) view.findViewById(R.id.tv_song_artist);
            mSongListeners = (TextView) view.findViewById(R.id.tv_song_listeners);
            mSongPlayCount = (TextView) view.findViewById(R.id.tv_song_play_count);
        }
    }
}
