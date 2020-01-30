package com.example.karma;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class LevelFourActivity extends AppCompatActivity {

    private View mCircleBackground;
    private Activity mActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Smartphone s = Smartphone.getInstance(this);

        DrawLineWithFinger.width = (int) s.getWidthInPixels();
        DrawLineWithFinger.height = (int) s.getHeightInPixels();
        DrawLineWithFinger.mActivity = this;

        setContentView(R.layout.activity_level_four);

        mCircleBackground = findViewById(R.id.circleActivity_4);
        mCircleBackground.setVisibility(View.INVISIBLE);

        mCircleBackground.post(new Runnable() {
            @Override
            public void run() {
                Animation.circularReveal(mActivity,mCircleBackground);            }
        });
    }
}
