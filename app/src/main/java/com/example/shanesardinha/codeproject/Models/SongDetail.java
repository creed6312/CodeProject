package com.example.shanesardinha.codeproject.Models;

/**
 * Created by shanesardinha on 2016/08/08.
 */
public class SongDetail {

    private String name ;
    private String url ;
    private int duration ;
    private int playcount;
    private int listeners;
    private Wiki wiki ;
    private Album album ;
    private Artist artist;
    private TopTag toptags ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPlayCount() {
        return playcount;
    }

    public void setPlayCount(int playCount) {
        this.playcount = playCount;
    }

    public int getListeners() {
        return listeners;
    }

    public void setListeners(int listeners) {
        this.listeners = listeners;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Wiki getWiki() {
        return wiki;
    }

    public void setWiki(Wiki wiki) {
        this.wiki = wiki;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public TopTag getToptag() {
        return toptags;
    }

    public void setToptag(TopTag toptags) {
        this.toptags = toptags;
    }

    public class Wiki {
        private String published;
        private String summary ;
        private String content ;

        public Wiki()
        {

        }

        public Wiki(String published, String summary, String content)
        {
            this.published = published ;
            this.summary = summary ;
            this.content = content;
        }

        public String getPublished() {
            return published;
        }

        public void setPublished(String published) {
            this.published = published;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public class TopTag {

        private Tag[] tag;

        public Tag[] getTag() {
            return tag;
        }

        public void setTag(Tag[] tag) {
            this.tag = tag;
        }

        public class Tag
        {
            String name ;
            String url ;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }

    public class Album {
        private String artist;
        private String title ;
        private String url ;
        private Image[] image;

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Image[] getImage() {
            return image;
        }

        public void setImage(Image[] image) {
            this.image = image;
        }
    }
}
