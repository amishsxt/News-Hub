package com.example.newshub.presentation.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newshub.data.model.Article;
import com.example.newshub.data.model.ArticleResponse;
import com.example.newshub.utils.DataSourceCallback;
import com.example.newshub.domain.repository.NewsRepository;

import java.util.List;

public class NewsViewModel extends ViewModel {

    private NewsRepository newsRepository;
    private MutableLiveData<List<Article>> newsArticles = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public NewsViewModel() {
        this.newsRepository = new NewsRepository();
    }

    public LiveData<List<Article>> getNewsArticles() {
        return newsArticles;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void fetchTopHeadlines() {
        newsRepository.getTopHeadlines(new DataSourceCallback<List<Article>>() {
            @Override
            public void onSuccess(List<Article> data) {
                newsArticles.postValue(data);
            }

            @Override
            public void onError(String errorMessage) {
                NewsViewModel.this.errorMessage.postValue(errorMessage);
            }
        });
    }
}
