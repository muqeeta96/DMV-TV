package com.tabi.dmv_tv.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.tabi.dmv_tv.R;
import com.tabi.dmv_tv.databinding.ActivitySplashBinding;
import com.tabi.dmv_tv.ui.home.HomeActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends FragmentActivity {
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        openHomeActivityAfterDelay();
    }

    private void openHomeActivityAfterDelay() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        }, 1500);
    }
}