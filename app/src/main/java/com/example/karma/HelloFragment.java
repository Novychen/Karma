package com.example.karma;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import static androidx.core.content.ContextCompat.getSystemService;

public class HelloFragment extends Fragment{
    final static String TAG = "at.fhooe.mc.karma";

    ObjectAnimator mAnimator;
    public HelloFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
                             Bundle _savedInstanceState) {

        View view = _inflater.inflate(R.layout.fragment_hello, _container, false);
        TextView hello = view.findViewById(R.id.fragment_level_1_hello);
        mAnimator = ObjectAnimator.ofFloat(hello,"translationX",100f);
        mAnimator.setDuration(2000);

        return view;

    }


}
