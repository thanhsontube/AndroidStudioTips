package nt.son.androidstudiotips;

import android.app.Application;

import nt.son.androidstudiotips.retrofit.AppApi;

/**
 * Created by Sonnt on 4/17/15.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppApi.createInstance(getApplicationContext());
    }
}
