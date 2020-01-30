package com.example.karma;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * Schloss knacken
 */
public class LevelSevenFragment extends Fragment implements View.OnTouchListener{

    final static String TAG = "at.fhooe.mc.karma LevelSevenFragment";

    float mStartX;
    float mStartY;
    float mEndX;
    float mEndY;
    float mAngle;

    private ImageView mSafeLockNumbers;

    public LevelSevenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) {
        View view = _inflater.inflate(R.layout.fragment_level_seven, _container, false);
        mSafeLockNumbers = view.findViewById(R.id.fragment_level_7_numbers);
        ConstraintLayout constraintLayout = view.findViewById(R.id.fragment_level_7);
        constraintLayout.setOnTouchListener(this);
        return view;
    }

    private void rotateLock(){

        float width = Smartphone.getInstance(getActivity()).getWidthInPixels();
        float height = Smartphone.getInstance(getActivity()).getHeightInPixels();

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
}
