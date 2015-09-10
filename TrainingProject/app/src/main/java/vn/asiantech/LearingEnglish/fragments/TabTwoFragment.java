package vn.asiantech.LearingEnglish.fragments;

import android.support.v4.view.ViewPager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.TestingAdapter;
import vn.asiantech.LearingEnglish.views.HeaderBar;


@EFragment(R.layout.fragment_two)
public class TabTwoFragment extends BaseFragment implements TestingAdapter.OnChangePager {

    @ViewById(R.id.viewPagerTesting)
    ViewPager mViewPager;

    @AfterViews
    public void AfterViews() {
        FragmentPageTesting_ mFragmentPageTesting = new FragmentPageTesting_();
        TestingAdapter adapter = new TestingAdapter(getFragmentManager(),
                mFragmentPageTesting, this,getCountQuestion());
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mOnBaseFragmentListener != null) {
            mOnBaseFragmentListener.setHeaderTitle("");
            mOnBaseFragmentListener.setTypeHeaderBar(HeaderBar.HeaderBarType.TYPE_HOME);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onChange(int i) {
        if (i == 1) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
        }
        if (i == -1) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
        }
    }
    private int getCountQuestion(){
        String[] mQuestion = getResources().getStringArray(R.array.question_array);
        return mQuestion.length;
    }

}