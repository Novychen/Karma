package com.example.karma;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity implements View.OnClickListener {

    final static String TAG = "at.fhooe.mc.karma";
    private int mCount = 0;
    String[] mColor = {"#D51116","#C51262","#6C4595","#4C4394","#2B4792","#3B5FA9","#328ACA","#08B7D3","#30B39F","#48AE54","#76B82A","#ACC90F","#FFD600","#F8A912","#ED6D1D","#DD2E14"};



    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_main);

        hideStatusBar();

        ImageButton button = findViewById(R.id.activity_main_play);
        button.setOnClickListener(this);

        button = findViewById(R.id.activity_main_levels);
        button.setOnClickListener(this);

        button = findViewById(R.id.activity_main_info);
        button.setOnClickListener(this);

        PermissionHandler p = new PermissionHandler(this);
        p.checkPermission();

    }

    private void hideStatusBar(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    @Override
    public void onClick(View _v) {

        switch (_v.getId()){

            case R.id.activity_main_play: {


                SharedPreferences sharedPref = this.getSharedPreferences("at.fhooe.mc.karma", Context.MODE_PRIVATE);
                int level = sharedPref.getInt("level", 0);
                Log.i(TAG,"LEVEL : " + level);
                Intent i = new Intent(this, LevelOneActivity.class);
                if(level != 1) {
                    switch (level) {
                        case 1:
                            i = new Intent(this, LevelOneActivity.class);
                            break;
                        case 2:
                            i = new Intent(this, LevelTwoActivity.class);
                            break;
                        case 3:
                            i = new Intent(this, LevelThreeActivity.class);
                            break;
                        case 4:
                            i = new Intent(this, LevelFourActivity.class);
                            break;
                        case 5:
                            i = new Intent(this, LevelFiveActivity.class);
                            break;
                        case 6:
                            i = new Intent(this, LevelSixActivity.class);
                            break;
                        case 7:
                            i = new Intent(this, LevelSevenActivity.class);
                            break;
                        case 8:
                            i = new Intent(this, LevelEightActivity.class);
                            break;
                        default:
                            i = new Intent(this, LevelOneActivity.class);
                    }
                }

                SharedPreferences.Editor editor = sharedPref.edit();

                int[] location = new int[2];
                _v.getLocationInWindow(location);
                int x = location[0] + _v.getWidth() / 2;
                int y = location[1];
                Point point = new Point(x,y);

                editor.putInt("x-Co",point.x);
                editor.putInt("y-Co",point.y);
                if(level > 0){
                    editor.putString("color",mColor[level - 1]);
                }else {editor.putString("color",mColor[0]);}


                editor.apply();

                startActivity(i);
                overridePendingTransition(0, 0);

            } break;
            case R.id.activity_main_levels: {
                Intent i = new Intent(this, LevelOverviewActivity.class);
                i.putExtra("value",mCount);
                startActivity(i);
            } break;
            case R.id.activity_main_info: {
                Intent i = new Intent(this, GameInfo.class);
                startActivity(i);
            }break;
            default: {
                Log.i(TAG,"MainActivity :: onClick :: unexpected ID");
            }
        }
    }
}
