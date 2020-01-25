package com.example.karma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity implements View.OnClickListener {

    final static String TAG = "at.fhooe.mc.karma";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);

        ImageButton button = findViewById(R.id.activity_main_play);
        button.setOnClickListener(this);

        button = findViewById(R.id.activity_main_levels);
        button.setOnClickListener(this);

        button = findViewById(R.id.activity_main_settings);
        button.setOnClickListener(this);


    }

    @Override
    public void onClick(View _v) {
        switch (_v.getId()){
            case R.id.activity_main_play: {
                Intent i = new Intent(this, LevelActivity.class);
                startActivity(i);
            } break;
            case R.id.activity_main_levels: {
                Intent i = new Intent(this, LevelOverviewActivity.class);
                startActivity(i);
            } break;
            case R.id.activity_main_settings: {
                Log.i(TAG,"MainActivity :: onClick ::Settings was clicked");
            }break;
            default: {
                Log.i(TAG,"MainActivity :: onClick :: unexpected ID");
            }
        }
    }
}
