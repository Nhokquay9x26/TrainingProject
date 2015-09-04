package vn.asiantech.LearingEnglish.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.HomeAdapter;
import vn.asiantech.LearingEnglish.models.HomeCategory;
import vn.asiantech.LearingEnglish.utils.IsCallTop;

/**
 * @Author xuanphu
 * Created by xuanphu on 04/09/2015.
 */
@EFragment(R.layout.fragment_home)
public class HomeFragment extends BaseFragment implements IsCallTop{

    @ViewById(R.id.recyclerHome)
    RecyclerView mRecyclerHome;
    HomeAdapter mHomeAdapter;
    ArrayList<HomeCategory> mArraylist = new ArrayList<>();
    public HomeFragment(){
        fakedata();
    }

    @AfterViews
    void afterViews() {
        mHomeAdapter = new HomeAdapter(getActivity(), mArraylist, this);
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

    @Override
    public void callFragmentAnimal() {
        AnimalFragment_ mFragment = new AnimalFragment_();
        android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(null);
        fragmentTransaction.replace(R.id.framlayoutTop, mFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void callFragmentDetail() {

    }
}
