package com.example.newshub.presentation.ui.MainScreen.CategoryFrags;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.newshub.R;
import com.example.newshub.data.model.Article;
import com.example.newshub.databinding.FragmentSportsBinding;
import com.example.newshub.presentation.adapter.NewsAdapter;
import com.example.newshub.presentation.ui.NewsScreen.NewsActivity;
import com.example.newshub.presentation.viewModels.NewsViewModel;

import java.util.ArrayList;
import java.util.List;


public class SportsFragment extends Fragment {

    private FragmentSportsBinding binding;
    private NewsViewModel newsViewModel;
    private NewsAdapter newsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSportsBinding.bind(inflater.inflate(R.layout.fragment_sports, container, false));

        // Initialize ViewModel
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        // Init RecyclerView and its adapter
        newsAdapter = new NewsAdapter(getContext(), new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(newsAdapter);

        // Fetch sports headlines
        newsViewModel.fetchCategoryHeadlines("sports");

        newsViewModel.getCategoryNewsArticles().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                updateNewsArticles(articles);
            }
        });

        //on article clicked
        newsAdapter.setOnItemClickListener(article -> {
            Intent intent = new Intent(getActivity(), NewsActivity.class);
            intent.putExtra("articleObject", article);
            startActivity(intent);
        });

        return binding.getRoot();
    }

    private void updateNewsArticles(List<Article> newArticles) {
        newsAdapter.updateArticles(newArticles);
        newsAdapter.notifyDataSetChanged();
    }
}