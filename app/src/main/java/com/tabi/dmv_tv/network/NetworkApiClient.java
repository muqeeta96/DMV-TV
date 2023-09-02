package com.tabi.dmv_tv.network;

import com.tabi.dmv_tv.models.ApiResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkApiClient {
    private static final String BASE_URL = "Your Base URL";
    private static NetworkApiClient instance;

    private ApiRequest apiRequest = null;

    private NetworkApiClient() {

    }

    public static NetworkApiClient getInstance() {
        if (instance == null) {
            instance = new NetworkApiClient();
        }
        return instance;
    }

    private ApiRequest getRetrofitClient() {
        if (apiRequest == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            apiRequest = retrofit.create(ApiRequest.class);
        }
        return apiRequest;
    }

    public Call<ApiResponse> getVideos() {
        return getRetrofitClient().getVideos();
    }
}
