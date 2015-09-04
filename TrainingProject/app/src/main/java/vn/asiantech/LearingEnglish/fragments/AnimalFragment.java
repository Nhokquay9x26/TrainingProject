package vn.asiantech.LearingEnglish.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.*;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.AnimalAdapter;;
import vn.asiantech.LearingEnglish.models.AnimalCategory;

/**
 * @Author xuanphu
 * Created by xuanphu on 04/09/2015.
 */
@EFragment(R.layout.fragment_animal)
public class AnimalFragment extends BaseFragment {
    @ViewById(R.id.recyclerAnimal)
    RecyclerView mRecyclerAnimal;
    AnimalAdapter mAnimalAdapter;
    ArrayList<AnimalCategory> mArraylist = new ArrayList<>();

    @AfterViews
    void afterViews() {
        fakedata();
        mAnimalAdapter = new AnimalAdapter(getActivity(), mArraylist);
        mRecyclerAnimal.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        mRecyclerAnimal.setAdapter(mAnimalAdapter);
    }

    private void fakedata() {
        AnimalCategory animalCategory = new AnimalCategory();
        animalCategory.setMAvataAnimal(R.drawable.img_animal);
        animalCategory.setMNameAnimal("Lion");
        mArraylist.add(animalCategory);

        AnimalCategory animalCategory1 = new AnimalCategory();
        animalCategory1.setMAvataAnimal(R.drawable.img_animal);
        animalCategory1.setMNameAnimal("Tiger");
        mArraylist.add(animalCategory1);
    }
}
