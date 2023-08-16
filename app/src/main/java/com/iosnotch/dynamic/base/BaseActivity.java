package com.iosnotch.dynamic.base;

import android.os.Bundle;

import butterknife.ButterKnife;

public abstract class BaseActivity extends PermissionHelperActivity {
    public PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        bindViews();
        prefManager = new PrefManager(this);
        initView(savedInstanceState);
    }

    private void bindViews() {
        try {
            ButterKnife.bind(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initView(Bundle savedInstanceState);
}
