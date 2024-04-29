package com.example.newshub.presentation.ui.OptionScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.newshub.R;
import com.example.newshub.databinding.ActivityOptionBinding;

public class OptionActivity extends AppCompatActivity {

    private ActivityOptionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}