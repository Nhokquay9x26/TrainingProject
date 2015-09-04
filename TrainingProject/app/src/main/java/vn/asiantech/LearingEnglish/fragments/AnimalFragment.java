package vn.asiantech.LearingEnglish.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import org.androidannotations.annotations.*;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.AnimalAdapter;;
import vn.asiantech.LearingEnglish.models.AnimalCategory;
import vn.asiantech.LearingEnglish.utils.IsCallTop;
import vn.asiantech.LearingEnglish.utils.RecyclerItemClickListener;

/**
 * @Author xuanphu
 * Created by xuanphu on 04/09/2015.
 */
@EFragment(R.layout.fragment_animal)
public class AnimalFragment extends BaseFragment{
    @ViewById(R.id.recyclerAnimal)
    RecyclerView mRecyclerAnimal;
    AnimalAdapter mAnimalAdapter;
    ArrayList<AnimalCategory> mArraylist = new ArrayList<>();
    public AnimalFragment(){
        fakedata();
    }

    @AfterViews
    void afterViews() {
        mAnimalAdapter = new AnimalAdapter(getActivity(), mArraylist);
        mRecyclerAnimal.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        mRecyclerAnimal.setAdapter(mAnimalAdapter);
        mRecyclerAnimal.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerAnimal, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                DetailFragment mFragment = new DetailFragment(mArraylist,position);
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

    private void fakedata() {
        AnimalCategory animalCategory = new AnimalCategory();
        animalCategory.setMAvataAnimal(R.drawable.img_animal);
        animalCategory.setMNameAnimal("Lion");
        mArraylist.add(animalCategory);

        AnimalCategory animalCategory1 = new AnimalCategory();
        animalCategory1.setMAvataAnimal(R.drawable.img_tiger);
        animalCategory1.setMNameAnimal("Tiger");
        mArraylist.add(animalCategory1);
    }
}

