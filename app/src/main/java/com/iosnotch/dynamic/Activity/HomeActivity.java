package com.iosnotch.dynamic.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.iosnotch.dynamic.R;
import com.iosnotch.dynamic.base.BaseActivity;
import com.iosnotch.dynamic.fragment.HomeFragment;
import com.iosnotch.dynamic.fragment.SettingFragment;
import com.iosnotch.dynamic.fragment.WidgetFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {


    @BindView(R.id.mFrameLayout)
    FrameLayout mFrameLayout;
    @BindView(R.id.mIVHome)
    ImageView mIVHome;
    @BindView(R.id.mTxtHome)
    TextView mTxtHome;
    @BindView(R.id.mIVWidget)
    ImageView mIVWidget;
    @BindView(R.id.mTxtWidget)
    TextView mTxtWidget;
    @BindView(R.id.mIVSetting)
    ImageView mIVSetting;
    @BindView(R.id.mTxtSetting)
    TextView mTxtSetting;
    int pos = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mSetPositions(1);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mFrameLayout, fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    @OnClick({R.id.mLLHome, R.id.mLLWidget, R.id.mLLSetting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mLLHome:
                if (pos != 1)
                    mSetPositions(1);
                break;
            case R.id.mLLWidget:
                if (pos != 2)
                    mSetPositions(2);
                break;
            case R.id.mLLSetting:
                if (pos != 3)
                    mSetPositions(3);
                break;
        }
    }

    private void mSetPositions(int i) {
        pos = i;
        mIVHome.setImageDrawable(ContextCompat.getDrawable(HomeActivity.this, R.drawable.ic_home_unselected));
        mIVSetting.setImageDrawable(ContextCompat.getDrawable(HomeActivity.this, R.drawable.ic_setting_unselected));
        mIVWidget.setImageDrawable(ContextCompat.getDrawable(HomeActivity.this, R.drawable.ic_more_unselected));
        mTxtHome.setSelected(false);
        mTxtSetting.setSelected(false);
        mTxtWidget.setSelected(false);
        switch (i) {
            case 2:
                replaceFragment(new WidgetFragment());
                mTxtWidget.setSelected(true);
                mIVWidget.setImageDrawable(ContextCompat.getDrawable(HomeActivity.this, R.drawable.ic_more_selected));
                break;
            case 3:
                replaceFragment(new SettingFragment());
                mIVSetting.setImageDrawable(ContextCompat.getDrawable(HomeActivity.this, R.drawable.ic_setting_selected));
                mTxtSetting.setSelected(true);
                break;
            default:
                replaceFragment(new HomeFragment());
                mIVHome.setImageDrawable(ContextCompat.getDrawable(HomeActivity.this, R.drawable.ic_home_selected));
                mTxtHome.setSelected(true);
                break;
        }
    }
}