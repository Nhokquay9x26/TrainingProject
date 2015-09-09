package vn.asiantech.LearingEnglish.fragments;

import android.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.HomeAdapter;
import vn.asiantech.LearingEnglish.models.HomeCategory;
import vn.asiantech.LearingEnglish.utils.IsCallTop;
import vn.asiantech.LearingEnglish.utils.TabBar;

/**
 * @Author xuanphu
 * Created by xuanphu on 04/09/2015.
 */
@EFragment(R.layout.fragment_home)
public class HomeFragment extends BaseFragment implements IsCallTop, TabBar.OnTabBarListener {

    @ViewById(R.id.recyclerHome)
    RecyclerView mRecyclerHome;
    HomeAdapter mHomeAdapter;
    ArrayList<HomeCategory> mArraylists = new ArrayList<>();

    public HomeFragment() {
        fakedata();
    }

    @AfterViews
    void afterViews() {
        mHomeAdapter = new HomeAdapter(getActivity(), mArraylists, this);
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
        mArraylists.add(homeCategory);

        HomeCategory homeCategory1 = new HomeCategory();
        homeCategory1.setMAvataHome(R.drawable.img_vegetables);
        homeCategory1.setMNameHome("Vegetables");
        mArraylists.add(homeCategory1);

        HomeCategory homeCategory2 = new HomeCategory();
        homeCategory2.setMAvataHome(R.drawable.img_transport);
        homeCategory2.setMNameHome("Transport");
        mArraylists.add(homeCategory2);

        HomeCategory homeCategory3 = new HomeCategory();
        homeCategory3.setMAvataHome(R.drawable.img_vegetables);
        homeCategory3.setMNameHome("Vegetables");
        mArraylists.add(homeCategory3);
    }

    @Override
    public void callFragmentAnimal() {
        AnimalFragment_ mFragment = new AnimalFragment_();
        android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(null);
        fragmentTransaction.replace(R.id.framlayoutTop, mFragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onTabClick(int position) {

    }
}