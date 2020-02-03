package com.example.karma;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Objects;

public class LevelTwoActivity extends Activity implements View.OnTouchListener,Riddle{

    final static String TAG = "at.fhooe.mc.karma LevelTwoActivity";
    private int[] mCracks;
    private int mTouch;
    ConstraintLayout mConstraintLayout;
    private View mCircleBackground;
    private Activity mActivity = this;

    private long mTimeStart = 0;
    private long mTimeEnd = 0;
    private int mRating = 0;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_level_two);
        Log.i(TAG,"LEVEL TWO");
        mCircleBackground = findViewById(R.id.circleActivity_2);
        mCircleBackground.setVisibility(View.INVISIBLE);

        mCircleBackground.post(new Runnable() {
            @Override
            public void run() {
                Animation.circularReveal(mActivity,mCircleBackground);            }
        });

        mCracks = new int[]{R.drawable.ic_crack_1, R.drawable.ic_crack_2, R.drawable.ic_crack_3, R.drawable.ic_crack_4,
                R.drawable.ic_crack_5, R.drawable.ic_crack_6, R.drawable.ic_crack_7, R.drawable.ic_crack_8,
                R.drawable.ic_crack_9, R.drawable.ic_crack_10, R.drawable.ic_crack_11, R.drawable.ic_crack_12,
                R.drawable.ic_crack_13, R.drawable.ic_crack_14, R.drawable.ic_crack_15, R.drawable.ic_crack_16};

        mConstraintLayout = findViewById(R.id.circleActivity_2);
        mConstraintLayout.setOnTouchListener(this);

        hideStatusBar();
    }

    private void hideStatusBar(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
        mTimeStart = System.currentTimeMillis();

    }

    public boolean onTouch(View _v, MotionEvent _event) {

        float x = _event.getRawX();
        float y = _event.getRawY();

        float height = Smartphone.getInstance(this).getHeightInPixels();
        float width = Smartphone.getInstance(this).getWidthInPixels();

        int tap = 50 + (30* mTouch +1);

        if(_event.getAction() == MotionEvent.ACTION_DOWN){
            Log.i(TAG, "X: " + x + ", Y: " + y);
            Log.i(TAG, x + " > " + (width/2 - tap) +  "  -  " + x  + " < " + width/2 + tap);
            Log.i(TAG, y + " > " + (height/2 - tap) +  "  -  " + y  + " < " + height/2 + tap);
            if (mTouch > mCracks.length) {
                mTimeEnd = System.currentTimeMillis();
                LevelCompleteDialog dialog = new LevelCompleteDialog(this);
                dialog.show();
            } else if(x > (width/2 - tap) && x < (width/2 + tap) && y > (height/2 - tap) && y < (height/2 + tap) ) {
                if (mTouch == mCracks.length) {
                    Drawable d = Objects.requireNonNull(this).getDrawable(mCracks[mCracks.length - 1]);
                    if (d != null) {
                        d.setAlpha(0);
                        mConstraintLayout.setBackground(d);
                    }
                    mConstraintLayout.setBackground(this.getDrawable(R.drawable.ic_crack_17));

                } else {
                    mConstraintLayout.setBackground(this.getDrawable(mCracks[mTouch]));
                }
                mTouch++;
            }
        }
        return false;
    }

    @Override
    public void nextActivity() {
        SharedPreferences sharedPref = this.getSharedPreferences("at.fhooe.mc.karma", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("color", mColor[2]);
        editor.putInt("level",3);
        editor.apply();
        Intent i = new Intent(this, LevelThreeActivity.class);
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
}
