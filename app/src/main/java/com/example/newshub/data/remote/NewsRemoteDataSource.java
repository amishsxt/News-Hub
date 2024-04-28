package com.example.newshub.data.remote;

import com.example.newshub.data.model.Article;
import com.example.newshub.utils.DataSourceCallback;

import java.util.List;

public interface NewsRemoteDataSource {

    void getTopHeadlines(DataSourceCallback<List<Article>> callback);

    void getCategoryHeadlines(String category, DataSourceCallback callback);
}