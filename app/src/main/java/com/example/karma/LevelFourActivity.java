package com.example.karma;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class LevelFourActivity extends AppCompatActivity implements Riddle{
    final static String TAG = "at.fhooe.mc.karma LevelFourActivity";
    private View mCircleBackground;
    private Activity mActivity = this;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        Log.i(TAG,"LEVEL FOUR");
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

    @Override
    public void nextActivity() {
        Intent i = new Intent(this, LevelFiveActivity.class);
        startActivity(i);
    }

    @Override
    public void startTimer() {

    }

    @Override
    public void endTimer() {

    }
}
