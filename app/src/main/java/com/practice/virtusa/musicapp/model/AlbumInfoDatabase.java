package com.practice.virtusa.musicapp.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.practice.virtusa.musicapp.common.Global;


@Database(entities = {AlbumInfo.class}, version = 1)
public abstract class AlbumInfoDatabase extends RoomDatabase {

    public abstract AlbumInfoDao albumInfoDao();

    private static AlbumInfoDatabase INSTANCE;

    public static AlbumInfoDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AlbumInfoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AlbumInfoDatabase.class, Global.ALBUM_INFO_DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}