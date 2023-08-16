package com.iosnotch.dynamic.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.iosnotch.dynamic.AdsUtils.FirebaseADHandlers.AdUtils;
import com.iosnotch.dynamic.AdsUtils.Interfaces.AppInterfaces;
import com.iosnotch.dynamic.R;
import com.iosnotch.dynamic.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class IntroActivity extends BaseActivity {

    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    @BindView(R.id.mLLTop)
    LinearLayout mLLTop;
    //    @BindView(R.id.mTxtIntro)
//    TextView mTxtIntro;
    @BindView(R.id.mLayoutDots)
    LinearLayout mLayoutDots;
    private TextView[] dots;
    private int[] layouts;
    private MyViewPagerAdapter myViewPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_intro;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        layouts = new int[]{
                R.layout.layout_into1,
                R.layout.layout_into2,
                R.layout.layout_into3};

        addBottomDots(0);

        myViewPagerAdapter = new MyViewPagerAdapter();
        mViewPager.setAdapter(myViewPagerAdapter);
        mViewPager.addOnPageChangeListener(viewPagerPageChangeListener);
    }


    @OnClick({R.id.mTxtNext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mTxtNext:
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    mViewPager.setCurrentItem(current);
                } else {
                    AdUtils.showInterstitialAd(IntroActivity.this, new AppInterfaces.InterStitialADInterface() {
                        @Override
                        public void adLoadState(boolean isLoaded) {
                            launchHomeScreen();
                        }
                    });
                }
                break;
        }
    }

    private void addBottomDots(int currentPage) {
        if (currentPage == 0) {
//            mLLTop.setBackground(ContextCompat.getDrawable(IntroActivity.this,R.drawable.intro_bg1));
//            mTxtIntro.setText(getResources().getString(R.string.intro1));
        } else if (currentPage == 1) {
//            mLLTop.setBackground(ContextCompat.getDrawable(IntroActivity.this,R.drawable.intro_bg2));
//            mTxtIntro.setText(getResources().getString(R.string.intro2));
        } else {
//            mLLTop.setBackground(ContextCompat.getDrawable(IntroActivity.this,R.drawable.intro_bg3));
//            mTxtIntro.setText(getResources().getString(R.string.intro3));
        }
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        mLayoutDots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(28);
            dots[i].setTextColor(colorsInactive[currentPage]);
            mLayoutDots.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return mViewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(IntroActivity.this, PermissionsActivity.class));
        finish();
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}