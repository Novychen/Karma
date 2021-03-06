package com.example.karma;

import androidx.appcompat.app.AppCompatActivity;

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
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**class of the Level RotatingHello*/
public class LevelOneActivity extends AppCompatActivity implements SensorEventListener, Riddle{
    private AnimatorSet mAnimatorSet_01;
    private AnimatorSet mAnimatorSet_02;
    private AnimatorSet mAnimatorSet_03;
    private AnimatorSet mAnimatorSet_04;
    private int mCount;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private View mCircleBackground;
    private Activity mActivity = this;

    private long mTimeStart = 0;
    private long mTimeEnd = 0;

    private int mRating = 0;
    final static String TAG = "at.fhooe.mc.karma HelloActivity";

    /** sets the variables of this level, calls the method hideStatusBar()
     * @param _savedInstanceState*/
    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);

        setContentView(R.layout.activity_level_one);
        mCircleBackground = findViewById(R.id.circleActivity_1);
        mCircleBackground.setVisibility(View.INVISIBLE);

        mCircleBackground.post(new Runnable() {
            @Override
            public void run() {
                Animation.circularReveal(mActivity,mCircleBackground); }
        });
        mCount = 0;
        mSensorManager= (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        TextView hello = findViewById(R.id.activity_level_1_hello);
        animate(hello);

        mTimeStart = System.currentTimeMillis();

        hideStatusBar();
    }

    /**hides StatusBar in the Activity*/
    private void hideStatusBar(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    /**animates the rotation of the Text hello
     * @param _hello TextView of hello */
    private void animate (TextView _hello){

        Smartphone smartphone = Smartphone.getInstance(this);

        float height = smartphone.getHeightInPixels();
        float width = smartphone.getWidthInPixels();

        _hello.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        float widthText = _hello.getMeasuredWidth();
        float heightText = _hello.getMeasuredWidth();

        Log.i(TAG, "Device Height: " + height + "\n" +
                "Device Width: " + width + "\n" +
                "View Height: " + _hello.getHeight() + "\n" +
                "View Width: " + _hello.getMeasuredWidth() + "\n");

        // Moving Textview "Hello" to the top with a rotation
        ObjectAnimator oA_01 = ObjectAnimator.ofFloat(_hello, "translationX", width / 2 - widthText / 2);
        ObjectAnimator oA_02 = ObjectAnimator.ofFloat(_hello, "translationY", (-height / 2 + heightText / 2));
        ObjectAnimator oA_03 = ObjectAnimator.ofFloat(_hello, "rotation", 180);

        mAnimatorSet_01 = new AnimatorSet();
        mAnimatorSet_01.playTogether(oA_01, oA_02, oA_03);
        mAnimatorSet_01.setDuration(500);

        // Moving TextView "Hello" to the right with a rotation
        ObjectAnimator oA_05 = ObjectAnimator.ofFloat(_hello, "rotation", 270);
        ObjectAnimator oA_06 = ObjectAnimator.ofFloat(_hello, "translationX", (width - widthText));
        ObjectAnimator oA_07 = ObjectAnimator.ofFloat(_hello, "translationY", (0));

        mAnimatorSet_02 = new AnimatorSet();
        mAnimatorSet_02.playTogether(oA_05, oA_06, oA_07);
        mAnimatorSet_02.setDuration(500);

        // Moving TextView "Hello" to the Bottom with a rotation
        ObjectAnimator oA_08 = ObjectAnimator.ofFloat(_hello, "rotation", 360);
        ObjectAnimator oA_09 = ObjectAnimator.ofFloat(_hello, "translationX", width / 2 - widthText / 2);
        ObjectAnimator oA_10 = ObjectAnimator.ofFloat(_hello, "translationY", (height / 2));

        mAnimatorSet_03 = new AnimatorSet();
        mAnimatorSet_03.playTogether(oA_08, oA_09, oA_10);
        mAnimatorSet_03.setDuration(500);

        // Moving TextView "Hello" to the center, rotate it and make it bigger
        ObjectAnimator oA_11 = ObjectAnimator.ofFloat(_hello, "rotation", 0);
        ObjectAnimator oA_12 = ObjectAnimator.ofFloat(_hello, "translationX", width / 2 - widthText / 2);
        ObjectAnimator oA_13 = ObjectAnimator.ofFloat(_hello, "translationY", 0);
        ObjectAnimator oA_14 = ObjectAnimator.ofFloat(_hello, "scaleX", 1.5f);
        ObjectAnimator oA_15 = ObjectAnimator.ofFloat(_hello, "scaleY", 1.5f);

        mAnimatorSet_04 = new AnimatorSet();
        mAnimatorSet_04.playTogether(oA_11, oA_12, oA_13, oA_14, oA_15);
        mAnimatorSet_04.setDuration(500);
    }

    /** controls the Accelerometer-Sensor and starts the animation of the
     * text hello if the right values are return from the Sensor
     * @param _event*/
    @Override
    public void onSensorChanged(SensorEvent _event) {

        double max = 8;
        double min = -8;

        if(_event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            Log.i(TAG, "LevelOneActivity : " + _event.values[0] +" :"+ _event.values[1] +" : Count" + mCount);
            if (_event.values[0] > max && _event.values[1] > 0 &&  mCount == 0){
                mCount = 1;
                mAnimatorSet_01.start();

            }
            if (_event.values[0] < 0 && _event.values[1] < min && mCount == 1){
                mCount = 2;
                mAnimatorSet_02.start();

            }
            if (_event.values[0] < min && _event.values[1] < 0 && mCount == 2){
                mCount = 3;
                mAnimatorSet_03.start();

            }
            if (_event.values[0] < 0 && _event.values[1] > max && mCount == 3){
                mCount = 4;
                mAnimatorSet_04.start();
            }
            if(mCount == 4){
                mCount = 0;
                CountDownTimer countDownTimer = new CountDownTimer(1000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        mTimeEnd = System.currentTimeMillis();
                        dialog();
                    }
                };
                countDownTimer.start();


            }
        }
    }
    /**activates the class LevelCompleteDialog*/
    public void dialog(){
        LevelCompleteDialog d = new LevelCompleteDialog(this,1);
        d.show();
    }



    @Override
    public void onAccuracyChanged(Sensor _sensor, int _accuracy) {

    }
    @Override
    public void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mSensorManager.registerListener(this,mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void nextActivity() {
        mSensorManager.unregisterListener(this);
        SharedPreferences sharedPref = this.getSharedPreferences("at.fhooe.mc.karma", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("color", mColor[1]);
        editor.putInt("level",2);
        editor.apply();
        Intent i = new Intent(this, LevelTwoActivity.class);
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
        mRating = _rate;
    }
    @Override
    public int getRating() {
        return mRating;
    }
}
