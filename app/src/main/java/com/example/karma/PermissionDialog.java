package com.example.karma;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Dialog which asks for the permission needed
 */
public class PermissionDialog extends Dialog implements View.OnClickListener  {

    final static String TAG = "at.fhooe.mc.karma LevelCompleteDialog";

    private Activity mActivity;
    String mPermission;
    int mRequestCode;


    /**
     * Constructor of the PermissionDialog
     * @param _activity current activity
     */
    PermissionDialog(Activity _activity) {
        super(_activity);
        mActivity = _activity;
    }

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_permisson);

        TextView title = findViewById(R.id.dialog_permission_title);
        TextView message = findViewById(R.id.dialog_permission_message);
        TextView hint = findViewById(R.id.dialog_permission_hint);
        Button cancel = findViewById(R.id.dialog_permission_cancel);

        title.setText(R.string.dialog_permission_title);
        message.setText(R.string.dialog_permission_message);
        hint.setText(R.string.dialog_permission_hint);

        cancel.setOnClickListener(this);
    }

    /**
     * Requests the permisson when clicked
     * @param _v view that is clicked
     */
    @Override
    public void onClick(View _v) {

        if(_v.getId() == R.id.dialog_permission_cancel){
            dismiss();
            mActivity.requestPermissions(new String[]{mPermission},mRequestCode);
        }
    }
}