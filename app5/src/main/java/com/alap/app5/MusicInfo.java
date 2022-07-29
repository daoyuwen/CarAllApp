package com.alap.app5;

import java.net.URL;

public class MusicInfo {

    private String title;
    private long id;
    private String album;
    private String artist;
    private long size;
    private String displayName;
    private long duration;
    private String url;

    public MusicInfo(String title, long id, String album, String artist, long size, String displayName, long duration, String url) {
        this.title = title;
        this.id = id;
        this.album = album;
        this.artist = artist;
        this.size = size;
        this.displayName = displayName;
        this.duration = duration;
        this.url = url;
    }

    public MusicInfo() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
