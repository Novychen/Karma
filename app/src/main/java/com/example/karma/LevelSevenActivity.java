package com.example.karma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class LevelSevenActivity extends Activity implements View.OnTouchListener, Riddle{

    final static String TAG = "at.fhooe.mc.karma LevelSevenActivity";

    float mStartX;
    float mStartY;
    float mEndX;
    float mEndY;
    float mAngle;
    private View mCircleBackground;
    private Activity mActivity = this;

    ImageView mSafeLockNumbers;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_level_seven);
        Log.i(TAG,"LEVEL SEVEN");
        mCircleBackground = findViewById(R.id.circleActivity_7);
        mCircleBackground.setVisibility(View.VISIBLE);

        mCircleBackground.post(new Runnable() {
            @Override
            public void run() {
                Animation.circularReveal(mActivity,mCircleBackground); }
        });

        mSafeLockNumbers = findViewById(R.id.activity_level_2_lock_num);
        ConstraintLayout constraintLayout = findViewById(R.id.circleActivity_7);
        constraintLayout.setOnTouchListener(this);

        hideStatusBar();
    }


    private void hideStatusBar(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void rotateLock(){

        float width = Smartphone.getInstance(this).getWidthInPixels();
        float height = Smartphone.getInstance(this).getHeightInPixels();

        // Coordinate of "A"
        float aX = width/2;
        float aY = height/2;

        // Coordinate of "B"
        float bX = mEndX;
        float bY = mEndY;

        // Coordinate of "C"
        float cX = mStartX;
        float cY = mStartY;

        // "A" to "C" - b
        double b = Math.sqrt( Math.pow(aY - cY,2) +  Math.pow(cX - aX,2));

        // "A" to "B" - mConstraintLayout
        double c = Math.sqrt( Math.pow(aY - bY,2) +  Math.pow(bX - aX,2));

        // "B" to "C" - a
        double a = Math.sqrt( Math.pow(bY - cY,2) +  Math.pow(bX - cX,2));

        double powB = Math.pow(b,2);
        double powC = Math.pow(c,2);
        double powA = Math.pow(a,2);
        double value = ((powB + powC) - powA) / (2 * b * c);

        if(mStartY > mEndY){
            mAngle = mAngle - ((float) Math.acos(value) * 30);
        } else {
            mAngle = mAngle + ((float) Math.acos(value) * 30);
        }
        Log.i(TAG + "\n", "\n" + "----------------------------------MATH---------------------------------- " + "\n" +
                "WIDTH: " + width + "     HEIGHT: " + height + "\n" +
                "AX: " + aX +  "     AY: " + aY + "\n" +
                "BX: " + bX +  "     BY: " + bY + "\n" +
                "CX: " + cX +  "     CY: " + cY + "\n" +
                "a: " + a + "     b: " + b +  "     mConstraintLayout: " + c + "\n" +
                "Value: " + value + "     Angle: " + mAngle + "     Number: " + mAngle / 360);

        mSafeLockNumbers.animate().rotation(mAngle).setDuration(1000);
        mSafeLockNumbers.animate().start();
        //mSafeLockNumbers.setRotation(mAngle);
    }

    @Override
    public boolean onTouch(View _v, MotionEvent _event) {
        if(_event.getAction() == MotionEvent.ACTION_DOWN){
            mStartX = _event.getX();
            mStartY = _event.getY();

            Log.i(TAG, "ACTION DOWN:  X " + mStartX + "   Y " + mStartY);

        }
        if(_event.getAction() == MotionEvent.ACTION_MOVE){
            mEndX = _event.getX();
            mEndY = _event.getY();
            rotateLock();
            Log.i(TAG, "ACTION MOVE");

        }
        if(_event.getAction() == MotionEvent.ACTION_UP){

            Log.i(TAG, "ACTION UP:  X " + mEndX + "   Y " + mEndY);
        }
        return true;
    }

    @Override
    public void nextActivity() {
        SharedPreferences sharedPref = this.getSharedPreferences("at.fhooe.mc.karma", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("color", mColor[7]);
        editor.apply();
        Intent i = new Intent(this, ComingSoon.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }
}
