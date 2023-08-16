package com.iosnotch.dynamic.notchmodule.global;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.constraintlayout.motion.widget.Key;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.view.ViewCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iosnotch.dynamic.AdsUtils.FirebaseADHandlers.AdsJsonPOJO;
import com.iosnotch.dynamic.BuildConfig;
import com.iosnotch.dynamic.MyApp;
import com.iosnotch.dynamic.R;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.iosnotch.dynamic.notchmodule.Controllers.AirPlaneModeController;
import com.iosnotch.dynamic.notchmodule.Controllers.AlarmsController;
import com.iosnotch.dynamic.notchmodule.Controllers.AudioSettingController;
import com.iosnotch.dynamic.notchmodule.Controllers.BatterySaverController;
import com.iosnotch.dynamic.notchmodule.Controllers.BlueToothSettingController;
import com.iosnotch.dynamic.notchmodule.Controllers.BrightnessModeController;
import com.iosnotch.dynamic.notchmodule.Controllers.DarkModeController;
import com.iosnotch.dynamic.notchmodule.Controllers.DataSyncController;
import com.iosnotch.dynamic.notchmodule.Controllers.DataUsageController;
import com.iosnotch.dynamic.notchmodule.Controllers.DisplaySettingIntent;
import com.iosnotch.dynamic.notchmodule.Controllers.ExpandNotificationPanel;
import com.iosnotch.dynamic.notchmodule.Controllers.HotSpotController;
import com.iosnotch.dynamic.notchmodule.Controllers.KeyBoardController;
import com.iosnotch.dynamic.notchmodule.Controllers.LocationSettingController;
import com.iosnotch.dynamic.notchmodule.Controllers.LockScreenController;
import com.iosnotch.dynamic.notchmodule.Controllers.MobileDataController;
import com.iosnotch.dynamic.notchmodule.Controllers.NFCController;
import com.iosnotch.dynamic.notchmodule.Controllers.NightModeController;
import com.iosnotch.dynamic.notchmodule.Controllers.ScreenCastController;
import com.iosnotch.dynamic.notchmodule.Controllers.ScreenRecordController;
import com.iosnotch.dynamic.notchmodule.Controllers.ScreenShotController;
import com.iosnotch.dynamic.notchmodule.Controllers.ScreenTimeController;
import com.iosnotch.dynamic.notchmodule.Controllers.ScreenTimoutController;
import com.iosnotch.dynamic.notchmodule.Controllers.SimSelectController;
import com.iosnotch.dynamic.notchmodule.Controllers.StillCameraController;
import com.iosnotch.dynamic.notchmodule.Controllers.TorchMainController;
import com.iosnotch.dynamic.notchmodule.Controllers.WifiController;
import com.iosnotch.dynamic.notchmodule.Controllers.ZenModeController;
import com.iosnotch.dynamic.notchmodule.entity.AppDetail;
import com.iosnotch.dynamic.notchmodule.entity.ButtonState;
import com.iosnotch.dynamic.utils.Constants;
import com.iosnotch.dynamic.utils.PrefManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

import static android.os.Build.VERSION.SDK_INT;
import static android.util.Config.DEBUG;
import static java.lang.Character.MAX_VALUE;

public class Utils {
    public static final String COLOR_UPDATE = "COLOR_UPDATE";
    private static final int DAY_MILLIS = 86400000;
    public static final String FROM_NOTIFICATION_SERVICE = "from_notification_service";
    private static final int HOUR_MILLIS = 3600000;
    private static final int MINUTE_MILLIS = 60000;
    private static final int SECOND_MILLIS = 1000;
    public Context context;
    boolean isLocationEnabled = false;
    private int titleTextColor;

    public void setLottiViewState(LottieAnimationView lottieAnimationView, String str, String str2, boolean z) {
    }

    public Utils(Context context2) {
        this.context = context2;
        this.titleTextColor = new PrefManager(context2).getDefaultColor();
    }

    public int getTitleTextColor() {
        return this.titleTextColor;
    }

    public void startAppNotificationSetting(Context context2) {
        Intent intent = new Intent("android.settings.ALL_APPS_NOTIFICATION_SETTINGS");
        if (context2.getPackageManager().resolveActivity(intent, 0) == null) {
            intent.setAction("android.settings.APPLICATION_SETTINGS");
        }
        intent.setFlags(268435456);
        try {
            this.context.startActivity(intent);
        } catch (Exception unused) {
            Toast.makeText(context2, "Setting not found!", 1).show();
        }
    }

    public int getTileColor() {
        return this.titleTextColor;
    }

    public void applyRoundCorner(View view) {
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(new ShapeAppearanceModel().toBuilder().setAllCorners(0, convertDpToPixel(10.0f, this.context)).build());
        materialShapeDrawable.setFillColor(ContextCompat.getColorStateList(this.context, R.color.transparentFullWhite));
        ViewCompat.setBackground(view, materialShapeDrawable);
    }

    public void setTileColor(int i) {
        this.titleTextColor = i;
    }

    public boolean isSupportHardwareCamera(Context context2) {
        return context2.getPackageManager().hasSystemFeature("android.hardware.camera.flash");
    }

    public static boolean checkIfActivityFound(Context context2, Intent intent) {
        return context2.getPackageManager().resolveActivity(intent, 0) != null;
    }

    public ArrayList<ButtonState> getButtonsList() {
        ArrayList arrayList = new ArrayList(Arrays.asList((this.context.getString(R.string.quick_settings_tiles_default) + this.context.getResources().getString(R.string.quick_settings_tiles_more)).split(",")));
        ArrayList<ButtonState> arrayList2 = new ArrayList<>();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ButtonState buttonSate = getButtonSate((String) it.next());
            if (buttonSate != null) {
                arrayList2.add(buttonSate);
            }
        }
        return arrayList2;
    }

    public ButtonState getButtonSate(String str) {
        str.hashCode();
        int hashCode = str.hashCode();
        char c = MAX_VALUE;
        switch (hashCode) {
            case -1367751899:
                if (str.equals("camera")) {
                    c = 0;
                    break;
                }
                break;
            case -1313911455:
                if (str.equals("timeout")) {
                    c = 1;
                    break;
                }
                break;
            case -1183073498:
                if (str.equals("flashlight")) {
                    c = 2;
                    break;
                }
                break;
            case -930895139:
                if (str.equals("ringer")) {
                    c = 3;
                    break;
                }
                break;
            case -907689876:
                if (str.equals("screen")) {
                    c = 4;
                    break;
                }
                break;
            case -677011630:
                if (str.equals("airplane")) {
                    c = 5;
                    break;
                }
                break;
            case -331239923:
                if (str.equals("battery")) {
                    c = 6;
                    break;
                }
                break;
            case -40300674:
                if (str.equals(Key.ROTATION)) {
                    c = 7;
                    break;
                }
                break;
            case 3154:
                if (str.equals("bt")) {
                    c = 8;
                    break;
                }
                break;
            case 3680:
                if (str.equals("ss")) {
                    c = 9;
                    break;
                }
                break;
            case 99610:
                if (str.equals("dnd")) {
                    c = 10;
                    break;
                }
                break;
            case 108971:
                if (str.equals("nfc")) {
                    c = 11;
                    break;
                }
                break;
            case 112784:
                if (str.equals("rec")) {
                    c = 12;
                    break;
                }
                break;
            case 113879:
                if (str.equals("sim")) {
                    c = 13;
                    break;
                }
                break;
            case 3046207:
                if (str.equals("cast")) {
                    c = 14;
                    break;
                }
                break;
            case 3049826:
                if (str.equals("cell")) {
                    c = 15;
                    break;
                }
                break;
            case 3075958:
                if (str.equals("dark")) {
                    c = 16;
                    break;
                }
                break;
            case 3327275:
                if (str.equals("lock")) {
                    c = 17;
                    break;
                }
                break;
            case 3545755:
                if (str.equals("sync")) {
                    c = 18;
                    break;
                }
                break;
            case 3649301:
                if (str.equals("wifi")) {
                    c = 19;
                    break;
                }
                break;
            case 94755854:
                if (str.equals("clock")) {
                    c = 20;
                    break;
                }
                break;
            case 104817688:
                if (str.equals("night")) {
                    c = 21;
                    break;
                }
                break;
            case 109211285:
                if (str.equals("saver")) {
                    c = 22;
                    break;
                }
                break;
            case 109770518:
                if (str.equals("stock")) {
                    c = 23;
                    break;
                }
                break;
            case 503739367:
                if (str.equals("keyboard")) {
                    c = 24;
                    break;
                }
                break;
            case 648162385:
                if (str.equals("brightness")) {
                    c = 25;
                    break;
                }
                break;
            case 1099603663:
                if (str.equals("hotspot")) {
                    c = 26;
                    break;
                }
                break;
            case 1901043637:
                if (str.equals("location")) {
                    c = 27;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return new StillCameraController(this.context);
            case 1:
                return new ScreenTimoutController(this.context);
            case 2:
                return new TorchMainController(this.context);
            case 3:
                return new AudioSettingController(this.context);
            case 4:
                return new ScreenTimeController(this.context);
            case 5:
                return new AirPlaneModeController(this.context);
            case 6:
                return new BatterySaverController(this.context);
            case 7:
                return new DisplaySettingIntent(this.context);
            case 8:
                return new BlueToothSettingController(this.context);
            case 9:
                return new ScreenRecordController(this.context);
            case 10:
                return new ZenModeController(this.context);
            case 11:
                return new NFCController(this.context);
            case 12:
                return new ScreenShotController(this.context);
            case 13:
                try {
                    SubscriptionManager subscriptionManager = (SubscriptionManager) this.context.getSystemService("telephony_subscription_service");
                    if (ActivityCompat.checkSelfPermission(this.context, "android.permission.READ_PHONE_STATE") == 0 && subscriptionManager.getActiveSubscriptionInfoList().size() == 2) {
                        return new SimSelectController(this.context, subscriptionManager);
                    }
                } catch (Throwable unused) {
                }
                return null;
            case 14:
                return new ScreenCastController(this.context);
            case 15:
                return new MobileDataController(this.context);
            case 16:
                return new DarkModeController(this.context);
            case 17:
                if (Build.VERSION.SDK_INT < 28) {
                    return null;
                }
                return new LockScreenController(this.context);
            case 18:
                return new DataSyncController(this.context);
            case 19:
                return new WifiController(this.context);
            case 20:
                return new AlarmsController(this.context);
            case 21:
                return new NightModeController(this.context);
            case 22:
                if (Build.VERSION.SDK_INT < 24) {
                    return null;
                }
                return new DataUsageController(this.context);
            case 23:
                return new ExpandNotificationPanel(this.context);
            case 24:
                return new KeyBoardController(this.context);
            case 25:
                return new BrightnessModeController(this.context);
            case 26:
                return new HotSpotController(this.context);
            case 27:
                return new LocationSettingController(this.context);
            default:
                return null;
        }
    }

    public void setBatteryImage(LottieAnimationView lottieAnimationView, boolean z) {
        if (!z) {
            lottieAnimationView.setMinAndMaxFrame("" + (getBatteryLevel() / 10));
            setLottieAnimColor(lottieAnimationView, this.context.getResources().getColor(R.color.on_text));
            return;
        }
        lottieAnimationView.setMinAndMaxFrame("charging");
        setLottieAnimColor(lottieAnimationView, this.context.getResources().getColor(R.color.on_text));
    }

    public int getAirpodsBattery() {
        int airPodLevel = getAirPodLevel();
        if (airPodLevel == -1) {
            return -1;
        }
        int i = airPodLevel / 10;
        int i2 = R.drawable.airbug_00;
        if (i == 1) {
            i2 = R.drawable.airbug_01;
        }
        if (i == 2) {
            i2 = R.drawable.airbug_02;
        }
        if (i == 3) {
            i2 = R.drawable.airbug_03;
        }
        if (i == 4) {
            i2 = R.drawable.airbug_04;
        }
        if (i == 5) {
            i2 = R.drawable.airbug_05;
        }
        if (i == 6) {
            i2 = R.drawable.airbug_06;
        }
        if (i == 7) {
            i2 = R.drawable.airbug_07;
        }
        if (i == 8) {
            i2 = R.drawable.airbug_08;
        }
        if (i == 9) {
            i2 = R.drawable.airbug_09;
        }
        return i == 10 ? R.drawable.airbug_10 : i2;
    }

    public int getBatteryImage() {
        int batteryLevel = getBatteryLevel() / 10;
        int i = R.drawable.battery_00;
        if (batteryLevel == 1) {
            i = R.drawable.battery_01;
        }
        if (batteryLevel == 2) {
            i = R.drawable.battery_02;
        }
        if (batteryLevel == 3) {
            i = R.drawable.battery_03;
        }
        if (batteryLevel == 4) {
            i = R.drawable.battery_04;
        }
        if (batteryLevel == 5) {
            i = R.drawable.battery_05;
        }
        if (batteryLevel == 6) {
            i = R.drawable.battery_06;
        }
        if (batteryLevel == 7) {
            i = R.drawable.battery_07;
        }
        if (batteryLevel == 8) {
            i = R.drawable.battery_08;
        }
        if (batteryLevel == 9) {
            i = R.drawable.battery_09;
        }
        return batteryLevel == 10 ? R.drawable.battery_10 : i;
    }

    public int getAirPodLevel() {
        try {
            if (BluetoothAdapter.getDefaultAdapter() == null || ActivityCompat.checkSelfPermission(this.context, "android.permission.BLUETOOTH_CONNECT") != 0) {
                return -1;
            }
            int i = -1;
            for (BluetoothDevice address : BluetoothAdapter.getDefaultAdapter().getBondedDevices()) {
                try {
                    BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(address.getAddress());
                    i = ((Integer) remoteDevice.getClass().getMethod("getBatteryLevel", new Class[0]).invoke(remoteDevice, new Object[0])).intValue();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return i;
        } catch (Exception unused) {
        }
        return -1;
    }

    public int getBatteryLevel() {
        return ((BatteryManager) this.context.getSystemService("batterymanager")).getIntProperty(4);
    }

    public int getHeight(Context context2) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context2.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public int getWidth(Context context2) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context2.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public float convertDpToPixel(float f, Context context2) {
        return f * (((float) context2.getResources().getDisplayMetrics().densityDpi) / 160.0f);
    }

    public float convertPixelsToDp(float f, Context context2) {
        return f / (((float) context2.getResources().getDisplayMetrics().densityDpi) / 160.0f);
    }

    public int getBrightMode(Context context2) {
        try {
            return Settings.System.getInt(context2.getContentResolver(), "screen_brightness_mode");
        } catch (Exception e) {
            Log.d("tag", e.toString());
            return 0;
        }
    }

    public boolean isWifiOn(Context context2) {
        WifiManager wifiManager = (WifiManager) context2.getApplicationContext().getSystemService("wifi");
        try {
            Method declaredMethod = Class.forName(wifiManager.getClass().getName()).getDeclaredMethod("isWifiEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(wifiManager, new Object[0])).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isHotspotOn(Context context2) {
        WifiManager wifiManager = (WifiManager) context2.getApplicationContext().getSystemService("wifi");
        try {
            Method declaredMethod = Class.forName(wifiManager.getClass().getName()).getDeclaredMethod("getWifiApState", new Class[0]);
            declaredMethod.setAccessible(true);
            Object[] objArr = null;
            int intValue = ((Integer) declaredMethod.invoke(wifiManager, (Object[]) null)).intValue();
            if (intValue != 11 && intValue == 13) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void getBoxBrightness(AppCompatSeekBar appCompatSeekBar, Context context2) {
        float f;
        try {
            f = (float) Settings.System.getInt(context2.getContentResolver(), "screen_brightness");
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            f = 0.0f;
        }
        appCompatSeekBar.setProgress((int) f);
    }

    public void setBrightness(int i, Context context2) {
        if (getBrightMode(context2) == 1) {
            Settings.System.putInt(context2.getContentResolver(), "screen_brightness_mode", 0);
        }
        Settings.System.putInt(context2.getContentResolver(), "screen_brightness", i);
    }

    public void modifyAirplanemode(Context context2) {
        try {
            Intent intent = new Intent("android.settings.WIRELESS_SETTINGS");
            intent.setFlags(268435456);
            context2.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e("exception", e + "");
        }
    }

    public void playRingTone() {
        RingtoneManager.getRingtone(this.context, RingtoneManager.getActualDefaultRingtoneUri(this.context.getApplicationContext(), 1)).play();
    }

    public void stopRingTone() {
        RingtoneManager.getRingtone(this.context, RingtoneManager.getActualDefaultRingtoneUri(this.context.getApplicationContext(), 1)).play();
    }

    public int getDndState() {
        AudioManager audioManager = (AudioManager) this.context.getSystemService("audio");
        if (audioManager != null) {
            return audioManager.getRingerMode();
        }
        return 0;
    }

    public void changeSoundMode(Context context2, boolean z, LottieAnimationView lottieAnimationView) {
        TextView textView = (TextView) ((RelativeLayout) lottieAnimationView.getParent()).getChildAt(1);
        AudioManager audioManager = (AudioManager) context2.getSystemService("audio");
        int ringerMode = audioManager != null ? audioManager.getRingerMode() : 0;
        Vibrator vibrator = (Vibrator) context2.getSystemService("vibrator");
        boolean hasVibrator = vibrator != null ? vibrator.hasVibrator() : false;
        if (audioManager == null) {
            return;
        }
        if (hasVibrator) {
            if (ringerMode == 2) {
                if (z) {
                    audioManager.setRingerMode(0);
                    textView.setText("Mute");
                    setLottiViewState(lottieAnimationView, "sound", "mute", false);
                    return;
                }
                textView.setText("Sound");
                setLottiViewState(lottieAnimationView, "start", "sound", true);
            } else if (ringerMode == 0) {
                if (z) {
                    audioManager.setRingerMode(1);
                    textView.setText("Vibrate");
                    setLottiViewState(lottieAnimationView, "mute", "viberate", false);
                    return;
                }
                textView.setText("Mute");
                setLottiViewState(lottieAnimationView, "sound", "mute", false);
            } else if (ringerMode != 1) {
            } else {
                if (z) {
                    audioManager.setRingerMode(2);
                    textView.setText("Sound");
                    setLottiViewState(lottieAnimationView, "start", "sound", true);
                    return;
                }
                textView.setText("Vibrate");
                setLottiViewState(lottieAnimationView, "mute", "viberate", false);
            }
        } else if (ringerMode == 2) {
            if (z) {
                audioManager.setRingerMode(0);
                textView.setText("Mute");
                setLottiViewState(lottieAnimationView, "sound", "mute", false);
                return;
            }
            textView.setText("Sound");
            setLottiViewState(lottieAnimationView, "start", "sound", true);
        } else if (ringerMode != 0) {
        } else {
            if (z) {
                audioManager.setRingerMode(2);
                textView.setText("Sound");
                setLottiViewState(lottieAnimationView, "start", "sound", true);
                return;
            }
            textView.setText("Mute");
            setLottiViewState(lottieAnimationView, "sound", "mute", false);
        }
    }

    public void isAutoRotateOn(Context context2, LottieAnimationView lottieAnimationView, LottieAnimationView lottieAnimationView2) {
        if (!(Settings.System.getInt(context2.getContentResolver(), "accelerometer_rotation", 0) == 1)) {
            Constants.setAutoOrientationEnabled(context2, true);
            setLottiViewState(lottieAnimationView, "start", "mid", true);
            setLottiViewState(lottieAnimationView2, "start", "mid", true);
            return;
        }
        Constants.setAutoOrientationEnabled(context2, false);
        setLottiViewState(lottieAnimationView, "mid", "end", false);
        setLottiViewState(lottieAnimationView2, "mid", "end", false);
    }

    public void openDirectory(Uri uri) {
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
        intent.addFlags(1);
        intent.putExtra("android.provider.extra.INITIAL_URI", uri);
        this.context.startActivity(intent);
    }

    public boolean isAirplaneModeOn(Context context2) {
        return Settings.Global.getInt(context2.getContentResolver(), "airplane_mode_on", 0) != 0;
    }

    public void isAirplaneModeOn(Context context2, LottieAnimationView lottieAnimationView, LottieAnimationView lottieAnimationView2) {
        if (Settings.Global.getInt(context2.getContentResolver(), "airplane_mode_on", 0) != 0) {
            setLottiViewState(lottieAnimationView, "start", "mid", true);
            setLottiViewState(lottieAnimationView2, "start", "mid", true);
            return;
        }
        setLottiViewState(lottieAnimationView, "mid", "end", false);
        setLottiViewState(lottieAnimationView2, "mid", "end", false);
    }

    public void isBluetoothOn(LottieAnimationView lottieAnimationView) {
        if (BluetoothAdapter.getDefaultAdapter() == null) {
            setLottiViewState(lottieAnimationView, "mid", "end", false);
        } else if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            setLottiViewState(lottieAnimationView, "start", "mid", true);
        } else {
            setLottiViewState(lottieAnimationView, "mid", "end", false);
        }
    }

    public void mobilecheack(Context context2, LottieAnimationView lottieAnimationView) {
        if (getMobileDataState(context2)) {
            setLottiViewState(lottieAnimationView, "start", "mid", true);
        } else {
            setLottiViewState(lottieAnimationView, "start", "mid", false);
        }
    }

    public boolean isSimPresent() {
        return ((TelephonyManager) this.context.getSystemService("phone")).getSimState() == 5;
    }

    public boolean isConnectedToNetwork() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == 0;
    }

    public boolean isMobileDataEnable(Context context2) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context2.getSystemService("connectivity");
        try {
            Method declaredMethod = Class.forName(connectivityManager.getClass().getName()).getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(connectivityManager, new Object[0])).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public void setMobileDataState(boolean z, Context context2) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context2.getSystemService("phone");
            ((TelephonyManager) Objects.requireNonNull(telephonyManager)).getClass().getDeclaredMethod("setDataEnabled", new Class[]{Boolean.TYPE}).invoke(telephonyManager, new Object[]{Boolean.valueOf(z)});
        } catch (Exception e) {
            Log.e("MainActivity", "Error setting mobile data state", e);
        }
    }

    public boolean getMobileDataState(Context context2) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context2.getSystemService("phone");
            return ((Boolean) telephonyManager.getClass().getDeclaredMethod("getDataEnabled", new Class[0]).invoke(telephonyManager, new Object[0])).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void gpsstate(Context context2, LottieAnimationView lottieAnimationView) {
        if (((LocationManager) context2.getSystemService("location")).isProviderEnabled("gps")) {
            setLottiViewState(lottieAnimationView, "start", "mid", true);
            this.isLocationEnabled = true;
            return;
        }
        setLottiViewState(lottieAnimationView, "mid", "end", false);
        this.isLocationEnabled = false;
    }

    public void setWifiState(boolean z, LottieAnimationView lottieAnimationView) {
        if (z) {
            setLottiViewState(lottieAnimationView, "start", "mid", true);
        } else {
            setLottiViewState(lottieAnimationView, "mid", "end", false);
        }
    }

    public boolean isWifiOn(Intent intent, LottieAnimationView lottieAnimationView) {
        if (((NetworkInfo) intent.getParcelableExtra("networkInfo")).isConnected()) {
            setLottiViewState(lottieAnimationView, "start", "mid", true);
            return true;
        }
        setLottiViewState(lottieAnimationView, "mid", "end", false);
        return false;
    }

    public void animateViewHeight(final View view, int i) {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{view.getMeasuredHeight(), i});
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = intValue;
                view.setLayoutParams(layoutParams);
            }
        });
        ofInt.setDuration(250);
        ofInt.start();
    }

    public void animateViewWeightSum(final LinearLayout linearLayout, float f) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{linearLayout.getWeightSum(), f});
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                linearLayout.setWeightSum(((Float) valueAnimator.getAnimatedValue()).floatValue());
                linearLayout.requestLayout();
            }
        });
        ofFloat.setDuration(250);
        ofFloat.start();
    }

    public void animateViewAlpha(final View view, final float f) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{view.getAlpha(), f});
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        ofFloat.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                if (f == 1.0f) {
                    view.setAlpha(0.0f);
                    view.setVisibility(0);
                }
            }

            public void onAnimationEnd(Animator animator) {
                if (f == 0.0f) {
                    view.setVisibility(8);
                }
            }
        });
        ofFloat.setDuration(250);
        ofFloat.start();
    }

    public void setLottieAnimColor(LottieAnimationView lottieAnimationView, final int i) {
        lottieAnimationView.addValueCallback(new KeyPath("**"), LottieProperty.COLOR_FILTER, new SimpleLottieValueCallback<ColorFilter>() {
            public ColorFilter getValue(LottieFrameInfo<ColorFilter> lottieFrameInfo) {
                return new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_ATOP);
            }
        });
    }

    public Bitmap getBitmapFromByteArray(byte[] bArr) {
        if (bArr != null) {
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }
        return null;
    }

    public void stopForegroundService(Context context2) {
        if (Build.VERSION.SDK_INT >= 29) {
            ((Service) context2).stopForeground(true);
        }
    }

    public void startForegroundService(Context context2) {
        if (Build.VERSION.SDK_INT >= 29) {
            Service service = (Service) context2;
            NotificationManager notificationManager = (NotificationManager) context2.getSystemService(NotificationManager.class);
            if (notificationManager.getNotificationChannel("FOREGROUND_INFO") == null) {
                NotificationChannel notificationChannel = new NotificationChannel("FOREGROUND_INFO", "Keep Alive", 2);
                notificationChannel.setSound((Uri) null, (AudioAttributes) null);
                notificationChannel.setVibrationPattern((long[]) null);
                notificationChannel.setShowBadge(false);
                notificationChannel.setDescription("Useful to keep the app running in the background");
                notificationManager.createNotificationChannel(notificationChannel);
            }
            Notification.BigTextStyle summaryText = new Notification.BigTextStyle().setSummaryText("Keep Alive");
            Notification.Builder builder = new Notification.Builder(context2, "FOREGROUND_INFO");
            builder.setSmallIcon(R.drawable.info);
            builder.setColor(context2.getResources().getColor(R.color.colorAccent));
            builder.setStyle(summaryText).setContentTitle("Running").setContentText("This notification helps to keep the app running");
            builder.setPriority(-1);
            service.startForeground(99, builder.build());
        }
    }

    public int getStatusBarHeight(Resources resources) {
        int identifier = resources.getIdentifier("android:dimen/status_bar_height", (String) null, (String) null);
        if (identifier > 0) {
            return resources.getDimensionPixelOffset(identifier);
        }
        return (int) Math.ceil((double) (((float) (Build.VERSION.SDK_INT >= 23 ? 24 : 25)) * resources.getDisplayMetrics().density));
    }

    public String getFormatedDate(long j) {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        if (j > timeInMillis || j <= 0) {
            return null;
        }
        long j2 = timeInMillis - j;
        if (j2 < 60000) {
            return "now";
        }
        if (j2 < 120000) {
            return "1m";
        }
        if (j2 < 3000000) {
            return (j2 / 60000) + " m";
        }
        if (j2 < 5400000) {
            return "1h";
        }
        if (j2 < 86400000) {
            return (j2 / 3600000) + " h";
        }
        if (j2 < 172800000) {
            return "1d";
        }
        return (j2 / 86400000) + " d";
    }

    public String getFormatedDate1(long j) {
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(j);
        if (instance2.get(1) != instance.get(1)) {
            return DateFormat.format("MMMM dd yyyy, HH:mm", instance2).toString();
        }
        if (instance2.get(2) != instance.get(2)) {
            return DateFormat.format("MMMM d, HH:mm", instance2).toString();
        }
        if (instance2.get(5) - instance.get(5) == 1) {
            return "Tomorrow at " + DateFormat.format("HH:mm", instance2);
        }
        if (instance.get(5) == instance2.get(5)) {
            return "Today at " + DateFormat.format("HH:mm", instance2);
        }
        if (instance.get(5) - instance2.get(5) == 1) {
            return "Yesterday at " + DateFormat.format("HH:mm", instance2);
        }
        return DateFormat.format("MMMM d, HH:mm", instance2).toString();
    }

    public static ActivityInfo getActivityInfo(Context context2, String str, String str2) {
        Intent intent = new Intent();
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setAction("android.intent.action.MAIN");
        intent.setPackage(str);
        intent.setComponent(new ComponentName(str, str2));
        ResolveInfo resolveActivity = context2.getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity != null) {
            return resolveActivity.activityInfo;
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static ArrayList<AppDetail> sortAppsAlphabetically(ArrayList<AppDetail> arrayList) {
        ArrayList<AppDetail> arrayList2 = new ArrayList<>(arrayList);
        arrayList.clear();
        arrayList2.sort(new Comparator<AppDetail>() {
            public int compare(AppDetail appDetail, AppDetail appDetail2) {
                return appDetail.label.compareToIgnoreCase(appDetail2.label);
            }
        });
        return arrayList2;
    }

    public static String ISPRIVACYFIRST = "ISPRIVACYFIRST";
    public static String ISOUND = "ISOUND";
    public static String ISNOTIFY = "ISNOTIFY";
    public static String ISVIBRATE = "ISVIBRATE";
    public static String ProfilePath = "Anims/anim1.png";
    public static String mPath = null;
    public static int mRoomId = 1;
    public static int mCallId = 1;
    public static int mTotalUsers = 10;
    public static int mTotalBoys = 5;
    public static int mTotalGirls = 4;
    public static String DND="DoNotDisturb";
    public static String WHATIS="voice";
    public static String TEXTEXTRACT="voice";
    ProgressDialog progressDialog;

    public static String getDataDirPath(Context context) throws Exception {
        return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.dataDir;
    }

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static Shader getShader(TextView textView) {
        float width = textView.getPaint().measureText(textView.getText().toString());
        Shader shader = new LinearGradient(0, 0, width, textView.getTextSize(),
                MyApp.getInstance().getResources().getColor(R.color.colorBackground), MyApp.getInstance().getResources().getColor(R.color.colorOnBackground), Shader.TileMode.CLAMP);
        return shader;
    }

    public static boolean isEmptyStr(String str) {
        if (str == null || str.trim().isEmpty() || str.equalsIgnoreCase(""))
            return true;
        return false;
    }

    public static File getRootFileForMoveCopy() {
        if (SDK_INT >= Build.VERSION_CODES.Q) {
            return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        } else {
            return Environment.getExternalStorageDirectory();
        }
    }

    public static Uri getUri(File file, Context context) {
        Uri contentUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            contentUri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file);
        } else {
            contentUri = Uri.fromFile(file);
        }
        return contentUri;
    }

    public static Uri getContentMediaUri() {
        if (isLatestVersion()) {
            return MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
        } else {
            return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
    }

    public static boolean isLatestVersion() {
        return SDK_INT >= Build.VERSION_CODES.R;
    }

    public static String getPath(final Context context, final Uri uri) {
        if (DEBUG)
            Log.d("TAG" + " File -",
                    "Authority: " + uri.getAuthority() +
                            ", Fragment: " + uri.getFragment() +
                            ", Port: " + uri.getPort() +
                            ", Query: " + uri.getQuery() +
                            ", Scheme: " + uri.getScheme() +
                            ", Host: " + uri.getHost() +
                            ", Segments: " + uri.getPathSegments().toString()
            );

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // LocalStorageProvider
            if (isLocalStorageDocument(uri)) {
                // The path is the id
                return DocumentsContract.getDocumentId(uri);
            }
            // ExternalStorageProvider
            else if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                } else {
                    // TODO handle non-primary volumes
                    return "/storage/" + split[0] + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = getContentMediaUri();
                } else if ("video".equals(type)) {
                    contentUri = getContentVideoUri();
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static Uri getContentVideoUri() {
        if (isLatestVersion()) {
            return MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
        } else {
            return MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        }
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                if (DEBUG)
                    DatabaseUtils.dumpCursor(cursor);

                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static final String AUTHORITY = BuildConfig.APPLICATION_ID;

    public static boolean isLocalStorageDocument(Uri uri) {
        return AUTHORITY.equals(uri.getAuthority());
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static File getRootFileForSave() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        } else {
            return Environment.getExternalStorageDirectory();
        }
    }

    static AlertDialog alertDialog;
    private static ProgressDialog dialog;

    @SuppressLint("LogNotTimber")
    public static void displayProgress(Context context) {

        try {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.progressbar, null);
            dialogBuilder.setView(dialogView);

            if (alertDialog == null) {
                alertDialog = dialogBuilder.create();

            }
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            alertDialog.setCancelable(false);

            WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            lp.gravity = Gravity.CENTER;
            lp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            lp.dimAmount = 0.5f;
            alertDialog.getWindow().setAttributes(lp);

            if (!alertDialog.isShowing())
                alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dismissProgress() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                dialog = null;
            }

            if (alertDialog != null && alertDialog.isShowing()) {
                alertDialog.dismiss();
                alertDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SecureRandom rnd = new SecureRandom();
    private static final int abLength = 62;

    public static String getRandomString(int i) {
        StringBuilder sb = new StringBuilder(i);
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".charAt(rnd.nextInt(abLength)));
        }
        return sb.toString();
    }

    public static File getCacheFile(String str, Context context) {
        try {
            File externalStorageDirectory = new File(Utils.getDataDirPath(context));
            File file = new File(externalStorageDirectory.getAbsolutePath() + "/Profile");
            file.mkdirs();
            try {
                new File(file, "nomedia").createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            File file2 = new File(file, str);
            try {
                file2.createNewFile();
            } catch (IOException unused) {
            }
            return file2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getImageData(File path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path.getAbsolutePath(), options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(path.getAbsolutePath(), bmOptions);
        bitmap = Bitmap.createScaledBitmap(bitmap, imageWidth, imageHeight, true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String encodedImageString = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImageString;
    }

    public static String getImageData(Bitmap path) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        path.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String encodedImageString = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImageString;
    }

    public static Bitmap getBitmapData(String str) {
        /*byte[] tmp2 = Base64.decode(str, Base64.URL_SAFE);
        String decodedString = null;
        try {
            decodedString = new String(tmp2, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(tmp2, 0, tmp2.length);
        byte[] bytarray = Base64.decode(str, Base64.DEFAULT);
        Bitmap bmimage = BitmapFactory.decodeByteArray(bytarray, 0,
                bytarray.length);
        return bmimage;
    }

    public static boolean isNetworkAvailable(Context context) {
        boolean isNetworkAvail = false;
        if (context == null) return isNetworkAvail;
        try {
            ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
            isNetworkAvail = connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isNetworkAvail;
    }
    public static void printLog(String key, String val) {
        if (BuildConfig.DEBUG)
            Log.e(key + "*===", "===*" + val);
    }

    public static AdsJsonPOJO getAdsData(String json) {
        Type familyType = new TypeToken<AdsJsonPOJO>() {
        }.getType();
        return new Gson().fromJson(json, familyType);

    }
    public void showProgressDialog(Activity activity, String msg) {
        displayProgress(activity);
    }
    public void hideProgressDialog() {
        try {
            dismissProgress();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
