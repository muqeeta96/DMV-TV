package com.tabi.dmv_tv.network;

import com.tabi.dmv_tv.models.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequest {
    @GET("/e7dcbd9ef88a1")
    Call<ApiResponse> getVideos();
}
