package com.example.karma;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class LevelSixActivity extends Activity {

    private View mCircleBackground;
    private Activity mActivity = this;

public class LevelSixActivity extends Activity implements View.OnClickListener {
    final static String TAG = "at.fhooe.mc.karma LevelSixActivity";

    private CountDownTimer mCountdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_six);
        mCircleBackground = findViewById(R.id.circleActivity_6);
        mCircleBackground.setVisibility(View.INVISIBLE);

        mCircleBackground.post(new Runnable() {
            @Override
            public void run() {
                Animation.circularReveal(mActivity,mCircleBackground);            }
        });
        Log.i(TAG, "LevelSixActivity : Timer");
        Button b = findViewById(R.id.activity_level_6);
        mCountdown = new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                    Log.i(TAG, "LevelSixActivity : Time remaining : " + millisUntilFinished);
            }

            @Override
            public void onFinish() {
                Done();
            }
        };
        mCountdown.start();
        Log.i(TAG, "LevelSixActivity : Timer START");


    }
    private void Done(){
        LevelCompleteDialog d = new LevelCompleteDialog(this);
        d.show();
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG,"Is clicked");
    }
}
