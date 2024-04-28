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

    private MutableLiveData<List<Article>> categoryNewsArticles = new MutableLiveData<>();
    private MutableLiveData<String> categoryErrorMessage = new MutableLiveData<>();

    public NewsViewModel() {
        this.newsRepository = new NewsRepository();
    }

    public LiveData<List<Article>> getNewsArticles() {
        return newsArticles;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<List<Article>> getCategoryNewsArticles() {
        return categoryNewsArticles;
    }

    public LiveData<String> getCategoryErrorMessage() {
        return categoryErrorMessage;
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

    public void fetchCategoryHeadlines(String category){
        newsRepository.getCategoryHeadlines(category, new DataSourceCallback<List<Article>>() {
            @Override
            public void onSuccess(List<Article> data) {
                categoryNewsArticles.postValue(data);
            }

            @Override
            public void onError(String errorMessage) {
                NewsViewModel.this.categoryErrorMessage.postValue(errorMessage);
            }
        });
    }
}
