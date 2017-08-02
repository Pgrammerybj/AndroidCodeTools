package com.jackyang.codetools;

import android.app.Application;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 *
 * Created by JackyYang on 2017/8/1.
 */

public class BaseApplication extends Application {

    private static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "BaseApplication: onCreate");
        mApplication = this;
    }

    public static Application currentApplication() {
        return mApplication;
    }
}
