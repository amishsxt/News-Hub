package com.example.newshub.domain.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.newshub.data.local.NewsLocalDataSource;
import com.example.newshub.data.local.entities.NewsArticle;

import java.util.List;

public class LocalDataRepository {

    private NewsLocalDataSource localDataSource;

    public LocalDataRepository(Application application) {
        this.localDataSource = new NewsLocalDataSource(application);
    }

    public LiveData<List<NewsArticle>> getAllArticles() {
        return localDataSource.getAllArticles();
    }

    public void saveArticle(NewsArticle article) {
        localDataSource.insertArticle(article);
    }

    public void deleteArticle(String title) {
        localDataSource.deleteArticle(title);
    }

    public boolean isThere(String title){
        return localDataSource.isThere(title);
    }

}
