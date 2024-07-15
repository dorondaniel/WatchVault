package com.doron.watchvault.activities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.doron.watchvault.R;


@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity
{
    private static final int SPLASH_DISPLAY_LENGTH = 2500;
    private static final int SPLASH_ANIM = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        ImageView logo = findViewById(R.id.splash);


        ObjectAnimator moveup;
        moveup = ObjectAnimator.ofFloat(logo, "translationY", 0f, -50f);
        ObjectAnimator movedown = ObjectAnimator.ofFloat(logo, "translationY", -50f, 0f);

        moveup.setDuration(SPLASH_ANIM);
        movedown.setDuration(SPLASH_ANIM);

        moveup.setRepeatCount(ObjectAnimator.INFINITE);
        moveup.setRepeatMode(ObjectAnimator.REVERSE);

        movedown.setRepeatCount(ObjectAnimator.INFINITE);
        movedown.setRepeatMode(ObjectAnimator.REVERSE);

        AnimatorSet ans = new AnimatorSet();
        ans.playSequentially(moveup,movedown);

        ans.start();

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            SplashActivity.this.startActivity(intent);
            SplashActivity.this.finish();
        }, SPLASH_DISPLAY_LENGTH);
    }
}
