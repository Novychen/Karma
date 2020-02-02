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

public class LevelOverviewActivity extends Activity implements View.OnClickListener {

    final static String TAG = "at.fhooe.mc.karma";
    private String[] mColor = {"#D51116","#C51262","#6C4595","#4C4394","#2B4792","#3B5FA9","#328ACA","#08B7D3","#30B39F","#48AE54","#76B82A","#ACC90F","#FFD600","#F8A912","#ED6D1D","#DD2E14"};
    public static final int RECORD_AUDIO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_overview);

        setOnClickListenerToButtons();

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }


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
     * @param _star1 first star icon
     * @param _star2 second star icon
     * @param _star3 third star icon
     */
    private void animate(ImageView _star1, ImageView _star2, ImageView _star3){

        Drawable d = getDrawable(R.drawable.anim_star);

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

    /**
     * Send SharedPreferences so that the next activity (LevelActivity) knows which color the background should be in as well as the position for the animation
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

        Button b = findViewById(_v.getId());
        b.getBackground().setTint(Color.parseColor(mColor[level]));
        editor.putString("color", mColor[level]);
        editor.apply();
    }


    private void setVisible(ImageView _star1, ImageView _star2, ImageView _star3){

        _star1.setVisibility(View.VISIBLE);
        _star2.setVisibility(View.VISIBLE);
        _star3.setVisibility(View.VISIBLE);
    }
    @Override
    public void onClick(View _v) {
        Handler handler = new Handler();

        switch (_v.getId()) {

            case R.id.level_overview_level_1: {
                Log.i(TAG, " :: onClick :: clicked 1. level");
                Intent i = new Intent(this, LevelOneActivity.class);
                ImageView star_1 = findViewById(R.id.level_overview_star_1);
                ImageView star_2 = findViewById(R.id.level_overview_star_2);
                ImageView star_3 = findViewById(R.id.level_overview_star_3);

                setVisible(star_1,star_2,star_3);


                animate(star_1,star_2,star_3);
                sendPreferences(_v,0);
                i.putExtra("value", 0);
                setResult(RESULT_OK,i);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_2: {
                Intent i = new Intent(this, LevelTwoActivity.class);
                Log.i(TAG, " :: onClick :: clicked 2. level");

                ImageView star_4 = findViewById(R.id.level_overview_star_4);
                ImageView star_5 = findViewById(R.id.level_overview_star_5);
                ImageView star_6 = findViewById(R.id.level_overview_star_6);

                setVisible(star_4,star_5,star_6);
                sendPreferences(_v,1);
                i.putExtra("value", 1);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_3: {
                Log.i(TAG, " :: onClick :: clicked 3. level");
                Intent i = new Intent(this, LevelThreeActivity.class);
                ImageView star_7 = findViewById(R.id.level_overview_star_7);
                ImageView star_8 = findViewById(R.id.level_overview_star_8);
                ImageView star_9 = findViewById(R.id.level_overview_star_9);

                setVisible(star_7,star_8,star_9);
                sendPreferences(_v,2);
                i.putExtra("value", 2);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_4: {
                Log.i(TAG, " :: onClick :: clicked 4. level");
                Intent i = new Intent(this, LevelFourActivity.class);
                ImageView star_10 = findViewById(R.id.level_overview_star_10);
                ImageView star_11 = findViewById(R.id.level_overview_star_11);
                ImageView star_12 = findViewById(R.id.level_overview_star_12);

                setVisible(star_10,star_11,star_12);
                sendPreferences(_v,3);
                i.putExtra("value", 3);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_5: {
                Log.i(TAG, " :: onClick :: clicked 5. level");
                Intent i = new Intent(this, LevelFiveActivity.class);
                ImageView star_13 = findViewById(R.id.level_overview_star_13);
                ImageView star_14 = findViewById(R.id.level_overview_star_14);
                ImageView star_15 = findViewById(R.id.level_overview_star_15);

                setVisible(star_13,star_14,star_15);
                sendPreferences(_v,4);
                i.putExtra("value", 4);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_6: {
                Log.i(TAG, " :: onClick :: clicked 6. level");
                Intent i = new Intent(this, LevelSixActivity.class);
                ImageView star_16 = findViewById(R.id.level_overview_star_16);
                ImageView star_17 = findViewById(R.id.level_overview_star_17);
                ImageView star_18 = findViewById(R.id.level_overview_star_18);

                setVisible(star_16,star_17,star_18);
                sendPreferences(_v,5);
                i.putExtra("value", 5);

                startActivity(i);
                overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_7: {
                Log.i(TAG, " :: onClick :: clicked 7. level");
                Intent i = new Intent(this, LevelSevenActivity.class);
                ImageView star_19 = findViewById(R.id.level_overview_star_19);
                ImageView star_20 = findViewById(R.id.level_overview_star_20);
                ImageView star_21 = findViewById(R.id.level_overview_star_21);

                setVisible(star_19,star_20,star_21);
                sendPreferences(_v,6);
                i.putExtra("value", 6);

                startActivity(i);
                overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_8: {
                Log.i(TAG, " :: onClick :: clicked 8. level");
                Intent i = new Intent(this, ComingSoon.class);
                ImageView star_22 = findViewById(R.id.level_overview_star_22);
                ImageView star_23 = findViewById(R.id.level_overview_star_23);
                ImageView star_24 = findViewById(R.id.level_overview_star_24);

                setVisible(star_22,star_23,star_24);
                sendPreferences(_v,7);
                i.putExtra("value", 7);

                startActivity(i);
                overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_9: {
                Log.i(TAG, " :: onClick :: clicked 9. level");
                Intent i = new Intent(this, ComingSoon.class);
                ImageView star_25 = findViewById(R.id.level_overview_star_25);
                ImageView star_26 = findViewById(R.id.level_overview_star_26);
                ImageView star_27 = findViewById(R.id.level_overview_star_27);

                setVisible(star_25,star_26,star_27);
                sendPreferences(_v,8);

                startActivity(i);
                overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_10: {
                Log.i(TAG, " :: onClick :: clicked 10. level");
                Intent i = new Intent(this, ComingSoon.class);
                ImageView star_28 = findViewById(R.id.level_overview_star_28);
                ImageView star_29 = findViewById(R.id.level_overview_star_29);
                ImageView star_30 = findViewById(R.id.level_overview_star_30);

                setVisible(star_28,star_29,star_30);
                sendPreferences(_v,9);

                startActivity(i);
                overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_11: {
                Log.i(TAG, " :: onClick :: clicked 11. level");
                Intent i = new Intent(this, ComingSoon.class);
                ImageView star_31 = findViewById(R.id.level_overview_star_31);
                ImageView star_32 = findViewById(R.id.level_overview_star_32);
                ImageView star_33 = findViewById(R.id.level_overview_star_33);

                setVisible(star_31,star_32,star_33);
                sendPreferences(_v,10);

                startActivity(i);
                overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_12: {
                Log.i(TAG, " :: onClick :: clicked 12. level");
                Intent i = new Intent(this, ComingSoon.class);
                ImageView star_34 = findViewById(R.id.level_overview_star_34);
                ImageView star_35 = findViewById(R.id.level_overview_star_35);
                ImageView star_36 = findViewById(R.id.level_overview_star_36);

                setVisible(star_34,star_35,star_36);
                sendPreferences(_v,11);

                startActivity(i);
                overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_13: {
                Log.i(TAG, " :: onClick :: clicked 13. level");
                Intent i = new Intent(this, ComingSoon.class);
                ImageView star_37 = findViewById(R.id.level_overview_star_37);
                ImageView star_38 = findViewById(R.id.level_overview_star_38);
                ImageView star_39 = findViewById(R.id.level_overview_star_39);

                setVisible(star_37,star_38,star_39);
                sendPreferences(_v,12);

                startActivity(i);
                overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_14: {
                Log.i(TAG, " :: onClick :: clicked 14. level");
                Intent i = new Intent(this, ComingSoon.class);
                ImageView star_40 = findViewById(R.id.level_overview_star_40);
                ImageView star_41 = findViewById(R.id.level_overview_star_41);
                ImageView star_42 = findViewById(R.id.level_overview_star_42);

                setVisible(star_40,star_41,star_42);
                sendPreferences(_v,13);

                startActivity(i);
                overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_15: {
                Log.i(TAG, " :: onClick :: clicked 15. level");
                Intent i = new Intent(this, ComingSoon.class);
                ImageView star_43 = findViewById(R.id.level_overview_star_43);
                ImageView star_44 = findViewById(R.id.level_overview_star_44);
                ImageView star_45 = findViewById(R.id.level_overview_star_45);

                setVisible(star_43,star_44,star_45);
                sendPreferences(_v,14);

                startActivity(i);
                overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_16: {
                Log.i(TAG, " :: onClick :: clicked 16. level");
                Intent i = new Intent(this, ComingSoon.class);
                ImageView star_46 = findViewById(R.id.level_overview_star_46);
                ImageView star_47 = findViewById(R.id.level_overview_star_47);
                ImageView star_48 = findViewById(R.id.level_overview_star_48);

                setVisible(star_46,star_47,star_48);
                sendPreferences(_v,15);

                startActivity(i);
                overridePendingTransition(0, 0);
            }
            break;

            case R.id.level_overview_back:{
                Log.i(TAG, " :: onClick :: clicked back");
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
            }break;
            default: {
                Log.i(TAG, " :: onClick :: unexpected ID");
            }

        }
    }
}
