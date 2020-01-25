package com.example.karma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class LevelOverviewActivity extends Activity implements View.OnClickListener {

    final static String TAG = "at.fhooe.mc.karma";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_overview);

        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void setOnClickListnerToButtons(){
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




    @Override
    public void onClick(View _v) {
        Intent i = new Intent(this, LevelActivity.class);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("level", _v.getId());
        editor.apply();

        switch (_v.getId()){

            case R.id.level_overview_level_1: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 1. level");
                startActivity(i);
            }break;
            case R.id.level_overview_level_2: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 2. level");
                startActivity(i);
            }break;
            case R.id.level_overview_level_3: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 3. level");
                startActivity(i);
            }break;
            case R.id.level_overview_level_4: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 4. level");
                startActivity(i);
            }break;
            case R.id.level_overview_level_5: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 5. level");
                startActivity(i);
            }break;
            case R.id.level_overview_level_6: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 6. level");
                startActivity(i);
            }break;
            case R.id.level_overview_level_7: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 7. level");
                startActivity(i);
            }break;
            case R.id.level_overview_level_8: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 8. level");
                startActivity(i);
            }break;
            case R.id.level_overview_level_9: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 9. level");
                startActivity(i);
            }break;
            case R.id.level_overview_level_10: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 10. level");
                startActivity(i);
            }break;
            case R.id.level_overview_level_11: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 11. level");
                startActivity(i);
            }break;
            case R.id.level_overview_level_12: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 12. level");
                startActivity(i);
            }break;
            case R.id.level_overview_level_13: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 13. level");
                startActivity(i);
            }break;
            case R.id.level_overview_level_14: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 14. level");
                startActivity(i);
            }break;
            case R.id.level_overview_level_15: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 15. level");
                startActivity(i);
            }break;
            case R.id.level_overview_level_16: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 16. level");
                startActivity(i);
            }break;
            default: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: unexpected ID");
            }

        }
    }
}
