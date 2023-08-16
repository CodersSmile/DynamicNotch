package com.iosnotch.dynamic.AdsUtils.Interfaces;

import com.iosnotch.dynamic.AdsUtils.FirebaseADHandlers.AdsJsonPOJO;

public class AppInterfaces {
    public interface AdDataInterface {
        void getAdData(AdsJsonPOJO adsJsonPOJO);
    }

    public interface InterStitialADInterface {
        void adLoadState(boolean isLoaded);
    }
    public interface AppOpenADInterface {
        void appOpenAdState(boolean state_load);
    }
}
