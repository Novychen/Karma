package com.example.karma;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Provides Information about the device the user is currently using
 */
public class Smartphone {

    private static Smartphone mInstance;
    static private Activity mActivity;
    static private float mHeightInPixels;
    static private float mWidthInPixels;

    private Smartphone(){
    }

    /**
     * Gets an instance of Smartphone
     * @param _activity the activity where it is called (as the activities window will be measured)
     * @return a Smartphone object. It creates only one instance of the Smartphone class (Singelton-Pattern)
     */
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


    /**
     * Gets the current height of the activity in pixel
     * @return the height of the activity in pixel
     */
    public float getHeightInPixels() {
        return mHeightInPixels;
    }


    /**
     * Gets the current width of the activity in pixel
     * @return the width of the activity in pixel
     */
    public float getWidthInPixels() {
        return mWidthInPixels;
    }
}

