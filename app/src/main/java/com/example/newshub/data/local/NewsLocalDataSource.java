package com.example.newshub.data.local;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.newshub.data.local.dao.NewsDao;
import com.example.newshub.data.local.entities.NewsArticle;

import java.util.List;

public class NewsLocalDataSource {

    private Application application;
    private NewsDao newsDao;
    private NewsDatabase newsDatabase;

    public NewsLocalDataSource(Application application) {
        this.application = application;
        newsDatabase = NewsDatabase.getDB(application.getApplicationContext());
    }

    public LiveData<List<NewsArticle>> getAllArticles() {
        return newsDatabase.newsDao().getAllArticles();
    }

    public void insertArticle(NewsArticle article) {
        newsDatabase.newsDao().insert(article);
    }

    public void deleteArticle(String title) {
        newsDatabase.newsDao().delete(title);
    }

    public boolean isThere(String title){
        int a = newsDatabase.newsDao().getArticleCount(title);

        if(a > 0){
            return true;
        }

        return false;
    }
}
