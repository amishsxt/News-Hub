package com.example.newshub.presentation.viewModels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.newshub.data.local.entities.NewsArticle;
import com.example.newshub.data.model.Article;
import com.example.newshub.domain.repository.LocalDataRepository;

import java.util.List;

public class LocalDataViewModel extends AndroidViewModel {

    private Context context;
    private LocalDataRepository localDataRepository;

    public LocalDataViewModel(@NonNull Application application) {
        super(application);
        localDataRepository = new LocalDataRepository(application);
    }

    public LiveData<List<NewsArticle>> getAllArticles() {
        return localDataRepository.getAllArticles();
    }

    public void saveArticle(Article article) {
        NewsArticle newsArticle = convert(article);
        localDataRepository.saveArticle(newsArticle);
    }

    public void deleteArticle(String title) {
        localDataRepository.deleteArticle(title);
    }

    public boolean isThere(String title){
        return localDataRepository.isThere(title);
    }

    private NewsArticle convert(Article article){
        NewsArticle newsArticle = new NewsArticle(
                article.getSource().getId(),
                article.getSource().getName(),
                article.getTitle(),
                article.getDescription(),
                article.getContent(),
                article.getAuthor(),
                article.getUrl(),
                article.getUrlToImage(),
                article.getPublishedAt()
        );

        return newsArticle;
    }
}
