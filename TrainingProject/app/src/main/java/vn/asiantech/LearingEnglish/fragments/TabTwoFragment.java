package vn.asiantech.LearingEnglish.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.androidannotations.annotations.EFragment;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.TestingAdapter;


@EFragment(R.layout.fragment_two)
public class TabTwoFragment extends BaseFragment {
    TestingAdapter mAdapter;
    ViewPager mPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_two, container, false);
        mAdapter = new TestingAdapter(getActivity().getSupportFragmentManager());
        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}