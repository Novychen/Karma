package com.example.karma;

import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LevelOverviewActivity extends Activity implements View.OnClickListener {

    final static String TAG = "at.fhooe.mc.karma LevelOverviewActivity";
    private int mNumberStars;
    private String[] mColor = {"#D51116","#C51262","#6C4595","#4C4394","#2B4792","#3B5FA9","#328ACA","#08B7D3","#30B39F","#48AE54","#76B82A","#ACC90F","#FFD600","#F8A912","#ED6D1D","#DD2E14"};

    /** sets the variables of this level, calls the method hideStatusBar()
     * @param _savedInstanceState*/
    @Override
    protected void onCreate(Bundle _savedInstanceState) {

        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_level_overview);

        setOnClickListenerToButtons();
        hideStatusBar();

        SharedPreferences sharedPref = getSharedPreferences("at.fhooe.mc.karma", Context.MODE_PRIVATE);
        ImageView star_1 = null;
        ImageView star_2 = null;
        ImageView star_3 = null;
        int rating;
        for (int i = 1; i <= mColor.length; i++) {

            ImageView [] stars = getStars(i);
            if(stars != null) {
                star_1 = stars[0];
                star_2 = stars[1];
                star_3 = stars[2];
            }

            String string = "level_" + i;
            rating = sharedPref.getInt(string, 0);
            mNumberStars = mNumberStars + rating;
            setVisible(star_1, star_2, star_3, rating);
        }
        TextView t = findViewById(R.id.level_overview_number_curr);
        t.setText(String.valueOf(mNumberStars));
    }

    private ImageView[] getStars(int _level){
        switch(_level){
            case 1:{ return new ImageView[]{findViewById(R.id.level_overview_star_1), findViewById(R.id.level_overview_star_2), findViewById(R.id.level_overview_star_3)}; }
            case 2:{ return new ImageView[]{findViewById(R.id.level_overview_star_4), findViewById(R.id.level_overview_star_5), findViewById(R.id.level_overview_star_6)}; }
            case 3:{ return new ImageView[]{findViewById(R.id.level_overview_star_7), findViewById(R.id.level_overview_star_8), findViewById(R.id.level_overview_star_9)}; }
            case 4:{ return new ImageView[]{findViewById(R.id.level_overview_star_10), findViewById(R.id.level_overview_star_11), findViewById(R.id.level_overview_star_12)}; }
            case 5:{ return new ImageView[]{findViewById(R.id.level_overview_star_13), findViewById(R.id.level_overview_star_14), findViewById(R.id.level_overview_star_15)}; }
            case 6:{ return new ImageView[]{findViewById(R.id.level_overview_star_16), findViewById(R.id.level_overview_star_17), findViewById(R.id.level_overview_star_18)}; }
            case 7:{ return new ImageView[]{findViewById(R.id.level_overview_star_19), findViewById(R.id.level_overview_star_20), findViewById(R.id.level_overview_star_21)}; }
            case 8:{ return new ImageView[]{findViewById(R.id.level_overview_star_22), findViewById(R.id.level_overview_star_23), findViewById(R.id.level_overview_star_24)}; }
            case 9:{ return new ImageView[]{findViewById(R.id.level_overview_star_25), findViewById(R.id.level_overview_star_26), findViewById(R.id.level_overview_star_27)}; }
            case 10:{ return new ImageView[]{findViewById(R.id.level_overview_star_28), findViewById(R.id.level_overview_star_29), findViewById(R.id.level_overview_star_30)}; }
            default:{ return null; }
        }
    }
    /** hides the Statusbar in the activity*/
    private void hideStatusBar(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
    /** sets all the buttons in the overview*/
    private void setOnClickListenerToButtons() {
        Button button = findViewById(R.id.level_overview_level_1);
        button.setOnClickListener(this);

        button = findViewById(R.id.level_overview_level_2);
        button.setOnClickListener(this);

        button = findViewById(R.id.level_overview_level_3);
        button.setOnClickListener(this);

        button = findViewById(R.id.level_overview_level_4);
        button.setOnClickListener(this);

        button = findViewById(R.id.level_overview_level_5);
        button.setOnClickListener(this);

        button = findViewById(R.id.level_overview_level_6);
        button.setOnClickListener(this);

        button = findViewById(R.id.level_overview_level_7);
        button.setOnClickListener(this);

        button = findViewById(R.id.level_overview_level_8);
        button.setOnClickListener(this);

        button = findViewById(R.id.level_overview_level_9);
        button.setOnClickListener(this);

        button = findViewById(R.id.level_overview_level_10);
        button.setOnClickListener(this);

        button = findViewById(R.id.level_overview_level_11);
        button.setOnClickListener(this);

        button = findViewById(R.id.level_overview_level_12);
        button.setOnClickListener(this);

        button = findViewById(R.id.level_overview_level_13);
        button.setOnClickListener(this);

        button = findViewById(R.id.level_overview_level_14);
        button.setOnClickListener(this);

        button = findViewById(R.id.level_overview_level_15);
        button.setOnClickListener(this);

        button = findViewById(R.id.level_overview_level_16);
        button.setOnClickListener(this);

        button = findViewById(R.id.level_overview_back);
        button.setOnClickListener(this);
    }


    /**
     * Three star animation (with Drawable Vector Animation)
     * @param _img ImageView that will be animated
     */
    private void animate(ImageView _img){

        Drawable d = getDrawable(R.drawable.anim_star);

        if(d instanceof AnimatedVectorDrawableCompat){
            AnimatedVectorDrawableCompat avd = (AnimatedVectorDrawableCompat) d;
            _img.setImageDrawable(avd);
            avd.start();

        } else if (d instanceof AnimatedVectorDrawable){
            AnimatedVectorDrawable avd = (AnimatedVectorDrawable) d;

            _img.setImageDrawable(avd);
            avd.start();
        }
    }

    /**
     * Send SharedPreferences so that the next activity knows which color the background should be in as well as the position for the animation
     * @param _v View to fetch the buttons etc
     * @param level which level the player wants to play
     */
    private void sendPreferences(View _v, int level){
        SharedPreferences sharedPref = this.getSharedPreferences("at.fhooe.mc.karma", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        int[] location = new int[2];
        _v.getLocationInWindow(location);
        int x = location[0] + _v.getWidth() / 2;
        int y = location[1];
        Point point = new Point(x, y);

        editor.putInt("x-Co", point.x);
        editor.putInt("y-Co", point.y);
        editor.putInt("level", level);

        Button b = findViewById(_v.getId());
        b.getBackground().setTint(Color.parseColor(mColor[level]));
        editor.putString("color", mColor[level]);
        editor.apply();
    }

    /**sets the visibility of the stars
     * @param _rating rating of the level
     * @param _star1 ImageView of star1
     * @param _star2 ImageView of star2
     * @param _star3 ImageView of star3*/
    private void setVisible(ImageView _star1, ImageView _star2, ImageView _star3, int _rating) {

        for(int i = 0; i <= _rating; i++){
            if(i == 1){
                _star1.setVisibility(View.VISIBLE);
                animate(_star1);
            } else if(i == 2) {
                _star2.setVisibility(View.VISIBLE);
                animate(_star2);
            } else if (i == 3){
                _star3.setVisibility(View.VISIBLE);
                animate(_star3);
            }
        }
    }


    private void checkPermission(Intent _i){
        if(checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED){
            PermissionDialog d = new PermissionDialog(this);
            d.mPermission = Manifest.permission.RECORD_AUDIO;
            d.mRequestCode = 666;
            d.show();
        }
        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED){
            startActivity(_i);
            overridePendingTransition(0, 0);
        }
    }

    /** Handles the button in the overview and calls the right Activity
     * @param _v */
    @Override
    public void onClick(View _v) {
        Handler handler = new Handler();

        switch (_v.getId()) {

            case R.id.level_overview_level_1: {
                Log.i(TAG, " :: onClick :: clicked 1. level");
                Intent i = new Intent(this, LevelOneActivity.class);

                sendPreferences(_v,0);
                i.putExtra("value", 0);
                setResult(RESULT_OK,i);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                checkPermission(i);
            }
            break;
            case R.id.level_overview_level_2: {
                Log.i(TAG, " :: onClick :: clicked 2. level");
                Intent i = new Intent(this, LevelTwoActivity.class);

                sendPreferences(_v,1);
                i.putExtra("value", 1);
                checkPermission(i);
            }
            break;
            case R.id.level_overview_level_3: {
                Log.i(TAG, " :: onClick :: clicked 3. level");
                Intent i = new Intent(this, LevelThreeActivity.class);

                sendPreferences(_v,2);
                i.putExtra("value", 2);
                checkPermission(i);
            }
            break;
            case R.id.level_overview_level_4: {
                Log.i(TAG, " :: onClick :: clicked 4. level");
                Intent i = new Intent(this, LevelFourActivity.class);

                sendPreferences(_v,3);
                i.putExtra("value", 3);
                checkPermission(i);
            }
            break;
            case R.id.level_overview_level_5: {
                Log.i(TAG, " :: onClick :: clicked 5. level");
                Intent i = new Intent(this, LevelFiveActivity.class);

                sendPreferences(_v,4);
                i.putExtra("value", 4);
                checkPermission(i);
            }
            break;
            case R.id.level_overview_level_6: {
                Log.i(TAG, " :: onClick :: clicked 6. level");
                Intent i = new Intent(this, LevelSixActivity.class);

                sendPreferences(_v,5);
                i.putExtra("value", 5);
                checkPermission(i);

            }
            break;
            case R.id.level_overview_level_7: {
                Log.i(TAG, " :: onClick :: clicked 7. level");
                Intent i = new Intent(this, LevelSevenActivity.class);

                sendPreferences(_v,6);
                i.putExtra("value", 6);
                checkPermission(i);
            }
            break;
            case R.id.level_overview_level_8: {
                Log.i(TAG, " :: onClick :: clicked 8. level");
                Intent i = new Intent(this, LevelEightActivity.class);

                sendPreferences(_v,7);
                i.putExtra("value", 7);
                checkPermission(i);
            }
            break;
            case R.id.level_overview_level_9: {
                Log.i(TAG, " :: onClick :: clicked 9. level");
                Intent i = new Intent(this, ComingSoon.class);

                sendPreferences(_v,8);
                checkPermission(i);
            }
            break;
            case R.id.level_overview_level_10: {
                Log.i(TAG, " :: onClick :: clicked 10. level");
                Intent i = new Intent(this, ComingSoon.class);

                sendPreferences(_v,9);
                checkPermission(i);
            }
            break;
            case R.id.level_overview_level_11: {
                Log.i(TAG, " :: onClick :: clicked 11. level");
                Intent i = new Intent(this, ComingSoon.class);

                sendPreferences(_v,10);
                checkPermission(i);
            }
            break;
            case R.id.level_overview_level_12: {
                Log.i(TAG, " :: onClick :: clicked 12. level");
                Intent i = new Intent(this, ComingSoon.class);

                sendPreferences(_v,11);
                checkPermission(i);
            }
            break;
            case R.id.level_overview_level_13: {
                Log.i(TAG, " :: onClick :: clicked 13. level");
                Intent i = new Intent(this, ComingSoon.class);

                sendPreferences(_v,12);
                checkPermission(i);
            }
            break;
            case R.id.level_overview_level_14: {
                Log.i(TAG, " :: onClick :: clicked 14. level");
                Intent i = new Intent(this, ComingSoon.class);

                sendPreferences(_v,13);
                checkPermission(i);
            }
            break;
            case R.id.level_overview_level_15: {
                Log.i(TAG, " :: onClick :: clicked 15. level");
                Intent i = new Intent(this, ComingSoon.class);

                sendPreferences(_v,14);
                checkPermission(i);
            }
            break;
            case R.id.level_overview_level_16: {
                Log.i(TAG, " :: onClick :: clicked 16. level");
                Intent i = new Intent(this, ComingSoon.class);

                sendPreferences(_v,15);
                checkPermission(i);
            }
            break;

            case R.id.level_overview_back:{
                Log.i(TAG, " :: onClick :: clicked back");
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }break;
            default: {
                Log.i(TAG, " :: onClick :: unexpected ID");
            }
        }
        getWindow().setWindowAnimations(0);
        handler.postDelayed(new Runnable() {
            public void run() { finish(); }
        }, 1000);
    }
}
