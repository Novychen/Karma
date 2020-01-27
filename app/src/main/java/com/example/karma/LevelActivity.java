package com.example.karma;

import android.animation.Animator;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;


public class LevelActivity extends FragmentActivity {

    final static String TAG = "at.fhooe.mc.karma";
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
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.fragment_container, new HelloFragment());
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();
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

}

