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

    private TextView mTitle;
    private TextView mMessage;
    private Button mBackButton;
    private Button mNextButton;
    private ImageView mStar1;
    private ImageView  mStar2;
    private ImageView  mStar3;
    private Activity mActivity;


    public LevelCompleteDialog(Activity _activity){
        super(_activity);
        mActivity = _activity;
    }

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_level_complete);

        mTitle = findViewById(R.id.dialog_level_complete_title);
        mMessage = findViewById(R.id.dialog_level_complete_message);
        mBackButton = findViewById(R.id.dialog_level_complete_back);
        mNextButton = findViewById(R.id.dialog_level_complete_next);
        mStar1 = findViewById(R.id.dialog_level_complete_star_1);
        mStar2 = findViewById(R.id.dialog_level_complete_star_2);
        mStar3 = findViewById(R.id.dialog_level_complete_star_3);


        animate(mStar1,mStar2,mStar3);


        mBackButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
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
            _star1.setImageDrawable(avd);
            _star2.setImageDrawable(avd);
            _star3.setImageDrawable(avd);
            avd.start();

        } else if (d instanceof AnimatedVectorDrawable){
            AnimatedVectorDrawable avd = (AnimatedVectorDrawable) d;

            _star1.setImageDrawable(avd);
            _star2.setImageDrawable(avd);
            _star3.setImageDrawable(avd);
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
                dismiss();
            }break;
            default:{
                Log.i(TAG, " :: onClick :: unexpected ID");
            }
        }


    }
}

