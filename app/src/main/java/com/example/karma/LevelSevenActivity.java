package com.example.karma;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
/**class of the level Lock*/
public class LevelSevenActivity extends Activity implements View.OnTouchListener, View.OnClickListener, Riddle{

    final static String TAG = "at.fhooe.mc.karma LevelSevenActivity";
    final int ANIM_TO_CHECK = R.drawable.anim_safelock_to_check;
    final int ANIM_TO_DELETE = R.drawable.anim_safelock_to_delete;
    final int ANIM_FROM_CHECK = R.drawable.anim_safelock_from_check;
    final int ANIM_FROM_DELETE = R.drawable.anim_safelock_from_delete;

    private float mStartX;
    private float mStartY;
    private float mEndX;
    private float mEndY;
    private float mAngle;
    private int mNumber;
    private View mCircleBackground;
    private Activity mActivity = this;

    private long mTimeStart = 0;
    private long mTimeEnd = 0;

    private ImageView mSafeLockNumbers;
    private ImageView mSafeLock;

    private Button mFirst;
    private Button mSecond;
    private Button mThird;
    private Button mFourth;

    private Button mLastClicked;
    private int mCurrentAnimation;

    private int mTouched;
    private boolean mFour;
    private boolean mThree;
    private boolean mTwo;
    private boolean mOne;

    private int mCode1 = 10;
    private int mCode2 = 14;
    private int mCode3 = 55;
    private int mCode4 = 0;
    private int mRating = 0;
    private Drawable mD;

    private CountDownTimer mTimer;
    /** sets the variables of this level, calls the method hideStatusBar()
     * @param _savedInstanceState*/
    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_level_seven);

        // Circular Background reveal
        mCircleBackground = findViewById(R.id.circleActivity_7);
        mCircleBackground.setVisibility(View.VISIBLE);

        mCircleBackground.post(new Runnable() {
            @Override
            public void run() {
                Animation.circularReveal(mActivity,mCircleBackground); }
        });

        mSafeLockNumbers = findViewById(R.id.activity_level_7_lock_num);
        mSafeLock = findViewById(R.id.activity_level_7_lock);

        Button safeLockButton = findViewById(R.id.activity_level_7_button);
        safeLockButton.setOnClickListener(this);

        mFirst = findViewById(R.id.activity_level_7_first);
        mFirst.setOnClickListener(this);
        mSecond = findViewById(R.id.activity_level_7_second);
        mSecond.setOnClickListener(this);
        mThird = findViewById(R.id.activity_level_7_third);
        mThird.setOnClickListener(this);
        mFourth = findViewById(R.id.activity_level_7_fourth);
        mFourth.setOnClickListener(this);
        mTimeStart = System.currentTimeMillis() + 7000;
        ConstraintLayout constraintLayout = findViewById(R.id.circleActivity_7);
        constraintLayout.setOnTouchListener(this);
        hideStatusBar();

        mD =  getDrawable(R.drawable.ic_safelock_box_checked);
    }


    /**
     * hides the statusbar
     */
    private void hideStatusBar(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    /**
     * calculates the angle in which the safelock is rotated by the user. This angle is then used to display the right animation
     */
    private void rotateLock(){

        float width = Smartphone.getInstance(this).getWidthInPixels();
        float height = Smartphone.getInstance(this).getHeightInPixels();

        // Coordinate of "A"
        float aX = width/2;
        float aY = height/2;

        // Coordinate of "B"
        float bX = mEndX;
        float bY = mEndY;

        // Coordinate of "C"
        float cX = mStartX;
        float cY = mStartY;

        // "A" to "C" - b
        double b = Math.sqrt( Math.pow(aY - cY,2) +  Math.pow(cX - aX,2));

        // "A" to "B" - c
        double c = Math.sqrt( Math.pow(aY - bY,2) +  Math.pow(bX - aX,2));

        // "B" to "C" - a
        double a = Math.sqrt( Math.pow(bY - cY,2) +  Math.pow(bX - cX,2));

        double powB = Math.pow(b,2);
        double powC = Math.pow(c,2);
        double powA = Math.pow(a,2);
        double value = ((powB + powC) - powA) / (2 * b * c);

        if(mStartY > mEndY){
            mAngle = mAngle - ((float) Math.acos(value) * 30);

        } else {
            mAngle = mAngle + ((float) Math.acos(value) * 30);
        }
        mSafeLockNumbers.animate().rotation(mAngle).setDuration(1000);
        mSafeLockNumbers.animate().start();
        mSafeLockNumbers.setRotation(mAngle);
    }
    /**handels the Touch motion
     * @param _v
     * @param _event */
    @Override
    public boolean onTouch(View _v, MotionEvent _event) {

        if(mTouched == 0){
            setTimer();
        }

        if(_event.getAction() == MotionEvent.ACTION_DOWN){          // get the first coordinates of the touch
            mTouched++;
            mStartX = _event.getX();
            mStartY = _event.getY();
        } else if(_event.getAction() == MotionEvent.ACTION_MOVE){   // get the coordinates of the touch and use them to calculate the angle
            mEndX = _event.getX();
            mEndY = _event.getY();
            rotateLock();
        }else if(_event.getAction() == MotionEvent.ACTION_UP){      // Calculate the number where the lock stopped

            mNumber = (int) ((mSafeLockNumbers.getRotation() % 360)/3.6);
            if(mNumber > 0) {
                mNumber = 100 - Math.abs(mNumber);
            } else {
                mNumber = Math.abs(mNumber);
            }
            isNear();                                               // Check if this number is near on of the correct numbers

            mSafeLockNumbers.setRotation(mAngle);
            if(mTimer != null){                                      // If the timer is started -> reset it
                Log.i(TAG, "Timer: reset");
                mTimer.cancel();
                mTimer.start();
            }
            done();
        }
        return true;
    }

    /** ends the activity, and calls the dialog */
    private void done(){
        if(mFirst.getBackground() == mD && mSecond.getBackground() == mD && mThird.getBackground() == mD && mFourth.getBackground() == mD){
            mTimeEnd = System.currentTimeMillis();
            LevelCompleteDialog dialog = new LevelCompleteDialog(this,7);
            dialog.show();
            mTimer.cancel();
        }
    }

    /**
     * sets a new timer. After the timer has ended the "check-animation" will be displayed
     */
    private void setTimer() {
            mTimer = new CountDownTimer(1000, 50) {
                @Override
                public void onTick(long millisUntilFinished) { }

                @Override
                public void onFinish() {
                    animate(mSafeLock, R.drawable.anim_safelock_to_check);
                    mCurrentAnimation = ANIM_TO_CHECK;

                }
            };
    }


    /**
     * Animates the transition of the middle knob (normal, check or delete)
     * @param _safelock ImageView that will be animated (in this case the middle knob
     * @param _animation ID of the animation that will be performed (in this case: delete, check or back to the original state)
     */
    private void animate(ImageView _safelock, int _animation){

        Drawable d = mActivity.getDrawable(_animation);

        if(d instanceof AnimatedVectorDrawableCompat){
            AnimatedVectorDrawableCompat avd = (AnimatedVectorDrawableCompat) d;
            _safelock.setImageDrawable(avd);
            avd.start();

        } else if (d instanceof AnimatedVectorDrawable){
            AnimatedVectorDrawable avd = (AnimatedVectorDrawable) d;
            _safelock.setImageDrawable(avd);
            avd.start();
        }
    }


    /**
     * Checks if the number (chosen by the user) is near to one of the numbers of the code. If yes the phone vibrates. The phone will only vibrate by the correct numbers that have yet to be guessed.
     * There is tolerance of +-2
     */
    private void isNear(){

        if(mNumber >= mCode1 - 2 && mNumber <= mCode1 + 2 && mFirst.getBackground() != mD) {

            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));

        } else if(mNumber >= mCode2 - 2 && mNumber <= mCode2 + 2 && mSecond.getBackground() != mD){

            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
        } else if(mNumber >= mCode3 - 2 && mNumber <= mCode3 + 2 && mThird.getBackground() != mD){

            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));

        } else if(mNumber >= mCode4 - 2 && mNumber <= mCode4 + 2 && mFourth.getBackground() != mD){

            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
        }
    }

    @Override
    public void nextActivity() {
        SharedPreferences sharedPref = this.getSharedPreferences("at.fhooe.mc.karma", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("color", mColor[7]);
        editor.putInt("level",8);
        editor.apply();
        Intent i = new Intent(this, LevelEightActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }

    @Override
    public long getTime() { return mTimeEnd - mTimeStart + 5000; }

    @Override
    public void setRating(int _rate) { mRating = _rate; }

    @Override
    public int getRating() { return mRating; }

    private void setUp(Button _field, int _code){
        _field.setVisibility(View.VISIBLE);
        _field.setText(String.valueOf(mNumber));
        if(mNumber <= _code + 2 && mNumber >= _code - 2){
            _field.setBackground(mD);
        }
        animate(mSafeLock, ANIM_FROM_CHECK);
        mCurrentAnimation = ANIM_FROM_CHECK;
    }
    /** sets the code in the level
     * @param _v */
    @Override
    public void onClick(View _v) {

        switch (_v.getId()){
            case R.id.activity_level_7_button: {
                if(mCurrentAnimation == ANIM_TO_CHECK) {                             // If the knob is a check sign, check if the numbers are already displayed
                    if ((mFirst.getVisibility() == View.INVISIBLE || !mOne) && mFirst.getBackground() != mD) {                  // If not -> make them visible and set the current number
                        setUp(mFirst,mCode1);
                        mOne = true;
                    } else if ((mSecond.getVisibility() == View.INVISIBLE || !mTwo) && mSecond.getBackground() != mD) {
                        setUp(mSecond,mCode2);
                        mTwo = true;
                    } else if ((mThird.getVisibility() == View.INVISIBLE || !mThree) && mThird.getBackground() != mD) {
                       setUp(mThird,mCode3);
                       mThree = true;
                    } else if ((mFourth.getVisibility() == View.INVISIBLE || !mFour) && mFourth.getBackground() != mD) {
                        setUp(mFourth,mCode4);
                        mFour = true;
                    }

                } else if(mCurrentAnimation== ANIM_TO_DELETE){          // If the knob is a delete sign
                    mLastClicked.setText("");                           // delete number of the last clicked field
                    animate(mSafeLock,ANIM_FROM_DELETE);
                    mCurrentAnimation = ANIM_FROM_DELETE;
                }
            }break;
            case R.id.activity_level_7_first: {
                if(mFirst.getBackground() != mD){
                    animate(mSafeLock,ANIM_TO_DELETE);              // clicked on first number -> show delete sign
                    mCurrentAnimation = ANIM_TO_DELETE;
                    mLastClicked = mFirst;                           // update what the latest pressed field was
                    mOne = false;
                }
            }break;
            case R.id.activity_level_7_second: {
                if(mSecond.getBackground() != mD) {
                    animate(mSafeLock, ANIM_TO_DELETE);              // clicked on second number -> show delete sign
                    mCurrentAnimation = ANIM_TO_DELETE;
                    mLastClicked = mSecond;                          // update what the latest pressed field was
                    mTwo = false;
                }
            }break;
            case R.id.activity_level_7_third: {
                if(mThird.getBackground() != mD) {
                    animate(mSafeLock, ANIM_TO_DELETE);              // clicked on third number -> show delete sign
                    mCurrentAnimation = ANIM_TO_DELETE;
                    mLastClicked = mThird;                          // update what the latest pressed field was
                    mThree = false;
                }
            }break;
            case R.id.activity_level_7_fourth: {
                if(mFourth.getBackground() != mD) {
                    animate(mSafeLock, ANIM_TO_DELETE);              // clicked on fourth number -> show delete sign
                    mCurrentAnimation = ANIM_TO_DELETE;
                    mLastClicked = mFourth;                         // update what the latest pressed field was
                    mFour = false;
                }
            }break;
            default:{
                Log.i(TAG, ":: onClick :: unknown ID");
            }
        }
        done();
    }
}
