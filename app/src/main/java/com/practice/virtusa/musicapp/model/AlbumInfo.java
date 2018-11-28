package com.practice.virtusa.musicapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

@Entity(tableName = "albums_info_table")
public class AlbumInfo {

    @ColumnInfo(name = "user_id")
    @SerializedName("userId")
    public String userId;

    @PrimaryKey
    @SerializedName("id")
    @NonNull
    public String albumId;

    @ColumnInfo(name = "album_title")
    @SerializedName("title")
    public String albumTitle;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }
}
