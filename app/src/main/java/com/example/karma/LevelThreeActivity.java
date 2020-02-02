package com.example.karma;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import static android.hardware.Sensor.TYPE_LIGHT;

public class LevelThreeActivity extends Activity implements SensorEventListener,Riddle {

    final static String TAG = "at.fhooe.mc.karma LevelThreeActivity";

    private View mCircleBackground;
    private Activity mActivity = this;

    private SensorManager mSensorManager;
    private Sensor mLight;

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
}
