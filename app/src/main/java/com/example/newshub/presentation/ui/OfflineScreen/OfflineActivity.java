package com.example.newshub.presentation.ui.OfflineScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.newshub.data.local.entities.NewsArticle;
import com.example.newshub.data.model.Article;
import com.example.newshub.data.model.Source;
import com.example.newshub.databinding.ActivityOfflineBinding;
import com.example.newshub.presentation.adapter.LocalNewsAdapter;
import com.example.newshub.presentation.ui.NewsScreen.NewsActivity;
import com.example.newshub.presentation.viewModels.LocalDataViewModel;

import java.util.ArrayList;
import java.util.List;

public class OfflineActivity extends AppCompatActivity {

    private ActivityOfflineBinding binding;
    private LocalNewsAdapter localNewsAdapter;
    private LocalDataViewModel localDataViewModel;

    private List<NewsArticle> articleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOfflineBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize ViewModel
        localDataViewModel = new ViewModelProvider(this).get(LocalDataViewModel.class);

        // Init RecyclerView and its adapter
        localNewsAdapter = new LocalNewsAdapter(getApplicationContext(), new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.recyclerView.setAdapter(localNewsAdapter);

        // Fetch local articles

        localDataViewModel.getAllArticles().observe(this, new Observer<List<NewsArticle>>() {
            @Override
            public void onChanged(List<NewsArticle> newsArticles) {
                articleList = newsArticles;
                hideProgressBar();
                updateNewsArticles(newsArticles);
            }
        });


        //on local article clicked
        localNewsAdapter.setOnItemClickListener(article -> {

            Article article1 = new Article(
                    new Source(article.getSourceId(), article.getSourceName()),
                    article.getTitle(),
                    article.getDescription(),
                    article.getContent(),
                    article.getAuthor(),
                    article.getUrl(),
                    article.getUrlToImage(),
                    article.getPublishedAt()
            );


            Intent intent = new Intent(OfflineActivity.this, NewsActivity.class);
            intent.putExtra("articleObject", article1);
            startActivity(intent);
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void updateNewsArticles(List<NewsArticle> newArticles) {
        localNewsAdapter.updateArticles(newArticles);
        localNewsAdapter.notifyDataSetChanged();
    }

    private void showProgressBar(){
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.recyclerView.setVisibility(View.INVISIBLE);
    }

    private void hideProgressBar(){
        binding.recyclerView.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
    }
}