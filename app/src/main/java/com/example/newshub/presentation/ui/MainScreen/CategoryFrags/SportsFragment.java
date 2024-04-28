package com.example.newshub.presentation.ui.MainScreen.CategoryFrags;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.newshub.R;
import com.example.newshub.databinding.FragmentSportsBinding;


public class SportsFragment extends Fragment {

    private FragmentSportsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSportsBinding.bind(inflater.inflate(R.layout.fragment_sports, container, false));
        return binding.getRoot();
    }
}