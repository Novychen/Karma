package com.example.karma;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Objects;

public class LevelTwoActivity extends Activity implements View.OnTouchListener{

    final static String TAG = "at.fhooe.mc.karma LevelTwoFragment";
    private int[] mCracks;
    private int mTouch;
    ConstraintLayout mConstraintLayout;
    private View mCircleBackground;
    private Activity mActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_two);
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

    }


    public boolean onTouch(View _v, MotionEvent _event) {

        float x = _event.getRawX();
        float y = _event.getRawY();

        float height = Smartphone.getInstance(this).getHeightInPixels();
        float width = Smartphone.getInstance(this).getWidthInPixels();

        int tap = 50 + (20* mTouch +1);

        if(_event.getAction() == MotionEvent.ACTION_DOWN){

            if (mTouch > mCracks.length) {
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

                    TextView title = _v.findViewById(R.id.level_2_title);
                    title.setVisibility(View.VISIBLE);

                    TextView message = _v.findViewById(R.id.level_2_message);
                    message.setVisibility(View.VISIBLE);
                } else {
                    mConstraintLayout.setBackground(this.getDrawable(mCracks[mTouch]));
                }
                mTouch++;
            }
        }
        return false;
    }
}
