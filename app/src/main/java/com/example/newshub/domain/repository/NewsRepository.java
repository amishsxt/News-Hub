package com.example.newshub.domain.repository;

import com.example.newshub.data.model.Article;
import com.example.newshub.data.remote.NewsApiDataSource;
import com.example.newshub.data.remote.NewsRemoteDataSource;
import com.example.newshub.utils.DataSourceCallback;

import java.util.List;


public class NewsRepository implements NewsRemoteDataSource {

    private NewsApiDataSource newsApiDataSource;

    public NewsRepository() {
        this.newsApiDataSource = new NewsApiDataSource();
    }

    @Override
    public void getTopHeadlines(DataSourceCallback<List<Article>> callback) {
        newsApiDataSource.getTopHeadlines(new DataSourceCallback<List<Article>>() {
            @Override
            public void onSuccess(List<Article> data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });

    }

    @Override
    public void getCategoryHeadlines(String category, DataSourceCallback callback) {
        newsApiDataSource.getCategoryHeadlines(category, new DataSourceCallback<List<Article>>() {
            @Override
            public void onSuccess(List<Article> data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }
}




