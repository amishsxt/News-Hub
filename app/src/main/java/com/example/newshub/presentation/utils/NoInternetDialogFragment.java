package com.example.newshub.presentation.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

import com.example.newshub.R;
import com.google.android.material.button.MaterialButton;


public class NoInternetDialogFragment extends DialogFragment {

    private final Runnable retryClickListener;

    public NoInternetDialogFragment(Runnable retryClickListener) {
        this.retryClickListener = retryClickListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_no_internet_dialog, container, false);
        MaterialButton retryButton = view.findViewById(R.id.retryBtn);

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retryClickListener.run();
                dismiss();
            }
        });

        return view;
    }
}
