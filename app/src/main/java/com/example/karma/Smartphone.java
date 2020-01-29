package com.example.karma;

import android.app.Activity;
import android.util.DisplayMetrics;

public class Smartphone {

    private static Smartphone mInstance;
    static private Activity mActivity;
    static private float mHeightInPixels;
    static private float mWidthInPixels;

    private Smartphone(){
    }

    static Smartphone getInstance(Activity _activity){
        if (mInstance == null) {
            mInstance = new Smartphone();
            DisplayMetrics displaymetrics = new DisplayMetrics();
            mActivity = _activity;
            mActivity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            mHeightInPixels = displaymetrics.heightPixels;
            mWidthInPixels =  displaymetrics.widthPixels;
            return mInstance;
        } else {
            return mInstance;
        }
    }


    public float getHeightInPixels() {
        return mHeightInPixels;
    }

    public float getWidthInPixels() {
        return mWidthInPixels;
    }
}

