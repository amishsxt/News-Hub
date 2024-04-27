package com.example.newshub.data.remote;

import android.util.Log;

import com.example.newshub.data.model.Article;
import com.example.newshub.data.model.ArticleResponse;
import com.example.newshub.utils.DataSourceCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Arrays;
import java.util.List;

public class NewsApiDataSource implements NewsRemoteDataSource {

    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static final String API_KEY = "01c59c66f2e845b9aa827924950d4f90";
    private static final String COUNTRY_CODE = "in";

    private NewsApiService newsApiService;

    public NewsApiDataSource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        newsApiService = retrofit.create(NewsApiService.class);
    }

    @Override
    public void getTopHeadlines(DataSourceCallback<List<Article>> callback) {
        Call<ArticleResponse> call = newsApiService.getTopHeadlines(COUNTRY_CODE, API_KEY);

        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body().getArticles());
                    Log.d("Response", response.body().getStatus());
                } else {
                    callback.onError("Failed to fetch top headlines");
                }
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                callback.onError(t.getMessage());
                Log.d("Call Failure", t.getMessage());
            }
        });
    }

}

