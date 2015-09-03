package vn.asiantech.LearingEnglish.fragments;

/**
 * Created by xuanphu on 31/08/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.*;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.HomeAdapter;
import vn.asiantech.LearingEnglish.models.HomeCategory;

@EFragment(R.layout.fragment_top)
public class TopFragment extends BaseFragment {
    @ViewById(R.id.recyclerHome)
    RecyclerView mRecyclerHome;
    HomeAdapter mHomeAdapter;
    ArrayList<HomeCategory> mArraylist = new ArrayList<>();

    @AfterViews
    void afterViews() {
        fakedata();
        mHomeAdapter = new HomeAdapter(getActivity(), mArraylist);
        mRecyclerHome.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        mRecyclerHome.setAdapter(mHomeAdapter);
    }

    /**
     * fakedata
     */
    private void fakedata() {
        HomeCategory homeCategory = new HomeCategory();
        homeCategory.setMAvataHome(R.drawable.img_animal);
        homeCategory.setMNameHome("Animal");
        mArraylist.add(homeCategory);

        HomeCategory homeCategory1 = new HomeCategory();
        homeCategory1.setMAvataHome(R.drawable.img_vegetables);
        homeCategory1.setMNameHome("Vegetables");
        mArraylist.add(homeCategory1);

        HomeCategory homeCategory2 = new HomeCategory();
        homeCategory2.setMAvataHome(R.drawable.img_transport);
        homeCategory2.setMNameHome("Transport");
        mArraylist.add(homeCategory2);

        HomeCategory homeCategory3 = new HomeCategory();
        homeCategory3.setMAvataHome(R.drawable.img_vegetables);
        homeCategory3.setMNameHome("Vegetables");
        mArraylist.add(homeCategory3);
    }
}
