package com.example.karma;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import static android.hardware.Sensor.TYPE_LIGHT;

public class LevelThreeActivity extends Activity implements SensorEventListener,Riddle {

    final static String TAG = "at.fhooe.mc.karma LevelThreeActivity";

    private View mCircleBackground;
    private Activity mActivity = this;

    private ImageView mLidTop;
    private ImageView mLidBottom;

    private SensorEventListener mListener;
    private SensorManager mSensorManager;
    private Sensor mLight;
    private boolean mDialog;
    private boolean mAnimation;

    private long mTimeStart = 0;
    private long mTimeEnd = 0;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_level_three);
        mCircleBackground = findViewById(R.id.circleActivity_3);
        mCircleBackground.setVisibility(View.INVISIBLE);

        mCircleBackground.post(new Runnable() {
            @Override
            public void run() {
                Animation.circularReveal(mActivity,mCircleBackground);            }
        });

        mSensorManager= (SensorManager)getSystemService(SENSOR_SERVICE);
        mLight = mSensorManager.getDefaultSensor(TYPE_LIGHT);
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);

        hideStatusBar();

        mLidTop = findViewById(R.id.level_three_lid_01);
        mLidBottom = findViewById(R.id.level_three_lid_02);

    }

    private void hideStatusBar(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
        mTimeStart = System.currentTimeMillis();
    }

    @Override
    public void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mSensorManager.registerListener(this,mLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent _event) {
        if(_event.sensor.getType() == TYPE_LIGHT){
            if(_event.values[0] < 5){

                CountDownTimer countDownTimer = new CountDownTimer(3000, 500) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                        if(millisUntilFinished <= 2500){
                            if(!mAnimation){
                                float move = mLidBottom.getHeight()/2;

                                ObjectAnimator oA_01 = ObjectAnimator.ofFloat(mLidTop, "translationY", move);
                                ObjectAnimator oA_02 = ObjectAnimator.ofFloat(mLidBottom, "translationY", -move);

                                AnimatorSet animatorSet = new AnimatorSet();
                                animatorSet.playTogether(oA_01, oA_02);
                                animatorSet.setDuration(2450);
                                animatorSet.start();
                                mAnimation = true;
                            }
                        }

                    }

                    @Override
                    public void onFinish() {
                        mTimeEnd = System.currentTimeMillis();
                        if(!mDialog) {
                            CountDownTimer countDownTimer = new CountDownTimer(1000,1000){
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
                    }
                };
                countDownTimer.start();

            }
        }
    }
    public void dialog(){
        mSensorManager.unregisterListener(mListener);
        LevelCompleteDialog d = new LevelCompleteDialog(mActivity);
        d.show();
        mDialog = true;
    }

    @Override
    public void onAccuracyChanged(Sensor _sensor, int _accuracy) {

    }

    @Override
    public void nextActivity() {
        SharedPreferences sharedPref = this.getSharedPreferences("at.fhooe.mc.karma", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("color", mColor[3]);
        editor.apply();
        Intent i = new Intent(this, LevelFourActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }

    @Override
    public long getTime() {
        return mTimeEnd - mTimeStart;
    }


    @Override
    public void setRating(int _rate) {
        mRating[2] = _rate;
    }
    @Override
    public int getRating() {
        return mRating[2];
    }
}
