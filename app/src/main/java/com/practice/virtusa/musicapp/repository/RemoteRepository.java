package com.practice.virtusa.musicapp.repository;

import android.arch.lifecycle.MutableLiveData;

import com.practice.virtusa.musicapp.app_start.Injection;
import com.practice.virtusa.musicapp.common.ApiError;
import com.practice.virtusa.musicapp.helpers.ApiResponse;
import com.practice.virtusa.musicapp.model.AlbumsInfoResponse;
import com.practice.virtusa.musicapp.networking.ApiUtils;
import com.practice.virtusa.musicapp.networking.MusicServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteRepository implements RepositoryInterface {
    private MusicServiceApi musicServiceApi = Injection.getInstance().provideMusicServiceApi();

    private final String TAG = "RepoAlbums";

    private static RemoteRepository INSTANCE = null;

    private RemoteRepository() {};

    public static synchronized RemoteRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository();
        }
        return(INSTANCE);
    }

    public MutableLiveData<ApiResponse<AlbumsInfoResponse>> getAlbumsInfo() {
        final MutableLiveData<ApiResponse<AlbumsInfoResponse>> liveDataAlbumInfo = new MutableLiveData<ApiResponse<AlbumsInfoResponse>>();

        ApiUtils.makeCall(musicServiceApi.GetAlbumInfo(),musicServiceApi.GetAlbumInfo(),new Callback<AlbumsInfoResponse>() {
            @Override
            public void onResponse(Call<AlbumsInfoResponse> call, Response<AlbumsInfoResponse> response) {
                if (response != null && response.isSuccessful()) {
                    liveDataAlbumInfo.setValue(ApiResponse.getInstance().success(response.body()));
                }
                else {
                    liveDataAlbumInfo.setValue(ApiResponse.getInstance().error(ApiError.GET_USER_ALBUMS_ERROR,null));
                }
            }

            @Override
            public void onFailure(Call<AlbumsInfoResponse> call, Throwable t) {
                liveDataAlbumInfo.setValue(ApiResponse.getInstance().error(ApiError.GET_USER_ALBUMS_ERROR,null));
            }
        });
        return liveDataAlbumInfo;
    }
}
