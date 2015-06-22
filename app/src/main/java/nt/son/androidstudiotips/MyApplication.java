package nt.son.androidstudiotips;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.facebook.FacebookSdk;
import com.facebook.drawee.backends.pipeline.Fresco;

import io.fabric.sdk.android.Fabric;
import nt.son.androidstudiotips.retrofit.AppApi;

/**
 * Created by Sonnt on 4/17/15.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        AppApi.createInstance(getApplicationContext());
        FacebookSdk.sdkInitialize(getApplicationContext());
        Fresco.initialize(this);
    }
}
