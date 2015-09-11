package vn.asiantech.LearingEnglish.fragments;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentByTag;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 9/4/15.
 */
@EFragment(R.layout.fragment_tutorial)
public class TutorialFragment extends BaseFragment {

    @ViewById(R.id.btnStartActivity)
    Button mBtnMainActivity;
    @ViewById(R.id.viewPager)
    ViewPager mViewPager;
    @ViewById(R.id.circle)
    CirclePageIndicator mIndicator;
    private MyInfinitePagerAdapter mAdapter;

    @FragmentByTag("LoginFragment")
    protected LoginFragment mLoginFragment;

    @AfterViews
    public void afterViews() {
        mAdapter = new MyInfinitePagerAdapter(getActivity());
        mViewPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mViewPager);
        mIndicator.setOnPageChangeListener(new PageIndicator() {
            @Override
            public void setViewPager(ViewPager view) {

            }

            @Override
            public void setViewPager(ViewPager view, int initialPosition) {

            }

            @Override
            public void setCurrentItem(int item) {

            }

            @Override
            public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {

            }

            @Override
            public void notifyDataSetChanged() {

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    mBtnMainActivity.setVisibility(View.VISIBLE);
                } else {
                    mBtnMainActivity.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Click(R.id.btnStartActivity)
    protected void onClick() {
        if (mLoginFragment == null) {
            mLoginFragment = LoginFragment_.builder().build();
        }
        addChildFragment(mLoginFragment);
    }

    /**
     * Build Adapter to custom viewpager
     */
    class MyInfinitePagerAdapter extends PagerAdapter {
        private int[] mImages = new int[]{
                R.drawable.bg_login_one,
                R.drawable.bg_login_two,
                R.drawable.bg_login_three,
        };
        private LayoutInflater mLayoutInflater;

        public MyInfinitePagerAdapter(Context context) {
            mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mImages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View itemView = mLayoutInflater.inflate(R.layout.complex_page_layout, container, false);
            final ImageView imageView = (ImageView) itemView.findViewById(R.id.imgBanner);
            imageView.setImageResource(mImages[position]);
            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((RelativeLayout) object);
        }
    }

    public boolean sendBackPressed() {
        if (getVisibleChildFragment() instanceof BaseFragment) {
            BaseFragment currentFragment = (BaseFragment) getVisibleChildFragment();
            return currentFragment.popChildFragment();
        }
        return false;
    }
}
