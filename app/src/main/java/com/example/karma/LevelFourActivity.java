package com.example.karma;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class LevelFourActivity extends AppCompatActivity implements Riddle{
    final static String TAG = "at.fhooe.mc.karma LevelFourActivity";
    private View mCircleBackground;
    private Activity mActivity = this;

    private long mTimeStart = 0;
    private long mTimeEnd = 0;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        Log.i(TAG,"LEVEL FOUR");
        Smartphone s = Smartphone.getInstance(this);
        mTimeStart = System.currentTimeMillis();
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
        mTimeEnd = System.currentTimeMillis();

    }

    @Override
    public void nextActivity() {
        SharedPreferences sharedPref = this.getSharedPreferences("at.fhooe.mc.karma", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("color", mColor[4]);
        editor.apply();
        Intent i = new Intent(this, LevelFiveActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }

    @Override
    public long getTime() {
        return mTimeEnd - mTimeStart;
    }


}
