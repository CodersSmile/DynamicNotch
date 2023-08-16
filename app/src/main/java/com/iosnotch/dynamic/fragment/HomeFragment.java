package com.iosnotch.dynamic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.fragment.app.Fragment;

import com.iosnotch.dynamic.R;
import com.iosnotch.dynamic.notchmodule.global.Utils;
import com.iosnotch.dynamic.utils.Constants;
import com.iosnotch.dynamic.utils.PrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    @BindView(R.id.mTxtStatusBar)
    TextView mTxtStatusBar;
    @BindView(R.id.mToggleEnableIsland)
    ToggleButton mToggleEnableIsland;
    @BindView(R.id.mSBMargin)
    SeekBar mSBMargin;
    @BindView(R.id.mSBHeight)
    AppCompatSeekBar mSBHeight;
    Context context;
    PrefManager prefManager;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        context = getContext();
        this.prefManager = new PrefManager(this.context);
        mTxtStatusBar.getPaint().setShader(Utils.getShader(mTxtStatusBar));
        mSetAllOptions(view);
        return view;
    }

    private void mSetAllOptions(View view) {
        mToggleEnableIsland.setChecked(Constants.getControlEnabled(context));
        this.mToggleEnableIsland.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Constants.setControlEnabled(context, isChecked);
            }
        });
        int cameraCount = this.prefManager.getCameraCount();
        if (cameraCount == 1) {
            ((RadioButton) view.findViewById(R.id.one_camera)).setChecked(true);
        }
        if (cameraCount == 2) {
            ((RadioButton) view.findViewById(R.id.two_camera)).setChecked(true);
        }
        if (cameraCount == 3) {
            ((RadioButton) view.findViewById(R.id.three_camera)).setChecked(true);
        }
        ((RadioGroup) view.findViewById(R.id.cam_count_group)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if (i == R.id.one_camera) {
                    prefManager.setCameraCount(1);
                } else if (i == R.id.two_camera) {
                    prefManager.setCameraCount(2);
                } else if (i == R.id.three_camera) {
                    prefManager.setCameraCount(3);
                }
            }
        });
        int cameraPos = this.prefManager.getCameraPos();
        if (cameraPos == 1) {
            ((RadioButton) view.findViewById(R.id.left_camera)).setChecked(true);
        }
        if (cameraPos == 2) {
            ((RadioButton) view.findViewById(R.id.center_camera)).setChecked(true);
        }
        if (cameraPos == 3) {
            ((RadioButton) view.findViewById(R.id.right_camera)).setChecked(true);
        }
        ((RadioGroup) view.findViewById(R.id.cam_pos_group)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if (i == R.id.left_camera) {
                    prefManager.setCameraPos(1);
                } else if (i == R.id.center_camera) {
                    prefManager.setCameraPos(2);
                } else if (i == R.id.right_camera) {
                    prefManager.setCameraPos(3);
                }
            }
        });
        mSBMargin.setProgress(prefManager.getYPosOfIsland());
        mSBMargin.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress >= 1 && progress < 20) {
                    prefManager.setYPosOfIsland(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSBHeight.setProgress(prefManager.getHeightOfIsland());
        mSBHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress >= 25 && progress < 40) {
                    prefManager.setHeightOfIsland(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}