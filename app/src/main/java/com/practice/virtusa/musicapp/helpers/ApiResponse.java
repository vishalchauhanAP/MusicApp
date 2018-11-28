package com.practice.virtusa.musicapp.helpers;

import com.practice.virtusa.musicapp.common.ApiError;

public final class ApiResponse<T> {
    private  ApiStatus apiStatus;
    private  T data;
    private ApiError error;

    private static ApiResponse INSTANCE = null;

    private ApiResponse() { }

    public static synchronized ApiResponse getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new ApiResponse();
        }
        return (INSTANCE);

    }

    public ApiResponse(ApiStatus status, T data, ApiError error) {
        super();
        this.apiStatus = status;
        this.data = data;
        this.error = error;
    }

    public final ApiStatus getStatus() {
        return this.apiStatus;
    }

    public final T getData() {
        return this.data;
    }

    public final ApiError getError() {
        return this.error;
    }

    public <T> ApiResponse<T> success(T data) {
        return new ApiResponse(ApiStatus.SUCCESS, data,null);
    }

    public <T> ApiResponse<T> error(ApiError error, T data) {
        return new ApiResponse(ApiStatus.ERROR, data, error);
    }

}
