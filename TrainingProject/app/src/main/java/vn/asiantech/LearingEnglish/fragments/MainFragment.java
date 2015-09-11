package vn.asiantech.LearingEnglish.fragments;

import android.content.res.ColorStateList;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.PagerAdapter;
import vn.asiantech.LearingEnglish.models.HomePageItem;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 8/27/15.
 */
@EFragment(R.layout.fragment_main)
public class MainFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    @ViewById(R.id.viewPager)
    protected ViewPager mViewPager;
    @ViewById(R.id.tabstrip)
    protected PagerSlidingTabStrip mTabSlidingAdapter;
    private PagerAdapter mPagerAdapter;

    @AfterViews
    public void afterViews() {
        initializeAdapterView();
    }

    private void initializeAdapterView() {
        ArrayList<HomePageItem> tabItems = new ArrayList<>();
        tabItems.add(new HomePageItem(TopFragment_.builder().build(), "TOP", R.mipmap.ic_top));
        tabItems.add(new HomePageItem(FavoriteFragment_.builder().build(), "NOTE", R.mipmap.ic_note));
        tabItems.add(new HomePageItem(TestFragment_.builder().build(), "TEST", R.mipmap.ic_test));
        tabItems.add(new HomePageItem(SettingFragment_.builder().build(), "SETTING", R.mipmap.ic_setting ));

        mPagerAdapter = new PagerAdapter(this, tabItems);
        mViewPager.setVerticalScrollBarEnabled(false);
        mViewPager.setAdapter(mPagerAdapter);
        mPagerAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(0, false);
        mViewPager.setOffscreenPageLimit(3);
        mTabSlidingAdapter.setViewPager(mViewPager);
        mTabSlidingAdapter.setDividerColor(0x30FFFFFF);
        mTabSlidingAdapter.setIndicatorColor(0x0033b5e5);
        mTabSlidingAdapter.setOnPageChangeListener(this);
        ColorStateList colors = getResources().getColorStateList(R.drawable.bg_tab_title);
//        mTabSlidingAdapter.setTextColorResource(colors);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        BaseFragment fragment = (BaseFragment) getVisibleFragment();
        FragmentManager manager = fragment.getChildFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            // refresh visible front fragment
            BaseFragment target = (BaseFragment) manager.getFragments().get(manager.getBackStackEntryCount() - 1);
            target.refresh();
        } else {
            fragment.refresh();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private Fragment getVisibleFragment() {
        return mPagerAdapter.getItem(mViewPager.getCurrentItem());
    }
}
