package vn.asiantech.LearingEnglish.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.fragments.FavoriteFragment;
import vn.asiantech.LearingEnglish.fragments.QuestionFragment;
import vn.asiantech.LearingEnglish.fragments.RatingsFragment;
import vn.asiantech.LearingEnglish.fragments.TopFragment;
import vn.asiantech.LearingEnglish.utils.TabBar;

/**
 * @Author TienTun
 * Created by tientun on 3/5/15.
 */

@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity {
    @ViewById(R.id.viewpagerMain)
    ViewPager mViewPagerMain;
    @ViewById(R.id.tabbarMain)
    TabBar mTabBarMain;
    private ViewPagerAdapter mAdapter;

    @OptionsItem(android.R.id.home)
    protected void backAction() {
        finish();
    }
    @AfterViews
    void afterViews(){
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerMain.setAdapter(mAdapter);
        mTabBarMain.clickTab(0);
        mTabBarMain.setOnTabBarListener(new TabBar.OnTabBarListener() {
            @Override
            public void onTabClick(int position) {
                mViewPagerMain.setCurrentItem(position);
            }
        });
        mViewPagerMain.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                mTabBarMain.clickTab(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    /**
     *
     */
    public class ViewPagerAdapter extends FragmentStatePagerAdapter{

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = null;
            switch (position) {
                case 0:
                    f = new TopFragment();
                    break;
                case 1:
                    f = new FavoriteFragment();
                    break;
                case 2:
                    f = new QuestionFragment();
                    break;
                case 3:
                    f = new RatingsFragment();
                    break;
                default:
                    f = new RatingsFragment();
                    break;
            }
            return f;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
