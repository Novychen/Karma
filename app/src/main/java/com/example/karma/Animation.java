package com.example.karma;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * Class that provides all animations, that are used in every level
 */
public class Animation {

    /**
     * Creates the circular reveal animation in the color of the box that was clicked.
     * It knows the color through sharedPreferences
     * @param _activity the activity that will be started an which background will be edited
     * @param _circleBackground the layout of the given activity, which color will be edited
     */
    protected static void circularReveal(Activity _activity, View _circleBackground) {
        SharedPreferences sharedPref = _activity.getSharedPreferences("at.fhooe.mc.karma", Context.MODE_PRIVATE);
        int bx = sharedPref.getInt("x-Co",0);
        int by = sharedPref.getInt("y-Co",0);
        String colorString = sharedPref.getString("color",null);

        if(colorString.equals("#C51262")) {
            _circleBackground.setBackgroundColor(Color.BLACK);
        } else {
            int color = Color.parseColor(colorString);
            _circleBackground.setBackgroundColor(color);
        }

        float radius = Math.max(_circleBackground.getWidth(), _circleBackground.getHeight());

        _circleBackground.setVisibility(View.VISIBLE);
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(_circleBackground,bx,by,0,radius);
        circularReveal.setDuration(1000);
        circularReveal.start();
    }
}
