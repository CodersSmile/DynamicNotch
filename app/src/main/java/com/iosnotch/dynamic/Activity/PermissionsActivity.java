package com.iosnotch.dynamic.Activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.core.app.ActivityCompat;

import com.iosnotch.dynamic.R;
import com.iosnotch.dynamic.base.BaseActivity;
import com.iosnotch.dynamic.notchmodule.services.MAccessibilityService;
import com.iosnotch.dynamic.notchmodule.services.NotificationService;
import com.iosnotch.dynamic.utils.Constants;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

public class PermissionsActivity extends BaseActivity {
    private boolean bluetoothPermissionGranted;
    @BindView(R.id.textViewLockscreen)
    TextView textViewLockscreen;
    @BindView(R.id.toggle_enable)
    ToggleButton toggleEnable;
    @BindView(R.id.tv_enable_notification)
    TextView tvEnableNotification;
    @BindView(R.id.toggle_notification_access)
    ToggleButton toggleNotificationAccess;
    @BindView(R.id.toggle_bluetooth_access)
    ToggleButton toggle_bluetooth_access;
    Context mContxt;
    @BindView(R.id.mTxtNext)
    TextView mTxtNext;
    @BindView(R.id.tv_enable__bluetooth_access)
    TextView tv_enable__bluetooth_access;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_permissions;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mContxt = this;
//        if (Constants.checkAccessibilityEnabled(this.mContxt)) {
            if (prefManager.isFirstTimeDetail()) {
                startActivity(new Intent(this.mContxt, PrivacyActivity.class));
            } else {
                startActivity(new Intent(this.mContxt, HomeActivity.class));
            }
            finish();
//        }
        if (Constants.checkAccessibilityEnabled(this.mContxt)) {
            this.toggleEnable.setChecked(true);
        } else {
            this.toggleEnable.setChecked(false);
        }
        this.toggleEnable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    enableLock();
                    return;
                }
                stopService(mContxt, 0);
                mTxtNext.setVisibility(View.INVISIBLE);
            }

            public void m82x9e5f95c4() {
                enableLock();
            }

            public void m83xa5c4cae3() {
                finish();
            }
        });
        if (Constants.getNotif(this.mContxt)) {
            this.toggleNotificationAccess.setChecked(true);
        } else {
            this.toggleNotificationAccess.setChecked(false);
        }
        this.toggleNotificationAccess.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    mEnableNotification(z);
                }
            }

            public void m84x9e5f95c5(boolean z) {
                try {
                    Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
                    Bundle bundle = new Bundle();
                    String str = mContxt.getPackageName() + "/" + NotificationService.class.getName();
                    bundle.putString(":settings:fragment_args_key", str);
                    intent.putExtra(":settings:fragment_args_key", str);
                    intent.putExtra(":settings:show_fragment_args", bundle);
                    PermissionsActivity permissionsActivity = PermissionsActivity.this;
                    permissionsActivity.startActivity(intent, ActivityOptions.makeCustomAnimation(permissionsActivity.mContxt, R.anim.fade_in, R.anim.fade_out).toBundle());
                } catch (Exception unused) {
                    Toast.makeText(mContxt, "Notification service activity not found.\nPlease grant permission manually", 1).show();
                }
                Constants.setNotif(mContxt, z);
            }

            public void m85xa5c4cae4() {
                finish();
            }
        });
        if (Build.VERSION.SDK_INT >= 31) {
            findViewById(R.id.enable__bluetooth_access_rl).setVisibility(0);
            if (ActivityCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                ((ToggleButton) findViewById(R.id.toggle_bluetooth_access)).setChecked(false);
                this.bluetoothPermissionGranted = false;
            } else {
                ((ToggleButton) findViewById(R.id.toggle_bluetooth_access)).setChecked(true);
                this.bluetoothPermissionGranted = true;
            }
            ((ToggleButton) findViewById(R.id.toggle_bluetooth_access)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    EasyPermissions.requestPermissions(PermissionsActivity.this, getString(R.string.blutooth_permission_txt), 16, Constants.BLUETOOTH_PERMISSION);

                }
            });
            return;
        }
        this.bluetoothPermissionGranted = true;
    }

    private void mEnableNotification(boolean z) {
        try {
            Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            Bundle bundle = new Bundle();
            String str = PermissionsActivity.this.mContxt.getPackageName() + "/" + NotificationService.class.getName();
            bundle.putString(":settings:fragment_args_key", str);
            intent.putExtra(":settings:fragment_args_key", str);
            intent.putExtra(":settings:show_fragment_args", bundle);
            PermissionsActivity permissionsActivity = PermissionsActivity.this;
            permissionsActivity.startActivity(intent, ActivityOptions.makeCustomAnimation(permissionsActivity.mContxt, R.anim.fade_in, R.anim.fade_out).toBundle());
        } catch (Exception unused) {
            Toast.makeText(PermissionsActivity.this.mContxt, "Notification service activity not found.\nPlease grant permission manually", 1).show();
        }
        Constants.setNotif(PermissionsActivity.this.mContxt, z);
    }

    @OnClick({R.id.mTxtNext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mTxtNext:
                mNextActivity();
                break;
        }
    }

    private void mNextActivity() {
        if (Constants.checkAccessibilityEnabled(this.mContxt)) {
            if (prefManager.isFirstTimeDetail()) {
                startActivity(new Intent(this.mContxt, PrivacyActivity.class));
            } else {
                startActivity(new Intent(this.mContxt, HomeActivity.class));
            }
            finish();
        } else {
            Toast.makeText(mContxt, "Allow Accessibility permission for this App..!", Toast.LENGTH_SHORT).show();
        }

    }

    public void enableLock() {
        Intent intent = new Intent("com.samsung.accessibility.installed_service");
        if (intent.resolveActivity(this.mContxt.getPackageManager()) == null) {
            intent = new Intent("android.settings.ACCESSIBILITY_SETTINGS");
        }
        Bundle bundle = new Bundle();
        String str = this.mContxt.getPackageName() + "/" + MAccessibilityService.class.getName();
        bundle.putString(":settings:fragment_args_key", str);
        intent.putExtra(":settings:fragment_args_key", str);
        intent.putExtra(":settings:show_fragment_args", bundle);
        StartPermissionActivity(this.mContxt, intent);
    }

    public void StartPermissionActivity(Context context, Intent intent) {
        context.startActivity(intent, ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out).toBundle());
    }

    public static void stopService(Context context, int i) {
        try {
            context.startService(new Intent(context, MAccessibilityService.class).putExtra("com.control.center.intent.MESSAGE", i));
        } catch (Throwable unused) {
        }
    }
}