package com.example.karma;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * pusteblume
 */
public class LevelFiveActivity extends Activity implements Riddle{
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
    }

    @Override
    public void nextActivity() {
        Intent i = new Intent(this, LevelSixActivity.class);
        startActivity(i);
    }
}
