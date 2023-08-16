package com.iosnotch.dynamic;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MyApp extends Application {
    Context context;
    public static MyApp myApp;

    public static MyApp getInstance() {
        return myApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        myApp = this;

        MobileAds.initialize(
                this,
                new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {}
                });
    }
}
