package com.example.newshub.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.newshub.data.local.entities.NewsArticle;

import java.util.List;

@Dao
public interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(NewsArticle article);

    @Query("DELETE FROM news_articles WHERE title = :articleTitle")
    void delete(String articleTitle);

    @Query("SELECT * FROM news_articles")
    LiveData<List<NewsArticle>> getAllArticles();

    @Query("SELECT COUNT(*) FROM news_articles WHERE title = :articleTitle")
    int getArticleCount(String articleTitle);
}
