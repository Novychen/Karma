package com.example.karma;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class ComingSoon extends Activity {

    private View mCircleBackground;
    private Activity mActivity = this;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_coming_soon);
        mCircleBackground = findViewById(R.id.circleActivity_soon);
        mCircleBackground.setVisibility(View.INVISIBLE);

        mCircleBackground.post(new Runnable() {
            @Override
            public void run() {
                Animation.circularReveal(mActivity,mCircleBackground);            }
        });
    }
}
