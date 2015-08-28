package vn.asiantech.LearingEnglish.activities;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.FragmentTransaction;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.ViewPagerAdapter;
import vn.asiantech.LearingEnglish.fragments.FavoriteFragment_;
import vn.asiantech.LearingEnglish.fragments.SettingFragment_;
import vn.asiantech.LearingEnglish.fragments.TestFragment_;
import vn.asiantech.LearingEnglish.fragments.TopFragment_;

/**
 * Created by tientun on 3/5/15.
 */

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActionBarActivity {
    @ViewById(R.id.frameLayoutMain)
    FrameLayout mFrameLayout;
    @ViewById(R.id.includeFooterBar)
    View mIncludeFooterBar;
    //tabTop
    @ViewById(R.id.imgTop)
    ImageView mImgTop;
    @ViewById(R.id.tvTop)
    TextView mTvTop;
    //TabFavorite
    @ViewById(R.id.imgFavorite)
    ImageView mImgFavorite;
    @ViewById(R.id.tvFavorite)
    TextView mTvFavorite;
    //tabTest
    @ViewById(R.id.imgTest)
    ImageView mImgTest;
    @ViewById(R.id.tvTest)
    TextView mTvTest;
    //TabSetting
    @ViewById(R.id.imgSetting)
    ImageView mImgSetting;
    @ViewById(R.id.tvSetting)
    TextView mTvSetting;

    @ViewById(R.id.viewPagerMain)
    ViewPager mViewPagerMain;

    private ViewPagerAdapter mViewPagerAdapter;
    @ViewById(R.id.llTabTop)
    LinearLayout mLLTabTop;
    @Click
    void llTabTopClicked (){
        setChangeFooterBar(0);
    }
    @ViewById(R.id.llTabFavorite)
    LinearLayout mllTabFavorite;
    @Click
    void llTabFavoriteClicked(){
        setContainer(FavoriteFragment_.builder().build(),false);
        setChangeFooterBar(1);

    }
    @ViewById(R.id.llTabTest)
    LinearLayout mllTabTest;
    @Click
    void llTabTestClicked(){
        setContainer(TestFragment_.builder().build(), false);
        setChangeFooterBar(2);


    }
    @ViewById(R.id.llTabSetting)
    LinearLayout mllTabSetting;
    @Click
    void llTabSettingClicked(){
        setContainer(SettingFragment_.builder().build(), false);
        setChangeFooterBar(3);

    }
    @OptionsItem(android.R.id.home)
    protected void backAction() {
        finish();
    }

    @Override
    void afterView() {
//        Fragment a = new TopFragment();
//        android.support.v4.app.FragmentManager fragmentManager= getSupportFragmentManager();
//         fragmentManager.beginTransaction().replace(R.id.frameLayoutMain, a).commit();
        setChangeFooterBar(1);
        setContainer(TopFragment_.builder().build(), false);
        mViewPagerMain.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        mViewPagerMain.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("Activity","viewpager dc chon ;a =" + position);
                setChangeFooterBar(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public void setContainer(Fragment fragment, boolean isAddToBackStack) {
       FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutMain, fragment);
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        // Commit the transaction
        fragmentTransaction.commit();
    }
public void setChangeFooterBar(int n){

    if (n == 0){
        mTvTop.setTextColor(getResources().getColor(R.color.footer_bar_text_white));
        mTvFavorite.setTextColor(getResources().getColor(R.color.footer_bar_text_black));
        mTvSetting.setTextColor(getResources().getColor(R.color.footer_bar_text_black));
        mTvTest.setTextColor(getResources().getColor(R.color.footer_bar_text_black));

        mImgTop.setImageResource(R.drawable.ico_coupons_white_xhd);
        mImgFavorite.setImageResource(R.drawable.ico_stores_dark_xhd);
        mImgTest.setImageResource(R.drawable.ico_price_comparison_dark_xhd);
        mImgSetting.setImageResource(R.drawable.ico_more_dark_xhd);
    }
    if (n == 1){
        mTvTop.setTextColor(getResources().getColor(R.color.footer_bar_text_black));
        mTvFavorite.setTextColor(getResources().getColor(R.color.footer_bar_text_white));
        mTvSetting.setTextColor(getResources().getColor(R.color.footer_bar_text_black));
        mTvTest.setTextColor(getResources().getColor(R.color.footer_bar_text_black));

        mImgTop.setImageResource(R.drawable.ico_coupons_dark_xhd);
        mImgFavorite.setImageResource(R.drawable.ico_stores_white_xhd);
        mImgTest.setImageResource(R.drawable.ico_price_comparison_dark_xhd);
        mImgSetting.setImageResource(R.drawable.ico_more_dark_xhd);

    }
    if (n == 2){
        mTvTop.setTextColor(getResources().getColor(R.color.footer_bar_text_black));
        mTvFavorite.setTextColor(getResources().getColor(R.color.footer_bar_text_black));
        mTvSetting.setTextColor(getResources().getColor(R.color.footer_bar_text_black));
        mTvTest.setTextColor(getResources().getColor(R.color.footer_bar_text_white));

        mImgTop.setImageResource(R.drawable.ico_coupons_dark_xhd);
        mImgFavorite.setImageResource(R.drawable.ico_stores_dark_xhd);
        mImgTest.setImageResource(R.drawable.ico_price_comparison_white_xhd);
        mImgSetting.setImageResource(R.drawable.ico_more_dark_xhd);

    }
    if (n == 3){
        mTvTop.setTextColor(getResources().getColor(R.color.footer_bar_text_black));
        mTvFavorite.setTextColor(getResources().getColor(R.color.footer_bar_text_black));
        mTvSetting.setTextColor(getResources().getColor(R.color.footer_bar_text_white));
        mTvTest.setTextColor(getResources().getColor(R.color.footer_bar_text_black));

        mImgFavorite.setImageResource(R.drawable.ico_stores_dark_xhd);
        mImgTop.setImageResource(R.drawable.ico_coupons_dark_xhd);
        mImgTest.setImageResource(R.drawable.ico_price_comparison_dark_xhd);
        mImgSetting.setImageResource(R.drawable.ico_more_white_xhd);

    }
}

}
