package com.iosnotch.dynamic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.iosnotch.dynamic.R;
import com.iosnotch.dynamic.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.iosnotch.dynamic.notchmodule.global.Utils.getShader;

public class PrivacyActivity extends BaseActivity {

    @BindView(R.id.mTxtTerms)
    TextView mTxtTerms;
    @BindView(R.id.mTxtPrivacy2)
    TextView mTxtPrivacy2;
    @BindView(R.id.mTxtTermsCondition)
    TextView mTxtTermsCondition;
    @BindView(R.id.mMCBPrivacy)
    MaterialCheckBox mMCBPrivacy;
    boolean mChecked = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_privacy;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mTxtTerms.getPaint().setShader(getShader(mTxtTerms));
        mTxtPrivacy2.getPaint().setShader(getShader(mTxtTerms));
        mTxtTermsCondition.getPaint().setShader(getShader(mTxtTerms));
        mMCBPrivacy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mChecked = isChecked;
            }
        });
    }

    private void mCallNextActivity() {
        if (mChecked) {
            prefManager.setFirstTimeDetail(false);
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Plz Check Privacy policy & Terms condition.", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.mLLCheck, R.id.mTxtNext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mLLCheck:
                if (mMCBPrivacy.isChecked()) {
                    mMCBPrivacy.setChecked(false);
                    mChecked = false;
                } else {
                    mMCBPrivacy.setChecked(true);
                    mChecked = true;
                }
                break;
            case R.id.mTxtNext:
                mCallNextActivity();
                break;
        }
    }
}