package vn.asiantech.LearingEnglish.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.*;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.activities.MainActivity;

/**
 * @Author XuanPhu
 * Created by xuanphu on 31/08/2015.
 */

public class TabBar extends RelativeLayout implements View.OnClickListener {
    public interface OnTabBarListener {
        void onTabClick(int position);
    }

    private static final int COLOR_TEXT_DEFAULT = 0xcc666666;
    private static final int COLOR_TEXT = 0xccffffff;
    private View mRootView;
    private View mViewTabHome;
    private View mViewTabFavorite;
    private View mViewTabQuestion;
    private View mViewTabSeting;
    private ImageView mImgHome;
    private TextView mTvHome;
    private ImageView mImgFavorite;
    private TextView mTvFavorite;
    private ImageView mImgQuestion;
    private TextView mTvQuestion;
    private ImageView mImgSetting;
    private TextView mTvSetting;
    private TextView mTvHeader;
    private OnTabBarListener mOnTabBarListener;

    public TabBar(Context context) {
        super(context);
        init(context);
    }

    public TabBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TabBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mRootView = LayoutInflater.from(context).inflate(R.layout.custom_tab_bar_main, this, false);
        addView(mRootView);

        mViewTabHome = mRootView.findViewById(R.id.llHome);
        mViewTabFavorite = mRootView.findViewById(R.id.llFavorite);
        mViewTabQuestion = mRootView.findViewById(R.id.llQuestion);
        mViewTabSeting = mRootView.findViewById(R.id.llSetting);
        mViewTabHome.setOnClickListener(this);
        mViewTabFavorite.setOnClickListener(this);
        mViewTabQuestion.setOnClickListener(this);
        mViewTabSeting.setOnClickListener(this);

        mImgHome = (ImageView) mRootView.findViewById(R.id.imgHome);
        mTvHome = (TextView) mRootView.findViewById(R.id.tvHome);
        mImgFavorite = (ImageView) mRootView.findViewById(R.id.imgFavorite);
        mTvFavorite = (TextView) mRootView.findViewById(R.id.tvFavorite);
        mImgQuestion = (ImageView) mRootView.findViewById(R.id.imgQuestion);
        mTvQuestion = (TextView) mRootView.findViewById(R.id.tvQuestion);
        mImgSetting = (ImageView) mRootView.findViewById(R.id.imgSetting);
        mTvSetting = (TextView) mRootView.findViewById(R.id.tvSetting);

        mTvHeader = (TextView) mRootView.findViewById(R.id.tvHeader);

        clickTab(0);
    }

    private void setColorDefault() {
        mImgHome.setImageResource(R.drawable.icon_top_b);
        mTvHome.setTextColor(COLOR_TEXT_DEFAULT);
        mImgFavorite.setImageResource(R.drawable.icon_favorite_b);
        mTvFavorite.setTextColor(COLOR_TEXT_DEFAULT);
        mImgQuestion.setImageResource(R.drawable.icon_question_b);
        mTvQuestion.setTextColor(COLOR_TEXT_DEFAULT);
        mImgSetting.setImageResource(R.drawable.icon_seting_b);
        mTvSetting.setTextColor(COLOR_TEXT_DEFAULT);

    }

    public void setOnTabBarListener(OnTabBarListener l) {
        mOnTabBarListener = l;
    }

    public void clickTab(int position) {
        setColorDefault();

        switch (position) {
            case 0:
                mImgHome.setImageResource(R.drawable.icon_top_w);
                mTvHome.setTextColor(COLOR_TEXT);
                mTvHeader.setText("Home");
                if (mOnTabBarListener != null) {
                    mOnTabBarListener.onTabClick(0);
                }
                break;

            case 1:
                mImgFavorite.setImageResource(R.drawable.icon_favorite_w);
                mTvFavorite.setTextColor(COLOR_TEXT);
                mTvHeader.setText("Favorite");
                if (mOnTabBarListener != null) {
                    mOnTabBarListener.onTabClick(1);
                }
                break;

            case 2:
                mImgQuestion.setImageResource(R.drawable.icon_question_w);
                mTvQuestion.setTextColor(COLOR_TEXT);
                mTvHeader.setText("Question");
                if (mOnTabBarListener != null) {
                    mOnTabBarListener.onTabClick(2);
                }
                break;

            case 3:
                mImgSetting.setImageResource(R.drawable.icon_seting_w);
                mTvSetting.setTextColor(COLOR_TEXT);
                mTvHeader.setText("Setting");
                if (mOnTabBarListener != null) {
                    mOnTabBarListener.onTabClick(3);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.llHome) {
            clickTab(0);
        } else if (id == R.id.llFavorite) {
            clickTab(1);
        } else if (id == R.id.llQuestion) {
            clickTab(2);
        } else if (id == R.id.llSetting) {
            clickTab(3);
        }
    }
}