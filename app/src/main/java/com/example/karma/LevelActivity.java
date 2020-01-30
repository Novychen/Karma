package com.example.karma;

import android.animation.Animator;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;

import static android.hardware.Sensor.TYPE_LIGHT;
import static android.hardware.Sensor.TYPE_ROTATION_VECTOR;


public class LevelActivity extends FragmentActivity implements SensorEventListener {

    final static String TAG = "at.fhooe.mc.karma LevelActivity";
    private View mCircleBackground;



    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_level);
        mCircleBackground = findViewById(R.id.circleActivity);
        mCircleBackground.setVisibility(View.INVISIBLE);

        mCircleBackground.post(new Runnable() {
            @Override
            public void run() {
                circularReveal();            }
        });

        Sensor s = null;
        SensorManager sMgr = (SensorManager)getSystemService(SENSOR_SERVICE);
        s = sMgr.getDefaultSensor(TYPE_LIGHT);
        sMgr.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
        s = sMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sMgr.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
        s = sMgr.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sMgr.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);

        Log.i(TAG, ":: onCreate");
        int value = getIntent().getIntExtra("value",0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor s = null;
        SensorManager sMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        s = sMgr.getDefaultSensor(TYPE_LIGHT);
        sMgr.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
        s = sMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sMgr.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
        s = sMgr.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sMgr.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        SensorManager sMgr = (SensorManager)getSystemService(SENSOR_SERVICE);
        sMgr.unregisterListener(this);
    }

    /**
     * Creates the circular reveal animation in the color of the box that was clicked
     */
    private void circularReveal() {
        SharedPreferences sharedPref = this.getSharedPreferences("at.fhooe.mc.karma",Context.MODE_PRIVATE);
        int bx = sharedPref.getInt("x-Co",0);
        int by = sharedPref.getInt("y-Co",0);
        int color = Color.parseColor(sharedPref.getString("color",null));
        mCircleBackground.setBackgroundColor(color);

        float radius = Math.max(mCircleBackground.getWidth(), mCircleBackground.getHeight());

        mCircleBackground.setVisibility(View.VISIBLE);
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(mCircleBackground,bx,by,0,radius);
        circularReveal.setDuration(1000);
        circularReveal.start();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            Log.i(TAG, ":: Orientation " + (int) event.values[0] + " :" + (int) event.values[1] + " :" + (int) event.values[2]);

        }else if(event.sensor.getType() == TYPE_LIGHT) {
            Log.i(TAG, ":: Light" + event.values[0]);

        }else if(event.sensor.getType() == TYPE_ROTATION_VECTOR) {
            Log.i(TAG, ":: Rotation " + (int) (event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

