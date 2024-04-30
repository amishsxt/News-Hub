package com.example.newshub.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.newshub.data.local.entities.NewsArticle;
import com.example.newshub.data.model.Article;

import java.util.List;

@Dao
public interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NewsArticle article);

    @Delete
    void delete(NewsArticle article);

    @Query("SELECT * FROM news_articles")
    LiveData<List<NewsArticle>> getAllArticles();
}
