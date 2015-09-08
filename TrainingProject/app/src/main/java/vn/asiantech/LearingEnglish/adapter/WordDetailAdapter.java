package vn.asiantech.LearingEnglish.adapter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.fragments.WordDetailFragment_;
import vn.asiantech.LearingEnglish.models.WordDetail;

public class WordDetailAdapter extends FragmentPagerAdapter {
    private ArrayList<WordDetail> mWordDetails;

    public WordDetailAdapter(FragmentManager fm, ArrayList<WordDetail> mFragments) {
        super(fm);
        this.mWordDetails = mFragments;
    }

    @Override
    public WordDetailFragment_ getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("wordDetail", mWordDetails.get(position));
        WordDetailFragment_ fragment = new WordDetailFragment_();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return mWordDetails.size();
    }
}
