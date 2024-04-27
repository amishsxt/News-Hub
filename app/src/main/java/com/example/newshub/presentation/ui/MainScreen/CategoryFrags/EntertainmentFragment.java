package com.example.newshub.presentation.ui.MainScreen.CategoryFrags;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newshub.R;
import com.example.newshub.databinding.FragmentEntertainmentBinding;


public class EntertainmentFragment extends Fragment {

    private FragmentEntertainmentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentEntertainmentBinding.bind(inflater.inflate(R.layout.fragment_entertainment, container, false));
        return binding.getRoot();
    }
}