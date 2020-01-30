package com.example.karma;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * pusteblume
 */
public class LevelFiveActivity extends Activity {

    private View mCircleBackground;
    private Activity mActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_five);
        mCircleBackground = findViewById(R.id.circleActivity_5);
        mCircleBackground.setVisibility(View.INVISIBLE);

        mCircleBackground.post(new Runnable() {
            @Override
            public void run() {
                Animation.circularReveal(mActivity,mCircleBackground);            }
        });
    }
}
