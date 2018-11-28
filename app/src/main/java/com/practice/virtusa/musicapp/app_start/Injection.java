package com.practice.virtusa.musicapp.app_start;

import android.util.Log;

import com.practice.virtusa.musicapp.common.AppConfig;
import com.practice.virtusa.musicapp.networking.MusicServiceApi;
import com.practice.virtusa.musicapp.repository.MockTestRepository;
import com.practice.virtusa.musicapp.repository.RemoteRepository;
import com.practice.virtusa.musicapp.repository.RepositoryInterface;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class Injection {
    private final String TAG = "Injection";

    private static Injection INSTANCE = null;

    private Injection() {
    }

    public static synchronized Injection getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new Injection();
        }
        return (INSTANCE);
    }

    public final RepositoryInterface provideRepository() {
        return RemoteRepository.getInstance();
        //return MockTestRepository.getInstance();
    }

    public final RepositoryInterface provideMockTestRepository() {
        return MockTestRepository.getInstance();
    }

    public MusicServiceApi provideMusicServiceApi() {
        return provideRetrofit(true).create(MusicServiceApi.class);
    }

    private Retrofit provideRetrofit(boolean isPrimary) {
        try {

            OkHttpClient client;

            client = new OkHttpClient.Builder()
                        .readTimeout(AppConfig.getServiceTimeOut(), TimeUnit.SECONDS)
                        .connectTimeout(AppConfig.getServiceTimeOut(), TimeUnit.SECONDS)
                        .build();

                return new Retrofit.Builder()
                        .baseUrl(AppConfig.getServerAppUrl())
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();

        } catch (Exception ex) {
            Log.e(TAG, "Error in getting Retrofit Instance" + ex.getMessage());
            return null;
        }
    }
}
