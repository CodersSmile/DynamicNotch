package com.iosnotch.dynamic.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.iosnotch.dynamic.BuildConfig;
import com.iosnotch.dynamic.R;
import com.iosnotch.dynamic.dialogs.RateDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingFragment extends Fragment {

    public SettingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.mIVBack, R.id.mIvRate, R.id.mIvShare, R.id.mIvPrivacy, R.id.mIvInfo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mIVBack:
                break;
            case R.id.mIvRate:
                mRateApp();
                break;
            case R.id.mIvShare:
                mShareApp();
                break;
            case R.id.mIvPrivacy:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://clickmediainc.blogspot.com/2023/03/privacy-policy.html"));
                startActivity(browserIntent);
                break;
            case R.id.mIvInfo:
                break;
        }
    }

    private void mShareApp() {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
            String shareMessage = "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mRateApp() {
        new RateDialog(new RateDialog.OnClickListener() {

            @Override
            public void OnRate() {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append("market://details?id=");
                    sb.append(getActivity().getPackageName());
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(sb.toString())));
                } catch (ActivityNotFoundException e) {
                    Intent rateIntent = rateIntentForUrl("https://play.google.com/store/apps/details");
                    startActivity(rateIntent);
                }
            }

            @Override
            public void OnCancel() {

            }
        }).show(getChildFragmentManager(), "");

    }

    private Intent rateIntentForUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getActivity().getPackageName())));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21) {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        } else {
            //noinspection deprecation
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        intent.addFlags(flags);
        return intent;
    }
}