package com.example.karma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class LevelSevenActivity extends AppCompatActivity implements View.OnTouchListener{
    final static String TAG = "at.fhooe.mc.karma LevelSevenFragment";

    ImageView mSafeLockNumbers;
    float mX;
    float mY;
    float mX2;
    float mY2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_seven);
        mSafeLockNumbers = findViewById(R.id.fragment_level_7_numbers);
        ConstraintLayout constraintLayout = findViewById(R.id.fragment_level_7);
        constraintLayout.setOnTouchListener(this);

    }

    private void rotateLock(ImageView _lockNumbers){

        float width = Smartphone.getInstance(getActivity()).getWidthInPixels();
        float height = Smartphone.getInstance(getActivity()).getHeightInPixels();

        double delta_x = mX - mX2;
        double delta_y = mY - mY2;
        double radians = Math.atan2(delta_y, delta_x);

        double angle =  Math.toDegrees(radians);
        Log.i(TAG, "Angle: " + angle);
        _lockNumbers.animate().rotation((float)angle);
    }

    @Override
    public boolean onTouch(View _v, MotionEvent _event) {
        if(_event.getAction() == MotionEvent.ACTION_DOWN){
            Log.i(TAG, "ACTION DOWN");

            mX = _event.getX();
            mY = _event.getY();

        }
        if(_event.getAction() == MotionEvent.ACTION_MOVE){
            Log.i(TAG, "ACTION MOVE");

        }
        if(_event.getAction() == MotionEvent.ACTION_UP){
            Log.i(TAG, "ACTION UP");

            mY2 = _event.getY();
            mX2 = _event.getX();
            rotateLock(mSafeLockNumbers);
        }
        return false;
    }
}
