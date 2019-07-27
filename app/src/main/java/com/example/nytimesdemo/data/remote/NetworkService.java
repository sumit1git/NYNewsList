package com.example.nytimesdemo.data.remote;

import com.example.nytimesdemo.data.remote.response.PopularNewsResponseModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkService {

    @GET("7.json?api-key=GPYviEgXDgo1g6uZVTYtfDMBtVRfFEGt")
    Call<PopularNewsResponseModel> getPopularNewsList();
}
