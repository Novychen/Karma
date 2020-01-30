package com.example.karma;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import static android.hardware.Sensor.TYPE_LIGHT;

public class LevelThreeActivity extends Activity implements SensorEventListener {

    final static String TAG = "at.fhooe.mc.karma LevelThreeActivity";

    private View mCircleBackground;
    private Activity mActivity = this;

    private SensorManager mSensorManager;
    private Sensor mLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            if(_event.values[0] < 10){
                LevelCompleteDialog d = new LevelCompleteDialog(this);
                d.show();
                mSensorManager.unregisterListener(this);
            }
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
