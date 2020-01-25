package com.example.karma;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Animatable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.widget.Button;

public class LevelActivity extends Activity {

    final static String TAG = "at.fhooe.mc.karma";
    private View mCircleBackground;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_level);

        mCircleBackground = findViewById(R.id.circleActivity);


        if (_savedInstanceState == null) {
            mCircleBackground.setVisibility(View.INVISIBLE);

            ViewTreeObserver viewTreeObserver = mCircleBackground.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        circularReveal();
                        mCircleBackground.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }
        }
    }

    private void circularReveal() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        int b = sharedPref.getInt("level",0);
        if(b == 0){
            Log.i(TAG,"LevelOverviewActivity :: circularReveal :: SharedPrefernce not found");
        }
        Button button = findViewById(b);
        int bx = button.getLeft() + button.getWidth()/2;
        int by = button.getTop() + button.getHeight()/2;

        int x = mCircleBackground.getLeft() + getSpace(bx);
        int y = mCircleBackground.getTop() + getSpace(by);

        float radius = Math.max(mCircleBackground.getWidth(), mCircleBackground.getHeight());

        Animator circularReveal = ViewAnimationUtils.createCircularReveal(mCircleBackground,x,y,0,radius);
        circularReveal.setDuration(2000);
        mCircleBackground.setVisibility(View.VISIBLE);
        circularReveal.start();
    }

    private int getSpace(int size) {
        Resources resources = getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,size,resources.getDisplayMetrics());
    }

}

