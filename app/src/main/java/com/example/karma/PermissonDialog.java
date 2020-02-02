package com.example.karma;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PermissonDialog extends Dialog implements View.OnClickListener  {

    final static String TAG = "at.fhooe.mc.karma LevelCompleteDialog";

    private TextView mTitle;
    private TextView mMessage;
    private TextView mHint;
    private Button mCancel;
    private Activity mActivity;
    public String mPermission;
    public int mRequestCode;


    public PermissonDialog(Activity _activity) {
        super(_activity);
        mActivity = _activity;
    }

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_permisson);

        mTitle = findViewById(R.id.dialog_permission_title);
        mMessage = findViewById(R.id.dialog_permission_message);
        mHint = findViewById(R.id.dialog_permission_hint);
        mCancel = findViewById(R.id.dialog_permission_cancel);

        mTitle.setText(R.string.dialog_permission_title);
        mMessage.setText(R.string.dialog_permission_message);
        mHint.setText(R.string.dialog_permission_hint);

        mCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View _v) {

        if(_v.getId() == R.id.dialog_permission_cancel){
            dismiss();
            mActivity.requestPermissions(new String[]{mPermission},mRequestCode);
        }
    }
}