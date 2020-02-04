package com.example.karma;

import android.app.Activity;
import android.content.pm.PackageManager;

/**
 * This class handles all permissions
 */
class PermissionHandler {

    private PermissionHandler mInstance;
    private Activity mActivity;

    private  PermissionHandler(){}

    /**
     * Constructor for the calls PermissionHandler
     * @param _activity that is currently running
     */
    PermissionHandler(Activity _activity) {
        if (mInstance == null) {
            mInstance = new PermissionHandler();
            mActivity = _activity;
        }
    }


    /**
     * Checks if the permission for audio recording was granted, if not it will ask the user to do so
     */
    void checkPermission() {
        if (mActivity.checkSelfPermission(android.Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
                PermissionDialog dialog = new PermissionDialog(mActivity);
                dialog.mPermission = android.Manifest.permission.RECORD_AUDIO;
                dialog.mRequestCode = 666;
                dialog.show();
        }
    }
}