package com.practice.virtusa.musicapp.app_start;

import android.app.Application;

public class MusicApp extends Application {

    private static MusicApp INSTANCE = null;

    public static MusicApp getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

}