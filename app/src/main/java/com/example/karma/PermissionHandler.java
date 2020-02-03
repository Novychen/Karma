package com.example.karma;

import android.app.Activity;
import android.content.pm.PackageManager;

class PermissionHandler {

    private PermissionHandler mInstance;
    private Activity mActivity;

    private  PermissionHandler(){

    }

    PermissionHandler(Activity _activity) {
        if (mInstance == null) {
            mInstance = new PermissionHandler();
            mActivity = _activity;
        }
    }


    void checkPermission() {
        if (mActivity.checkSelfPermission(android.Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
                PermissionDialog dialog = new PermissionDialog(mActivity);
                dialog.mPermission = android.Manifest.permission.RECORD_AUDIO;
                dialog.mRequestCode = 666;
                dialog.show();
        }
    }
}