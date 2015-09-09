package vn.asiantech.LearingEnglish.fragments;

import android.support.v4.view.ViewPager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.WordDetailAdapter;
import vn.asiantech.LearingEnglish.models.WordDetail;

@EFragment(R.layout.fragment_word_detail_container)
public class WordDetailContainerFragment extends BaseFragment
        implements WordDetailAdapter.OnWordDetailListener {

    @ViewById(R.id.viewPager)
    ViewPager mViewPager;
    private ArrayList<WordDetail> mWordDetails = new ArrayList<>();

    @AfterViews
    void afterView() {
        getData();
        WordDetailAdapter adapter = new WordDetailAdapter(getActivity(),
                mWordDetails, this);
        mViewPager.setAdapter(adapter);
    }

    private void getData() {
        for (int i = 0; i < 10; i++) {
            WordDetail wordDetail = new WordDetail(R.drawable.cloud, "Cloud " + (i + 1),
                    "Cloud /klaud/: Mây, đám mây...", "- the sun had disappeared behind a cloud");
            mWordDetails.add(wordDetail);
        }
    }

    @Override
    public void onClickNext() {
        if (mViewPager.getCurrentItem() != mWordDetails.size() - 1) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
        }
    }

    @Override
    public void onClickBack() {
        if (mViewPager.getCurrentItem() != 0) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
        }
    }
}
