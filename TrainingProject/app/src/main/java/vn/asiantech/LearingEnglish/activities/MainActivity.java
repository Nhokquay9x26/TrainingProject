package vn.asiantech.LearingEnglish.activities;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import java.util.ArrayList;

import lombok.Getter;
import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.fragments.FavoriteFragment_;
import vn.asiantech.LearingEnglish.fragments.QuestionFragment_;
import vn.asiantech.LearingEnglish.fragments.SettingFragment_;
import vn.asiantech.LearingEnglish.fragments.TopFragment_;
import vn.asiantech.LearingEnglish.models.AlarmReceiver;
import vn.asiantech.LearingEnglish.models.ApplicationData;
import vn.asiantech.LearingEnglish.models.FunctionModel;
import vn.asiantech.LearingEnglish.models.Ranking;
import vn.asiantech.LearingEnglish.utils.TabBar;

/**
 * @author TienTun
 *         Created by tientun on 3/5/15.
 */
@SuppressWarnings("ALL")
@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity {
    @Getter
    private ArrayList<Uri> mListUriRingTones;
    @Getter
    private ArrayList<Ranking> mRankings;

    @ViewById(R.id.viewpagerMain)
    ViewPager mViewPagerMain;
    @ViewById(R.id.tabbarMain)
    TabBar mTabBarMain;
    @ViewById(R.id.tvHeader)
    TextView tvHeader;
    private ViewPagerAdapter mAdapter;
    private Boolean mIsExit = false;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @OptionsItem(android.R.id.home)
    protected void backAction() {
        finish();
    }

    @AfterViews
    void afterViews() {
        mListUriRingTones = new ArrayList<>();
        mListUriRingTones = FunctionModel.getRingtones(this);
        getRankUser();
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        ViewPagerAdapter mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerMain.setAdapter(mAdapter);
        mTabBarMain.clickTab(0);
        mTabBarMain.setOnTabBarListener(new TabBar.OnTabBarListener() {
            @Override
            public void onTabClick(int position) {
                mViewPagerMain.setCurrentItem(position);
            }
        });

        final Intent mIntent = new Intent(this, TestingActivity_.class);
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

    private void getRankUser() {
        mRankings = new ArrayList<Ranking>();
        mRankings = ApplicationData.getRankUser();
    }

    /**
     * class ViewPagerAdapter
     */
    public class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f;
            switch (position) {
                case 0:
                    f = new TopFragment_();
                    break;
                case 1:
                    f = new FavoriteFragment_();
                    break;
                case 2:
                    f = new QuestionFragment_();
                    break;
                case 3:
                    f = new SettingFragment_();
                    break;
                default:
                    f = new SettingFragment_();
                    break;
            }
            return f;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    @Override
    public void onBackPressed() {
        if (mIsExit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            mIsExit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mIsExit = false;
                }
            }, 3 * 1000);
        }
    }
}
