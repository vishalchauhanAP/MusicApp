package com.practice.virtusa.musicapp.networking;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiUtils {
    public static final int NO_OF_RETRIES = 4;

    public static <T> void makeCall(Call<T> callPrimary, Call<T> callSecondary, final Callback<T> callback) {
        callPrimary.enqueue(new ApiUtils.CallBackAndRetry<T>(callPrimary, callSecondary, NO_OF_RETRIES) {

            @Override
            public void onGetSuccess(Call<T> call, Response<T> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onGetError(Call<T> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }

    public static boolean isSuccess(Response response) {
        int code = response.code();
        return (code >= 200 && code < 400);
    }

    public static class CallBackAndRetry<T> implements Callback<T> {
        private int totalRetries = 4;
        private int counter = 0;

        private final Call<T> call_1;
        private final Call<T> call_2;

        public CallBackAndRetry(Call<T> call_1, Call<T> call_2, int totalRetries) {
            this.call_1 = call_1;
            this.call_2 = call_2;
            this.totalRetries = totalRetries;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (!ApiUtils.isSuccess(response))
                if (counter++ < totalRetries) {
                    retry(call_1,call_2);
                } else
                    onGetSuccess(call, response);
            else
                onGetSuccess(call,response);
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            if (counter++ < totalRetries) {
                retry(call_1,call_2);
            } else
                onGetError(call, t);
        }

        public void onGetSuccess(Call<T> call, Response<T> response) {
        }

        public void onGetError(Call<T> call, Throwable t) {
        }

        private void retry(Call<T> call_1, Call<T> call_2) {
            if(counter %2 == 0){
                if(call_1.isExecuted()){
                    call_1.clone().enqueue(this); }
                else {
                    call_1.enqueue(this);
                }
            }
            else {
                if(call_2.isExecuted()){
                    call_2.clone().enqueue(this); }
                else {
                    call_2.enqueue(this);
                }
            }
        }
    }
}