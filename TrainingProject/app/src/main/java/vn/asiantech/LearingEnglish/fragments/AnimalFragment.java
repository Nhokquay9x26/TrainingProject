package vn.asiantech.LearingEnglish.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.androidannotations.annotations.*;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.AnimalAdapter;;
import vn.asiantech.LearingEnglish.models.AnimalCategory;
import vn.asiantech.LearingEnglish.utils.RecyclerItemClickListener;
import vn.asiantech.LearingEnglish.utils.TabBar;

/**
 * @Author xuanphu
 * Created by xuanphu on 04/09/2015.
 */
@EFragment(R.layout.fragment_animal)
public class AnimalFragment extends BaseFragment implements TabBar.OnTabBarListener {
    @ViewById(R.id.recyclerAnimal)
    RecyclerView mRecyclerAnimal;
    AnimalAdapter mAnimalAdapter;
    ArrayList<AnimalCategory> mArraylists = new ArrayList<>();

    public AnimalFragment() {
        fakedata();
    }

    @AfterViews
    void afterViews() {
        mAnimalAdapter = new AnimalAdapter(getActivity(), mArraylists);
        mRecyclerAnimal.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        mRecyclerAnimal.setAdapter(mAnimalAdapter);
        mRecyclerAnimal.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerAnimal, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                DetailFragment mFragment = new DetailFragment(mArraylists, position);
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(null);
                fragmentTransaction.replace(R.id.framlayoutTop, mFragment);
                fragmentTransaction.commit();

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    @Click
    void imgBackHeaderAnimal(){
        getActivity().onBackPressed();
    }

    private void fakedata() {
        AnimalCategory animalCategory = new AnimalCategory();
        animalCategory.setMAvataAnimal(R.drawable.img_animal);
        animalCategory.setMNameAnimal("Lion");
        animalCategory.setMExample("Three year-old male lions grow manes that vary in color from black to blond");
        animalCategory.setMFavorite(false);
        animalCategory.setMMeans("Sư Tử");
        animalCategory.setMSpelling("/līən/");
        mArraylists.add(animalCategory);

        AnimalCategory animalCategory1 = new AnimalCategory();
        animalCategory1.setMAvataAnimal(R.drawable.img_tiger);
        animalCategory1.setMNameAnimal("Tiger");
        animalCategory1.setMExample(" columbia tiger lily");
        animalCategory1.setMFavorite(false);
        animalCategory1.setMMeans("Hổ, Cop");
        animalCategory1.setMSpelling("/'taigə/");
        mArraylists.add(animalCategory1);

        AnimalCategory animalCategory2 = new AnimalCategory();
        animalCategory2.setMAvataAnimal(R.drawable.img_animal);
        animalCategory2.setMNameAnimal("Cat Big");
        animalCategory2.setMExample(" columbia big cat lily");
        animalCategory2.setMFavorite(false);
        animalCategory2.setMMeans("Con meo");
        animalCategory2.setMSpelling("/'taigə/");
        mArraylists.add(animalCategory2);
    }

    @Override
    public void onTabClick(int position) {

    }

}

