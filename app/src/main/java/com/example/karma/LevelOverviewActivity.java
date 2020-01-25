package com.example.karma;

import androidx.core.app.ActivityCompat;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LevelOverviewActivity extends Activity  implements View.OnClickListener {

    final static String TAG = "at.fhooe.mc.karma";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_overview);

        setOnClickListnerToButtons();

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
        SharedPreferences sharedPref = this.getSharedPreferences("at.fhooe.mc.karma",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        int[] location = new int[2];
        _v.getLocationInWindow(location);
        int x = location[0] + _v.getWidth() / 2;
        int y = location[1];
        Point point = new Point(x,y);

        Button b = findViewById(_v.getId());
        String color = b.getText().toString();

        editor.putInt("x-Co",point.x);
        editor.putInt("y-Co",point.y);
        editor.putString("color",color);
        editor.apply();

        switch (_v.getId()){

            case R.id.level_overview_level_1: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 1. level");
                ActivityCompat.startActivity(this,i,null);
                overridePendingTransition(0, 0);
            }break;
            case R.id.level_overview_level_2: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 2. level");
                ActivityCompat.startActivity(this,i,null);
                overridePendingTransition(0, 0);
            }break;
            case R.id.level_overview_level_3: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 3. level");
                ActivityCompat.startActivity(this,i,null);
                overridePendingTransition(0, 0);
            }break;
            case R.id.level_overview_level_4: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 4. level");
                ActivityCompat.startActivity(this,i,null);
                overridePendingTransition(0, 0);
            }break;
            case R.id.level_overview_level_5: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 5. level");
                ActivityCompat.startActivity(this,i,null);
                overridePendingTransition(0, 0);
            }break;
            case R.id.level_overview_level_6: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 6. level");
                ActivityCompat.startActivity(this,i,null);
                overridePendingTransition(0, 0);
            }break;
            case R.id.level_overview_level_7: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 7. level");
                ActivityCompat.startActivity(this,i,null);
                overridePendingTransition(0, 0);
            }break;
            case R.id.level_overview_level_8: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 8. level");
                ActivityCompat.startActivity(this,i,null);
                overridePendingTransition(0, 0);
            }break;
            case R.id.level_overview_level_9: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 9. level");
                ActivityCompat.startActivity(this,i,null);
                overridePendingTransition(0, 0);
            }break;
            case R.id.level_overview_level_10: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 10. level");
                ActivityCompat.startActivity(this,i,null);
                overridePendingTransition(0, 0);
            }break;
            case R.id.level_overview_level_11: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 11. level");
                ActivityCompat.startActivity(this,i,null);
                overridePendingTransition(0, 0);
            }break;
            case R.id.level_overview_level_12: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 12. level");
                ActivityCompat.startActivity(this,i,null);
                overridePendingTransition(0, 0);
            }break;
            case R.id.level_overview_level_13: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 13. level");
                ActivityCompat.startActivity(this,i,null);
                overridePendingTransition(0, 0);
            }break;
            case R.id.level_overview_level_14: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 14. level");
                ActivityCompat.startActivity(this,i,null);
                overridePendingTransition(0, 0);
            }break;
            case R.id.level_overview_level_15: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 15. level");
                ActivityCompat.startActivity(this,i,null);
                overridePendingTransition(0, 0);
            }break;
            case R.id.level_overview_level_16: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: clicked 16. level");
                ActivityCompat.startActivity(this,i,null);
                overridePendingTransition(0, 0);
            }break;
            default: {
                Log.i(TAG,"LevelOverviewActivity :: onClick :: unexpected ID");
            }

        }
    }
}
