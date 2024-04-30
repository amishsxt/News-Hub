package com.example.newshub.presentation.ui.SplashScreen;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newshub.R;
import com.example.newshub.databinding.ActivitySplashBinding;
import com.example.newshub.presentation.ui.MainScreen.MainActivity;
import com.example.newshub.presentation.utils.NoInternetDialogFragment;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //root
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Fade-in animation
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeInAnimation.setDuration(800);

        fadeInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if (isConnectedToInternet()) {
                    startNav();
                } else {
                    showNoInternetDialog();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        binding.splashLayout.startAnimation(fadeInAnimation);
    }

    private void startNav(){
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean isConnectedToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        Network network = connectivityManager.getActiveNetwork();
        NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
        return capabilities != null &&
                (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET));
    }

    private void showNoInternetDialog() {
        NoInternetDialogFragment dialogFragment = new NoInternetDialogFragment(new Runnable() {
            @Override
            public void run() {
                if (isConnectedToInternet()) {
                    startNav();
                } else {
                    showNoInternetDialog();
                }
            }
        });
        dialogFragment.show(getSupportFragmentManager(), "NoInternetDialog");
    }

}