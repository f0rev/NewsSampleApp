package com.feevx.ravan.newssampleapp.api;


import com.feevx.ravan.newssampleapp.api.response.ApiResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsWebservice {

    @GET("/api/news")
    Single<ApiResponse> getAllNews();

    @GET("/api/news/football")
    Single<ApiResponse> getFootballNews();

    @GET("/api/news/basketball")
    Single<ApiResponse> getBasketballNews();

    @GET("/api/news/volleyball")
    Single<ApiResponse> getVolleyballNews();

    @GET("/api/news/hockey")
    Single<ApiResponse> getHockeyNews();
}
