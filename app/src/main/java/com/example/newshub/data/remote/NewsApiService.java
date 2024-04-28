package com.example.newshub.data.remote;

import com.example.newshub.data.model.Article;
import com.example.newshub.data.model.ArticleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {

    //Endpoint to fetch headlines.
    @GET("top-headlines")
    Call<ArticleResponse> getTopHeadlines(
            @Query("country") String country,
            @Query("apiKey") String apiKey

    );

    //Endpoint to fetch category headlines.
    @GET("top-headlines")
    Call<ArticleResponse> getCategoryHeadlines(
            @Query("country") String country,
            @Query("category") String category,
            @Query("apiKey") String apiKey

    );
}
