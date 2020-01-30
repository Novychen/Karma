package com.example.karma;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Objects;

public class LevelTwoActivity extends AppCompatActivity implements View.OnTouchListener{

    final static String TAG = "at.fhooe.mc.karma LevelTwoFragment";
    private int[] mCracks;
    private int mTouch;
    RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_two);

        mCracks = new int[]{R.drawable.ic_crack_1, R.drawable.ic_crack_2, R.drawable.ic_crack_3, R.drawable.ic_crack_4,
                R.drawable.ic_crack_5, R.drawable.ic_crack_6, R.drawable.ic_crack_7, R.drawable.ic_crack_8,
                R.drawable.ic_crack_9, R.drawable.ic_crack_10, R.drawable.ic_crack_11, R.drawable.ic_crack_12,
                R.drawable.ic_crack_13, R.drawable.ic_crack_14, R.drawable.ic_crack_15, R.drawable.ic_crack_16};

        relativeLayout = findViewById(R.id.activity_level_2);
        relativeLayout.setOnTouchListener(this);
        relativeLayout.setBackground(getActivity().getDrawable(R.drawable.ic_crack_0));

    }


    public boolean onTouch(View _v, MotionEvent _event) {

        float x = _event.getRawX();
        float y = _event.getRawY();

        float height = Smartphone.getInstance(getActivity()).getHeightInPixels();
        float width = Smartphone.getInstance(getActivity()).getWidthInPixels();

        int tap = 50 + (20* mTouch +1);

        if(_event.getAction() == MotionEvent.ACTION_DOWN){
            if(x > (width/2 - tap) && x < (width/2 + tap) && y > (height/2 - tap) && y < (height/2 + tap) ) {
                if (mTouch >= mCracks.length) {
                    Drawable d = Objects.requireNonNull(getActivity()).getDrawable(mCracks[mCracks.length - 1]);
                    if (d != null) {
                        d.setAlpha(0);
                        relativeLayout.setBackground(d);
                    }
                    LevelCompleteDialog dialog = new LevelCompleteDialog(getActivity());
                    dialog.show();
                } else {
                    relativeLayout.setBackground(getActivity().getDrawable(mCracks[mTouch]));
                }
                mTouch++;
            }
        }
        return false;
    }
}
