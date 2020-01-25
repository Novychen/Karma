package com.example.karma;

import androidx.core.app.ActivityCompat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LevelOverviewActivity extends Activity  implements View.OnClickListener {

    final static String TAG = "at.fhooe.mc.karma";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_overview);

        setOnClickListnerToButtons();

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }


    private void setOnClickListnerToButtons() {
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
    }


    /**
     * Three star animation (with Drawable Vector Animation)
     * @param _star1 first star icon
     * @param _star2 second star icon
     * @param _star3 third star icon
     */
    private void animate(ImageView _star1, ImageView _star2, ImageView _star3){

        Drawable d = getDrawable(R.drawable.levelstar_anim);


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
        Intent i = new Intent(this, LevelActivity.class);
        SharedPreferences sharedPref = this.getSharedPreferences("at.fhooe.mc.karma", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        int[] location = new int[2];
        _v.getLocationInWindow(location);
        int x = location[0] + _v.getWidth() / 2;
        int y = location[1];
        Point point = new Point(x, y);

        Button b = findViewById(_v.getId());
        String color = b.getText().toString();

        editor.putInt("x-Co", point.x);
        editor.putInt("y-Co", point.y);
        editor.putString("color", color);
        editor.apply();

        switch (_v.getId()) {

            case R.id.level_overview_level_1: {
                Log.i(TAG, "LevelOverviewActivity :: onClick :: clicked 1. level");

                ImageView star_1 = findViewById(R.id.level_overview_star_1);
                ImageView star_2 = findViewById(R.id.level_overview_star_2);
                ImageView star_3 = findViewById(R.id.level_overview_star_3);

                animate(star_1,star_2,star_3);
                //ActivityCompat.startActivity(this, i, null);
                //overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_2: {
                Log.i(TAG, "LevelOverviewActivity :: onClick :: clicked 2. level");

                ImageView star_4 = findViewById(R.id.level_overview_star_4);
                ImageView star_5 = findViewById(R.id.level_overview_star_5);
                ImageView star_6 = findViewById(R.id.level_overview_star_6);

                animate(star_4,star_5,star_6);
                //ActivityCompat.startActivity(this, i, null);
                //overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_3: {
                Log.i(TAG, "LevelOverviewActivity :: onClick :: clicked 3. level");

                ImageView star_7 = findViewById(R.id.level_overview_star_7);
                ImageView star_8 = findViewById(R.id.level_overview_star_8);
                ImageView star_9 = findViewById(R.id.level_overview_star_9);

                animate(star_7,star_8,star_9);

                //ActivityCompat.startActivity(this, i, null);
                //overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_4: {
                Log.i(TAG, "LevelOverviewActivity :: onClick :: clicked 4. level");

                ImageView star_10 = findViewById(R.id.level_overview_star_10);
                ImageView star_11 = findViewById(R.id.level_overview_star_11);
                ImageView star_12 = findViewById(R.id.level_overview_star_12);

                animate(star_10,star_11,star_12);

                //ActivityCompat.startActivity(this, i, null);
                //overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_5: {
                Log.i(TAG, "LevelOverviewActivity :: onClick :: clicked 5. level");

                ImageView star_13 = findViewById(R.id.level_overview_star_13);
                ImageView star_14 = findViewById(R.id.level_overview_star_14);
                ImageView star_15 = findViewById(R.id.level_overview_star_15);

                animate(star_13,star_14,star_15);

                //ActivityCompat.startActivity(this, i, null);
                //overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_6: {
                Log.i(TAG, "LevelOverviewActivity :: onClick :: clicked 6. level");

                ImageView star_16 = findViewById(R.id.level_overview_star_16);
                ImageView star_17 = findViewById(R.id.level_overview_star_17);
                ImageView star_18 = findViewById(R.id.level_overview_star_18);

                animate(star_16,star_17,star_18);

                //ActivityCompat.startActivity(this, i, null);
                //overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_7: {
                Log.i(TAG, "LevelOverviewActivity :: onClick :: clicked 7. level");

                ImageView star_19 = findViewById(R.id.level_overview_star_19);
                ImageView star_20 = findViewById(R.id.level_overview_star_20);
                ImageView star_21 = findViewById(R.id.level_overview_star_21);

                animate(star_19,star_20,star_21);

                //ActivityCompat.startActivity(this, i, null);
                //overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_8: {
                Log.i(TAG, "LevelOverviewActivity :: onClick :: clicked 8. level");

                ImageView star_22 = findViewById(R.id.level_overview_star_22);
                ImageView star_23 = findViewById(R.id.level_overview_star_23);
                ImageView star_24 = findViewById(R.id.level_overview_star_24);

                animate(star_22,star_23,star_24);

                //ActivityCompat.startActivity(this, i, null);
                //overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_9: {
                Log.i(TAG, "LevelOverviewActivity :: onClick :: clicked 9. level");

                ImageView star_25 = findViewById(R.id.level_overview_star_25);
                ImageView star_26 = findViewById(R.id.level_overview_star_26);
                ImageView star_27 = findViewById(R.id.level_overview_star_27);

                animate(star_25,star_26,star_27);

                //ActivityCompat.startActivity(this, i, null);
                //overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_10: {
                Log.i(TAG, "LevelOverviewActivity :: onClick :: clicked 10. level");

                ImageView star_28 = findViewById(R.id.level_overview_star_28);
                ImageView star_29 = findViewById(R.id.level_overview_star_29);
                ImageView star_30 = findViewById(R.id.level_overview_star_30);

                animate(star_28,star_29,star_30);

                //ActivityCompat.startActivity(this, i, null);
                //overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_11: {
                Log.i(TAG, "LevelOverviewActivity :: onClick :: clicked 11. level");

                ImageView star_31 = findViewById(R.id.level_overview_star_31);
                ImageView star_32 = findViewById(R.id.level_overview_star_32);
                ImageView star_33 = findViewById(R.id.level_overview_star_33);

                animate(star_31,star_32,star_33);

                //ActivityCompat.startActivity(this, i, null);
                //overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_12: {
                Log.i(TAG, "LevelOverviewActivity :: onClick :: clicked 12. level");

                ImageView star_34 = findViewById(R.id.level_overview_star_34);
                ImageView star_35 = findViewById(R.id.level_overview_star_35);
                ImageView star_36 = findViewById(R.id.level_overview_star_36);

                animate(star_34,star_35,star_36);

                //ActivityCompat.startActivity(this, i, null);
                //overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_13: {
                Log.i(TAG, "LevelOverviewActivity :: onClick :: clicked 13. level");
                //ActivityCompat.startActivity(this, i, null);
                //overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_14: {
                Log.i(TAG, "LevelOverviewActivity :: onClick :: clicked 14. level");
                //ActivityCompat.startActivity(this, i, null);
                //overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_15: {
                Log.i(TAG, "LevelOverviewActivity :: onClick :: clicked 15. level");
                //ActivityCompat.startActivity(this, i, null);
                //overridePendingTransition(0, 0);
            }
            break;
            case R.id.level_overview_level_16: {
                Log.i(TAG, "LevelOverviewActivity :: onClick :: clicked 16. level");
                //ActivityCompat.startActivity(this, i, null);
                //overridePendingTransition(0, 0);
            }
            break;
            default: {
                Log.i(TAG, "LevelOverviewActivity :: onClick :: unexpected ID");
            }

        }
    }
}
