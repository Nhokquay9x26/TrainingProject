package vn.asiantech.LearingEnglish.fragments;

import android.support.v4.view.ViewPager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.DetailAdapter;
import vn.asiantech.LearingEnglish.adapter.TestAdapter;

/**
 * @author DaoQuocViet
 * Created by nhokquay9x26 on 10/09/2015.
 */
@EFragment(R.layout.fragment_detail)
public class DetailFragment extends BaseFragment implements DetailAdapter.OnChangePager{
    public static final String NEXT = "Next";
    public static final String BACK = "Back";
    static final int ITEMS = 10;

    @ViewById(R.id.viewPagerDetail)
    ViewPager mViewPager;
    @AfterViews
    public void init() {
        DetailPagerFragment_ fragment = new DetailPagerFragment_();
        DetailAdapter adapter = new DetailAdapter(getFragmentManager(), fragment, this);
        mViewPager.setAdapter(adapter);
    }

    
    @Override
    public void onChange(String direction) {
        if (direction.equals(NEXT)) {
            if (mViewPager.getCurrentItem() != ITEMS - 1) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
            } else {
                mViewPager.setCurrentItem(0);
            }
        }
        if (direction.equals(BACK)) {
            if (mViewPager.getCurrentItem() != 0) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);

            } else {
                mViewPager.setCurrentItem(ITEMS - 1);
            }
        }
    }
}
