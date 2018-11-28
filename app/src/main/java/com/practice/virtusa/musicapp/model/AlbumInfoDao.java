package com.practice.virtusa.musicapp.model;

import android.arch.lifecycle.LiveData;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface AlbumInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AlbumInfo album);

    @Query("SELECT * from albums_info_table ORDER BY album_title ASC")
    LiveData<List<AlbumInfo>> getAlbumsInfo();
}