package com.example.karma;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;


/**
 * Nothing
 */
public class LevelSixActivity extends Activity implements View.OnClickListener, Riddle{

    final static String TAG = "at.fhooe.mc.karma LevelSixActivity";

    private View mCircleBackground;
    private Activity mActivity = this;

    private int mClicked = 0;

    private CountDownTimer mCountdown;
    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_level_six);
        Log.i(TAG,"LEVEL SIX");
        mCircleBackground = findViewById(R.id.circleActivity_6);
        mCircleBackground.setVisibility(View.INVISIBLE);
        Button b = (Button) findViewById(R.id.activity_level_6_button);
        b.setOnClickListener(this);
        mCircleBackground.post(new Runnable() {
            @Override
            public void run() {
                Animation.circularReveal(mActivity,mCircleBackground);            }
        });
        Log.i(TAG, "LevelSixActivity : Timer");
        mCountdown = new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                    Log.i(TAG, "LevelSixActivity : Time remaining : " + millisUntilFinished);
            }

            @Override
            public void onFinish() {
                Done();
            }
        };
        mCountdown.start();
        Log.i(TAG, "LevelSixActivity : Timer START");


    }
    private void Done(){
        LevelCompleteDialog d = new LevelCompleteDialog(this);
        d.show();
    }

    @Override
    public void onClick(View _v) {
        Log.i(TAG,"Is clicked");

        if(_v.getId() == R.id.activity_level_6_button){
            Drawable d;
            if(mClicked % 2 == 0) {
                d = getDrawable(R.drawable.ic_evil_button_pressed);
            } else {
                d = getDrawable(R.drawable.ic_evil_button);
            }
            _v.setBackground(d);
            mClicked++;
        }
        mCountdown.cancel();
        mCountdown.start();
    }

    @Override
    public void nextActivity() {
        SharedPreferences sharedPref = this.getSharedPreferences("at.fhooe.mc.karma", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("color", mColor[6]);
        editor.apply();
        Intent i = new Intent(this, LevelSevenActivity.class);
        startActivity(i);
        finish();
    }
}
