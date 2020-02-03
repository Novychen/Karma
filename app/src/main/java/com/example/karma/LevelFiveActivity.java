package com.example.karma;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

/**
 * pusteblume
 */
public class LevelFiveActivity extends Activity implements Riddle {
    final static String TAG = "at.fhooe.mc.karma LevelFourActivity";
    private View mCircleBackground;
    private Activity mActivity = this;
    private ImageView mFlower;

    Handler mHandler;
    SoundMeter mSensor;
    Runnable mRunnable;
    Runnable mRunnableAmp;
    public static final int RECORD_AUDIO = 0;
    private long mTimeStart = 0;
    private long mTimeEnd = 0;
    private int mRating;


    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_level_five);

        mCircleBackground = findViewById(R.id.circleActivity_5);
        mCircleBackground.setVisibility(View.INVISIBLE);
        mCircleBackground.post(new Runnable() {
            @Override
            public void run() {
                Animation.circularReveal(mActivity, mCircleBackground);
            }
        });

        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO},
                    RECORD_AUDIO);
        }

        setAudio();

        mFlower = findViewById(R.id.level_five_flower);
        setFlower(mFlower);

        hideStatusBar();

        mTimeStart = System.currentTimeMillis();
    }

    private void setAudio(){

        mSensor = new SoundMeter();
        try {
            mSensor.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mHandler = new Handler();

        mRunnable = new Runnable() {
            @Override
            public void run() {
                mRunnableAmp = new Runnable() {
                    @Override
                    public void run() {
                        double volume = mSensor.getAmplitude();
                        Log.i(TAG, "running");
                        if(volume >= 25000){
                            animateFlower(mFlower);
                            solved();
                        }
                        mHandler.postDelayed(this,250);
                    }
                };
                runOnUiThread(mRunnableAmp);
            }
        };

        mHandler.postDelayed(mRunnable,250);
    }

    private void solved(){
        mSensor.stop();
        mHandler.removeCallbacks(mRunnableAmp);
        mHandler.removeCallbacks(mRunnable);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTimeEnd = System.currentTimeMillis();
                CountDownTimer countDownTimer = new CountDownTimer(1000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        dialog();
                    }
                };
                countDownTimer.start();
            }
        },200);

    }

    public void dialog(){
        LevelCompleteDialog d = new LevelCompleteDialog(this);
        d.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setAudio();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensor.stop();
        mHandler.removeCallbacks(mRunnableAmp);
        mHandler.removeCallbacks(mRunnable);
    }

    private void hideStatusBar(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    @Override
    public void nextActivity() {
        SharedPreferences sharedPref = this.getSharedPreferences("at.fhooe.mc.karma", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("color", mColor[5]);
        editor.putInt("level",6);
        editor.apply();

        Intent i = new Intent(this, LevelSixActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }

    public long getTime() {
        return mTimeEnd - mTimeStart;
    }

    @Override
    public void setRating(int _rate) {
        mRating = _rate;
    }
    @Override
    public int getRating() {
        return mRating;
    }


    private void animateFlower(ImageView _flower){
        Drawable d = getDrawable(R.drawable.anim_flower);

        if(d instanceof AnimatedVectorDrawableCompat){
            AnimatedVectorDrawableCompat avd = (AnimatedVectorDrawableCompat) d;
            _flower.setImageDrawable(avd);
            avd.start();

        } else if (d instanceof AnimatedVectorDrawable){
            AnimatedVectorDrawable avd = (AnimatedVectorDrawable) d;
            _flower.setImageDrawable(avd);
            avd.start();
        }
    }


    private void setFlower(ImageView _flower){
        Drawable d = getDrawable(R.drawable.anim_flower);

        if(d instanceof AnimatedVectorDrawableCompat){
            AnimatedVectorDrawableCompat avd = (AnimatedVectorDrawableCompat) d;
            _flower.setImageDrawable(avd);

        } else if (d instanceof AnimatedVectorDrawable){
            AnimatedVectorDrawable avd = (AnimatedVectorDrawable) d;
            _flower.setImageDrawable(avd);
        }
    }
}
