package com.example.karma;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

public class LevelEightActivity extends Activity implements Riddle{


    final int MAX_DISTANCE_OF_Y = 250;
    final static String TAG = "at.fhooe.mc.karma LevelEightActivity";
    private long mTimeStart = 0;
    private long mTimeEnd = 0;
    private int mRating;

    private View mCircleBackground;
    private ConstraintLayout mConstraintLayout;
    private Activity mActivity = this;
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_eight);

        mGestureDetector = new GestureDetector(this,new MyGestureListener());
        mCircleBackground = findViewById(R.id.circleActivity_8);
        mCircleBackground.setVisibility(View.INVISIBLE);

        mCircleBackground.post(new Runnable() {
            @Override
            public void run() {
                Animation.circularReveal(mActivity,mCircleBackground);            }
        });

        mTimeStart = System.currentTimeMillis();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void nextActivity() {

    }

    @Override
    public long getTime() {
        return mTimeEnd - mTimeStart;
    }

    @Override
    public void setRating(int _rate) {
        mRating = _rate;
    }

    @Override
    public int getRating() {
        return mRating;
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent _e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent _event1, MotionEvent _event2, float velocityX, float velocityY) {

            float x1 = _event1.getX();
            float y1 = _event1.getY();
            float x2 = _event2.getX();
            float y2 = _event2.getY();

            if(Math.abs(x1 - x2) < MAX_DISTANCE_OF_Y && y1 > y2){
                Log.i(TAG,"SWIPED UP");
            }
            mTimeEnd = System.currentTimeMillis();
            return true;
        }
    }
}


