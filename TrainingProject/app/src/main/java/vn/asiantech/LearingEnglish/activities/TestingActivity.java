package vn.asiantech.LearingEnglish.activities;

import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.w3c.dom.Text;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.fragments.TestingFragment;

/**
 * Created by tantv on 28/08/2015.
 */

@EActivity(R.layout.activity_testing)
public class TestingActivity extends FragmentActivity {
    private ArrayList<Fragment> mFragmentTesting;

    @ViewById(R.id.viewpager)
    ViewPager mViewPager;

    @ViewById(R.id.tvTimeLeft)
    TextView mTvTimeLeft;

    @ViewById(R.id.tvTotalQuestion)
    TextView mTvTotalQuestion;

    @ViewById(R.id.tvLeftQuestion)
    TextView mTvLeftQuestion;

    @AfterViews
    void afterView() {
        mFragmentTesting = new ArrayList<Fragment>();
        mFragmentTesting.add(new TestingFragment());
        mFragmentTesting.add(new TestingFragment());
        TestingAdapter adapterTesting = new TestingAdapter(getSupportFragmentManager(),mFragmentTesting);
        mViewPager.setAdapter(adapterTesting);

        creatCountDownt();


    }

    private void creatCountDownt(){
        //10min = 600 sec = 600000ms
        new CountDownTimer(600000, 1000) {

            public void onTick(long millisUntilFinished) {
                long min = (millisUntilFinished/1000) / 60;
                long sec = (millisUntilFinished/1000) % 60;
                mTvTimeLeft.setText("0"+min+":" + sec);

            }

            public void onFinish() {
                mTvTimeLeft.setText("00:00");
            }
        }.start();

    }
}
