package vn.asiantech.LearingEnglish.activities;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import lombok.Getter;
import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.TestingAdapter;
import vn.asiantech.LearingEnglish.fragments.TestResultingFragment_;
import vn.asiantech.LearingEnglish.fragments.TestingFragment_;
import vn.asiantech.LearingEnglish.models.DataQuestion;
import vn.asiantech.LearingEnglish.models.Question;

/**
 * Created by tantv on 28/08/2015.
 */

@EActivity(R.layout.activity_testing)
public class TestingActivity extends FragmentActivity {
    private ArrayList<Fragment> mArrFragmentTestings;

    @Getter
    private int mFragmentCurrentDisplay = 0;

    @Getter
    private ArrayList<Question> mArrQuestionDatas;

    @ViewById(R.id.viewpager)
    ViewPager mViewPager;

    @ViewById(R.id.tvTimeLeft)
    TextView mTvTimeLeft;

    @Getter
    @ViewById(R.id.tvLeftQuestion)
    TextView mTvLeftQuestion;

    @ViewById(R.id.imgPrevious)
    ImageView mImgPrevious;

    @ViewById(R.id.imgNext)
    ImageView mImgNext;

    @ViewById(R.id.tvNumberOfQuestion)
    TextView mNumberOfQuestion;

    @ViewById(R.id.tvTotalQuestion)
    TextView mTvTotalQuestion;

    @ViewById(R.id.btnSubmit)
    Button mBtnSubmit;

    @ViewById(R.id.frViewpager)
    FrameLayout mFrViewpager;

    @ViewById(R.id.rlButtonBottom)
    RelativeLayout mRlButtonBottom;



    @AfterViews
    void afterView() {
        getQuestion();
        mArrFragmentTestings = new ArrayList<Fragment>();
        newFragmentQuestion();
        TestingAdapter adapterTesting = new TestingAdapter(getSupportFragmentManager(), mArrFragmentTestings);
        mViewPager.setAdapter(adapterTesting);
        creatCountDown();
        setOnClickViewPager();
        mNumberOfQuestion.setText("1 - " + mArrQuestionDatas.size());
        mTvTotalQuestion.setText("1/" + mArrQuestionDatas.size());

    }

    @Click(R.id.btnSubmit)
    void mBtnSubmitClicked(){
        mRlButtonBottom.setVisibility(View.INVISIBLE);
        mTvTimeLeft.setVisibility(View.INVISIBLE);
        mTvTotalQuestion.setVisibility(View.INVISIBLE);
        mTvLeftQuestion.setText("Total Time: 06:59");
        TestResultingFragment_ fr = new TestResultingFragment_();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frViewpager,fr,"");
        fragmentTransaction.commit();
    }

    /**
     * Event When Click Button Previous
     */
    @Click(R.id.imgPrevious)
    void mImgPreviousClicked() {
        if (mFragmentCurrentDisplay != 0) {
            mViewPager.setCurrentItem(mFragmentCurrentDisplay - 1);
        }
    }

    /**
     * Event When Click Button Next
     */
    @Click(R.id.imgNext)
    void mImgNextClicked() {
        if (mFragmentCurrentDisplay != mArrQuestionDatas.size()-1) {
            mViewPager.setCurrentItem(mFragmentCurrentDisplay + 1);
        }
    }

    /**
     * Event when slide or click on viewpager
     */
    private void setOnClickViewPager() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 0) {
                    Log.d("Mcurrent:onPageScroll:", mViewPager.getCurrentItem() + "");
                    mFragmentCurrentDisplay = mViewPager.getCurrentItem();
                    mNumberOfQuestion.setText((mFragmentCurrentDisplay+1) + " - " + mArrQuestionDatas.size());
                    mTvTotalQuestion.setText((mFragmentCurrentDisplay+1) + "/" + mArrQuestionDatas.size());
                }
            }
        });
    }

    /**
     * New Fragmnet from Number of Question
     */
    private void newFragmentQuestion() {
        TestingFragment_ objectTestingFragment_ = new TestingFragment_();

        for (int i = 0; i < mArrQuestionDatas.size(); i++) {
            String question = mArrQuestionDatas.get(i).getQuestion();
            String selectionA = mArrQuestionDatas.get(i).getSelectionA();
            String selectionB = mArrQuestionDatas.get(i).getSelectionB();
            String selectionC = mArrQuestionDatas.get(i).getSelectionC();
            String selectionD = mArrQuestionDatas.get(i).getSelectionD();
            mArrFragmentTestings.add(objectTestingFragment_.newInstance(i+1,question, selectionA, selectionB, selectionC, selectionD));

        }
    }

    /**
     * get Question From Data
     */
    private void getQuestion() {
        mArrQuestionDatas = new ArrayList<Question>();
        DataQuestion.getDataQuestion(mArrQuestionDatas);

    }

    /**
     * CountDown Timer
     */
    private void creatCountDown() {
        //10min = 600 sec = 600000ms
        new CountDownTimer(600000, 1000) {
            public void onTick(long millisUntilFinished) {
                long min = (millisUntilFinished / 1000) / 60;
                long sec = (millisUntilFinished / 1000) % 60;
                if (min < 10 && (sec < 10)) {
                    mTvTimeLeft.setText("0" + min + ":0" + sec);
                } else if (min < 10 && (sec >= 10)) {
                    mTvTimeLeft.setText("0" + min + ":" + sec);
                } else if (min >= 10 && (sec < 10)) {
                    mTvTimeLeft.setText(min + ":0" + sec);
                } else {
                    mTvTimeLeft.setText(min + ":" + sec);
                }
            }
            public void onFinish() {
                mTvTimeLeft.setText("00:00");
            }
        }.start();
    }
}