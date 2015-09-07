package vn.asiantech.LearingEnglish.fragments;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


import org.androidannotations.annotations.EFragment;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.TestingAdapter;


@EFragment(R.layout.fragment_two)
public class TabTwoFragment extends BaseFragment implements TestingAdapter.OnChangePager{
    TestingAdapter mAdapter;
    ViewPager mPager;
    static final int ITEMS = 5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        FragmentPageTesting fragment=new FragmentPageTesting();
        mAdapter = new TestingAdapter(getActivity().getSupportFragmentManager(),fragment, this);
        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onChange(int i) {
        if (i==1){
            mPager.setCurrentItem(mPager.getCurrentItem()+1);
        }
        if (i==-1){
            mPager.setCurrentItem(mPager.getCurrentItem()-1);
        }
    }

}