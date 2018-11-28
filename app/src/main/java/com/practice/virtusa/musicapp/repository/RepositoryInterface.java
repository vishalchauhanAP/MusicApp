package com.practice.virtusa.musicapp.repository;

import android.arch.lifecycle.MutableLiveData;

import com.practice.virtusa.musicapp.helpers.ApiResponse;
import com.practice.virtusa.musicapp.model.AlbumsInfoResponse;

public interface  RepositoryInterface {
    public MutableLiveData<ApiResponse<AlbumsInfoResponse>> getAlbumsInfo();
}
