package com.example.karma;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.hardware.Sensor.TYPE_LIGHT;


/**
 * Put to sleep
 */
public class LevelThreeFragment extends Fragment implements SensorEventListener {

    final static String TAG = "at.fhooe.mc.karma LevelThreeFragment";

    private SensorManager mSensorManager;
    private Sensor mLight;

    public LevelThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
                             Bundle _savedInstanceState) {
        View view = _inflater.inflate(R.layout.fragment_level_three, _container, false);

        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mLight = mSensorManager.getDefaultSensor(TYPE_LIGHT);

        return view;
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
            if(_event.values[0] < 20){
                LevelCompleteDialog d = new LevelCompleteDialog(getActivity());
                d.show();
                mSensorManager.unregisterListener(this);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
