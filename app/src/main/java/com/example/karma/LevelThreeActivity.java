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
        Sensor s = null;
        SensorManager sMgr = (SensorManager)getSystemService(SENSOR_SERVICE);
        s = sMgr.getDefaultSensor(TYPE_LIGHT);
        sMgr.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
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
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == TYPE_LIGHT) {
            Log.i(TAG, ":: Light" + event.values[0]);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
