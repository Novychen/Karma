package com.example.karma;
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

    ImageView mSafeLockNumbers;
    float mX;
    float mY;
    float mX2;
    float mY2;

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
