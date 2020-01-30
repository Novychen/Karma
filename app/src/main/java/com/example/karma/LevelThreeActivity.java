package com.example.karma;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import static android.hardware.Sensor.TYPE_LIGHT;

public class LevelThreeActivity extends AppCompatActivity implements SensorEventListener {

    final static String TAG = "at.fhooe.mc.karma LevelThreeFragment";

    private SensorManager mSensorManager;
    private Sensor mLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_three);

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
            if(_event.values[0] < 50){
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
