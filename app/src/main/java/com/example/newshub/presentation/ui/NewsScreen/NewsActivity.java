package com.example.newshub.presentation.ui.NewsScreen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.newshub.R;
import com.example.newshub.data.model.Article;
import com.example.newshub.databinding.ActivityNewsBinding;
import com.example.newshub.presentation.utils.DateFormatter;
import com.example.newshub.presentation.viewModels.LocalDataViewModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class NewsActivity extends AppCompatActivity{

    private ActivityNewsBinding binding;
    private Article article;

    private LocalDataViewModel localDataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //getting intent
        article = (Article) getIntent().getSerializableExtra("articleObject");

        localDataViewModel = new ViewModelProvider(this).get(LocalDataViewModel.class);

        setData();

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.readMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWebSource();
            }
        });

        binding.shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share();
            }
        });

        binding.bookmarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                localDataViewModel.saveArticle(article);
            }
        });
    }

    private void openWebSource() {
        if (article.getUrl() != null) {
            Log.d("sourceUrl", article.getUrl());

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
            startActivity(intent);
        } else {
            Log.d("sourceUrl", "is null");
            Toast.makeText(this, "Source Unknown", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){

        binding.title.setText(article.getTitle());
        binding.publishDate.setText(DateFormatter.formatDate(article.getPublishedAt()));
        binding.description.setText(article.getDescription());
        binding.source.setText(article.getSource().getName());
        binding.content.setText(article.getContent());

        if(article.getUrlToImage()!=null){
            setPicture(binding.articleImg, Uri.parse(article.getUrlToImage()))  ;
        }
    }

    private void setPicture(ImageView imageView, Uri uri){

        showImgProgressBar();

        Picasso.get()
                .load(uri )
                .error(R.drawable.news_default)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        hideImgProgressBar();
                    }

                    @Override
                    public void onError(Exception e) {
                        hideImgProgressBar();
                    }
                });
    }

    private void showImgProgressBar(){
        binding.articleImg.setVisibility(View.INVISIBLE);
        binding.imgProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideImgProgressBar(){
        binding.articleImg.setVisibility(View.VISIBLE);
        binding.imgProgressBar.setVisibility(View.GONE);
    }

    private void share(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, article.getUrl());

        startActivity(Intent.createChooser(shareIntent, "Share with"));
    }
}