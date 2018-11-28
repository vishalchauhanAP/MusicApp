package com.practice.virtusa.musicapp.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.practice.virtusa.musicapp.helpers.ApiResponse;
import com.practice.virtusa.musicapp.model.AlbumInfo;
import com.practice.virtusa.musicapp.model.AlbumInfoDao;
import com.practice.virtusa.musicapp.model.AlbumInfoDatabase;
import com.practice.virtusa.musicapp.model.AlbumsInfoResponse;

import java.util.List;

public class LocalAlbumsRepository {

    private AlbumInfoDao albumInfoDao;
    private LiveData<List<AlbumInfo>> albumsInfo;

    public LocalAlbumsRepository(Application application) {
        AlbumInfoDatabase database = AlbumInfoDatabase.getDatabase(application);
        albumInfoDao = database.albumInfoDao();
        albumsInfo = albumInfoDao.getAlbumsInfo();
    }

    public LiveData<List<AlbumInfo>> getAlbumsInfo() {
       return albumsInfo;
    }

    public void insert (AlbumInfo album) {
        new insertAsyncTask(albumInfoDao).execute(album);
    }

    private static class insertAsyncTask extends AsyncTask<AlbumInfo, Void, Void> {

        private AlbumInfoDao asyncTaskDao;

        insertAsyncTask(AlbumInfoDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final AlbumInfo... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }
}