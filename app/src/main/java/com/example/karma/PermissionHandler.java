package com.example.karma;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.nfc.cardemulation.HostNfcFService;
import android.util.DisplayMetrics;

import java.util.logging.Handler;

public class PermissionHandler {

    private static PermissionHandler mInstance;
    private static Activity mActivity;

    private PermissionHandler() {
    }

    static PermissionHandler getInstance(Activity _activity) {
        if (mInstance == null) {
            mInstance = new PermissionHandler();
            mActivity = _activity;
            return mInstance;
        } else {
            return mInstance;
        }
    }


    protected void checkPermission(final String _permission, final int _requestCode) {
        if (mActivity.checkSelfPermission(_permission) == PackageManager.PERMISSION_DENIED) {
                PermissonDialog dialog = new PermissonDialog(mActivity);
                dialog.mPermission = _permission;
                dialog.mRequestCode = _requestCode;
                dialog.show();
        }
    }
}