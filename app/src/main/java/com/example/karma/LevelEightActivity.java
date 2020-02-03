package com.example.karma;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
/**class of the level Candlelight*/
public class LevelEightActivity extends Activity implements Riddle{


    final int MAX_DISTANCE_OF_Y = 250;
    final static String TAG = "at.fhooe.mc.karma LevelEightActivity";
    private long mTimeStart = 0;
    private long mTimeEnd = 0;
    private int mRating;

    private View mCircleBackground;
    private Activity mActivity = this;
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_eight);

        mTimeStart = System.currentTimeMillis();

        mGestureDetector = new GestureDetector(this,new MyGestureListener());
        mCircleBackground = findViewById(R.id.circleActivity_8);
        mCircleBackground.setVisibility(View.INVISIBLE);

        mCircleBackground.post(new Runnable() {
            @Override
            public void run() {
                Animation.circularReveal(mActivity,mCircleBackground);            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void nextActivity() {
        SharedPreferences sharedPref = this.getSharedPreferences("at.fhooe.mc.karma", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("color", mColor[8]);
        editor.apply();
        Intent i = new Intent(this, ComingSoon.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
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

        private void animate(ImageView _img){

            Drawable d = getDrawable(R.drawable.anim_candle);

            if(d instanceof AnimatedVectorDrawableCompat){
                AnimatedVectorDrawableCompat avd = (AnimatedVectorDrawableCompat) d;
                _img.setImageDrawable(avd);
                avd.start();

            } else if (d instanceof AnimatedVectorDrawable){
                AnimatedVectorDrawable avd = (AnimatedVectorDrawable) d;
                _img.setImageDrawable(avd);
                avd.start();
            }
        }

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

            ImageView fire = mActivity.findViewById(R.id.activity_level_8_fire);
            ImageView candle = mActivity.findViewById(R.id.activity_level_8_candle);
            fire.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            float h = Smartphone.getInstance(mActivity).getHeightInPixels();

            float width = Smartphone.getInstance(mActivity).getWidthInPixels()/2;
            float height = h/2.5f;

            float widthFire = fire.getMeasuredWidth()/2;
            float heightFire = fire.getMeasuredWidth();


            if(Math.abs(x1 - x2) < MAX_DISTANCE_OF_Y && y1 > y2){
                if(x1 >= width - widthFire && x1 <= widthFire + width && y1 <= height && y1 >= height - heightFire){
                    fire.setVisibility(View.INVISIBLE);
                    candle.setBackground(mActivity.getDrawable(R.drawable.ic_dummy));
                    animate(candle);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            LevelCompleteDialog d = new LevelCompleteDialog(mActivity,8);
                            mTimeEnd = System.currentTimeMillis();
                            d.show();
                        }
                    },5000);
                }
                Log.i(TAG,"SWIPED UP");
            }
            return true;
        }
    }
}


