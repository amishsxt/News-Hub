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

    public void deleteArticle(NewsArticle article) {
        newsDatabase.newsDao().delete(article.getTitle());
    }

//    private static class InsertArticleAsyncTask extends AsyncTask<NewsArticle, Void, Void> {
//        private NewsDao newsDao;
//
//        InsertArticleAsyncTask(NewsDao newsDao) {
//            this.newsDao = newsDao;
//        }
//
//        @Override
//        protected Void doInBackground(NewsArticle... articles) {
//            newsDao.insert(articles[0]);
//            return null;
//        }
//    }
//
//    private static class DeleteArticleAsyncTask extends AsyncTask<NewsArticle, Void, Void> {
//        private NewsDao newsDao;
//
//        DeleteArticleAsyncTask(NewsDao newsDao) {
//            this.newsDao = newsDao;
//        }
//
//        @Override
//        protected Void doInBackground(NewsArticle... articles) {
//            newsDao.delete(articles[0]);
//            return null;
//        }
//    }
}
