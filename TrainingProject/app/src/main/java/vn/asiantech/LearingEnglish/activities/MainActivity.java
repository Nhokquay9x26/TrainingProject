package vn.asiantech.LearingEnglish.activities;

import android.content.Intent;
import android.os.Handler;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import lombok.Getter;
import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.fragments.FavoriteFragment_;
import vn.asiantech.LearingEnglish.fragments.QuestionFragment;
import vn.asiantech.LearingEnglish.fragments.QuestionFragment_;
import vn.asiantech.LearingEnglish.fragments.RatingsFragment;
import vn.asiantech.LearingEnglish.fragments.RatingsFragment_;
import vn.asiantech.LearingEnglish.fragments.TopFragment_;
import vn.asiantech.LearingEnglish.models.ApplicationData;
import vn.asiantech.LearingEnglish.models.Ranking;
import vn.asiantech.LearingEnglish.utils.TabBar;

/**
 * @Author TienTun
 * Created by tientun on 3/5/15.
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity {

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

    @OptionsItem(android.R.id.home)
    protected void backAction() {
        finish();
    }

    @AfterViews
    void afterViews() {
        getRankUser();
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
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

                if (position == 2) {
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            startActivity(mIntent);
                        }
                    }, 1000);
                }
                if (position==3){
                   /* for (int i=0;i<5;i++){

                        Log.d("",mRankings.get(i).getMUserName());
                        Log.d("",mRankings.get(i).getMPoint()+"");

                    }*/
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    private void getRankUser(){
        mRankings = new ArrayList<Ranking>();
        mRankings = ApplicationData.getRankUser();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mViewPagerMain.setCurrentItem(0, false);
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
            Fragment f = null;
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
                    f = new RatingsFragment_();
                    break;
                default:
                    f = new RatingsFragment_();
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
