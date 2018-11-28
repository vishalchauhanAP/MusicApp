package com.practice.virtusa.musicapp.networking;

import com.practice.virtusa.musicapp.model.AlbumsInfoResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MusicServiceApi {
    @GET("albums")
    Call<AlbumsInfoResponse> GetAlbumInfo();
}
