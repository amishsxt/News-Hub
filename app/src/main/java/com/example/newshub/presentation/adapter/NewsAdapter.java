package com.example.newshub.presentation.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newshub.R;
import com.example.newshub.data.model.Article;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private Context context;
    private List<Article> articleList;
    private NewsAdapterListener listener;

    public NewsAdapter() {
    }

    public NewsAdapter(Context context, List<Article> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.title.setText(article.getTitle());
        holder.source.setText(article.getSource().getName());

        // Set image using Picasso
        if (article.getUrlToImage() != null) {
            holder.setPicture(holder.imageView, Uri.parse(article.getUrlToImage()));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(article);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }


    public interface NewsAdapterListener{
        public void onItemClick(Article article);
    }

    public void setOnItemClickListener(NewsAdapterListener listener){
        this.listener = listener;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        private TextView title, source;
        private ImageView imageView;
        private ProgressBar progressBar;


        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            source = itemView.findViewById(R.id.source);
            imageView = itemView.findViewById(R.id.newsImg);
            progressBar = itemView.findViewById(R.id.imgProgressBar);
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
            imageView.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }

        private void hideImgProgressBar(){
            imageView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    public void updateArticles(List<Article> articleList){
        this.articleList = articleList;
    }
}


