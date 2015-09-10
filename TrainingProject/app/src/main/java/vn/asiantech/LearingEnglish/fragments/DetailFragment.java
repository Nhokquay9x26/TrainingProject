package vn.asiantech.LearingEnglish.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.ViewpagerDetailAdapter;
import vn.asiantech.LearingEnglish.models.AnimalCategory;

/**
 * @
 * Created by xuanphu on 04/09/2015.
 */
public class DetailFragment extends BaseFragment implements ViewpagerDetailAdapter.CallBackNext {
    private ArrayList<AnimalCategory> mArraylist = new ArrayList<>();
    private int mPosition;
    private ViewPager mViewpager;
    private ViewpagerDetailAdapter mAdapter;
    private ImageView mImgBackHeaderDetail;

    public DetailFragment(ArrayList<AnimalCategory> mArraylist, int mPosition) {
        this.mArraylist = mArraylist;
        this.mPosition = mPosition;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_detail,container, false);
        mViewpager = (ViewPager)mView.findViewById(R.id.viewpagerDetail);
        mAdapter = new ViewpagerDetailAdapter(getActivity(),mArraylist, this);
        mViewpager.setAdapter(mAdapter);
        mViewpager.setCurrentItem(mPosition);
        mImgBackHeaderDetail = (ImageView)mView.findViewById(R.id.imgBackHeaderDetail);
        mImgBackHeaderDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return mView;
    }

    @Override
    public void OnClickNext() {
        if (mViewpager.getCurrentItem() != mArraylist.size()-1){
            mViewpager.setCurrentItem(mViewpager.getCurrentItem()+1);
        }
    }

    @Override
    public void OnClickBack() {
        if (mViewpager.getCurrentItem() != 0){
            mViewpager.setCurrentItem(mViewpager.getCurrentItem()-1);
        }
    }
}
