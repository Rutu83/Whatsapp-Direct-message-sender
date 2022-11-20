package com.is.whatsappdirectmessaagesender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    ImageView ivAppIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ivAppIcon = findViewById(R.id.iv_app_icon);

        Animation scaleAnim = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.scale_anim);
        ivAppIcon.startAnimation(scaleAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }
}