package com.example.karma;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * pusteblume
 */
public class LevelFiveActivity extends Activity implements Riddle, View.OnTouchListener {
    final static String TAG = "at.fhooe.mc.karma LevelFourActivity";
    private View mCircleBackground;
    private Activity mActivity = this;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_level_five);
        mCircleBackground = findViewById(R.id.circleActivity_5);
        mCircleBackground.setVisibility(View.INVISIBLE);
        Log.i(TAG,"LEVEL FIVE");
        mCircleBackground.post(new Runnable() {
            @Override
            public void run() {
                Animation.circularReveal(mActivity,mCircleBackground);
            }
        });

        MediaRecorder mRecorder;
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.UNPROCESSED);

        mRecorder.start();
        int volume = mRecorder.getMaxAmplitude();
        Log.i(TAG, "Volume : " + volume);
        /*AudioManager audioManager;*/
        mCircleBackground.setOnTouchListener(this);
    }

    @Override
    public void nextActivity() {
        SharedPreferences sharedPref = this.getSharedPreferences("at.fhooe.mc.karma", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("color", mColor[5]);
        editor.apply();

        Intent i = new Intent(this, LevelSixActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }

    private void animateFlower(ImageView _flower){
        Drawable d = getDrawable(R.drawable.anim_flower);

        if(d instanceof AnimatedVectorDrawableCompat){
            AnimatedVectorDrawableCompat avd = (AnimatedVectorDrawableCompat) d;
            _flower.setImageDrawable(avd);
            avd.start();

        } else if (d instanceof AnimatedVectorDrawable){
            AnimatedVectorDrawable avd = (AnimatedVectorDrawable) d;
            _flower.setImageDrawable(avd);
            avd.start();
        }
    }

    @Override
    public boolean onTouch(View _v, MotionEvent _event) {
        if(_event.getAction() == MotionEvent.ACTION_DOWN){
            ImageView img = _v.findViewById(R.id.level_five_flower);
            animateFlower(img);

        }
        return false;
    }
}
