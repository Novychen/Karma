package com.example.karma;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;


/**
 * Dialog shown to the user when a level is completed
 */
public class LevelCompleteDialog extends Dialog implements View.OnClickListener {

    final static String TAG = "at.fhooe.mc.karma LevelCompleteDialog";

    private Activity mActivity;
    private long mTime;
    private Riddle mRiddle;

    LevelCompleteDialog(Activity _activity) {
        super(_activity);
        mActivity = _activity;
    }

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_level_complete);
        mRiddle = (Riddle) mActivity;
        mTime = mRiddle.getTime();

        TextView title = findViewById(R.id.dialog_level_complete_title);
        TextView message = findViewById(R.id.dialog_level_complete_message);
        Button backButton = findViewById(R.id.dialog_level_complete_back);
        Button nextButton = findViewById(R.id.dialog_level_complete_next);


        ImageView star1 = findViewById(R.id.dialog_level_complete_star_1);
        ImageView star2 = findViewById(R.id.dialog_level_complete_star_2);
        ImageView star3 = findViewById(R.id.dialog_level_complete_star_3);

        title.setText(R.string.dialog_level_complete_title);
        message.setText(R.string.dialog_level_complete_message);
        animate(star1, star2, star3);

        backButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);


    }

    /**
     * Three star animation (with Drawable Vector Animation)
     * @param _star1 first star icon
     * @param _star2 second star icon
     * @param _star3 third star icon
     */
    private void animate(ImageView _star1, ImageView _star2, ImageView _star3){

        Drawable d = mActivity.getDrawable(R.drawable.anim_star);

        if(d instanceof AnimatedVectorDrawableCompat){
            AnimatedVectorDrawableCompat avd = (AnimatedVectorDrawableCompat) d;
            if(mTime < 20000){
                mRiddle.setRating(3);
                _star1.setImageDrawable(avd);
                _star2.setImageDrawable(avd);
                _star3.setImageDrawable(avd);
            }else if(mTime < 50000){
                mRiddle.setRating(2);
                _star1.setImageDrawable(avd);
                _star2.setImageDrawable(avd);
            }else{
                mRiddle.setRating(1);
                _star2.setImageDrawable(avd);
            }
            avd.start();

        } else if (d instanceof AnimatedVectorDrawable){
            AnimatedVectorDrawable avd = (AnimatedVectorDrawable) d;
            if(mTime < 20000){
                mRiddle.setRating(3);
                _star1.setImageDrawable(avd);
                _star2.setImageDrawable(avd);
                _star3.setImageDrawable(avd);
            }else if(mTime < 50000){
                mRiddle.setRating(2);
                _star1.setImageDrawable(avd);
                _star2.setImageDrawable(avd);
            }else{
                mRiddle.setRating(1);
                _star2.setImageDrawable(avd);
            }
            avd.start();
        }
    }

    @Override
    public void onClick(View _v) {

        switch (_v.getId()){
            case R.id.dialog_level_complete_back: {
                Log.i(TAG, " :: onClick :: clicked back button");
                dismiss();
                Intent intent = new Intent(mActivity, LevelOverviewActivity.class);
                mActivity.startActivity(intent);
                mActivity.finish();
            }break;
            case R.id.dialog_level_complete_next: {
                Log.i(TAG, " :: onClick :: clicked next button");
                mRiddle.nextActivity();
                dismiss();
            }break;
            default:{
                Log.i(TAG, " :: onClick :: unexpected ID");
            }
        }
    }
}

