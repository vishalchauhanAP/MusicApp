package com.practice.virtusa.musicapp.repository;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.practice.virtusa.musicapp.app_start.MusicApp;
import com.practice.virtusa.musicapp.common.ApiError;
import com.practice.virtusa.musicapp.helpers.ApiResponse;
import com.practice.virtusa.musicapp.model.AlbumsInfoResponse;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public class MockTestRepository implements RepositoryInterface {

    private static MockTestRepository INSTANCE = null;

    private final String TAG = "RepoAlbums";

    private final long FORCE_DELAY = 2000;

    private MockTestRepository() {};

    public static synchronized MockTestRepository getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new MockTestRepository();
        }
        return(INSTANCE);
    }

    public MutableLiveData<ApiResponse<AlbumsInfoResponse>> getAlbumsInfo() {
        final MutableLiveData<ApiResponse<AlbumsInfoResponse>> liveDataAlbumsInfo = new MutableLiveData<ApiResponse<AlbumsInfoResponse>>();
        AlbumsInfoResponse response = new AlbumsInfoResponse();
        Gson gson = new Gson();
        try {

            String json = this.loadJSONFromFile("albumsInfoResponse.json", MusicApp.getInstance().getBaseContext());
            Type jsonType = (new TypeToken<AlbumsInfoResponse>() {
            }).getType();
            response = gson.fromJson(json, jsonType);

            android.os.Handler handler = new android.os.Handler();
            final AlbumsInfoResponse finalResponse = response;
            handler.postDelayed(new Runnable() {
                public void run() {
                    liveDataAlbumsInfo.setValue(ApiResponse.getInstance().success(finalResponse));
                }
            }, FORCE_DELAY);
        } catch (Exception ex) {
            liveDataAlbumsInfo.setValue(ApiResponse.getInstance().error(ApiError.GET_USER_ALBUMS_ERROR, ex.getMessage()));
        }
        return liveDataAlbumsInfo;
    }

    private final String loadJSONFromFile(String filename, Context context) throws Exception{
        String json = null;

        try {
            InputStream inputStream = context.getAssets().open(filename);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Log.e(TAG, "Error reading from " + filename, ex);
            throw  ex;

        }
        return json;
    }
}

