package vn.asiantech.LearingEnglish.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import java.util.ArrayList;
import vn.asiantech.LearingEnglish.R;
import java.util.List;
import vn.asiantech.LearingEnglish.adapter.RatingAdapter;
import vn.asiantech.LearingEnglish.models.RatingUser;
/**
 * @author mrs
 * Created by mrson on 31/08/2015.
 * Created by tantv on 03/09/2015.
 */
@EFragment (R.layout.fragment_rating)
public class RatingsFragment extends BaseFragment {
    @ViewById(R.id.recyclerViewRating)
    RecyclerView mRecyclerViewRating;
    private final List<RatingUser> mRatingUsers = new ArrayList<>();

    @AfterViews
    void afterView(){
        fakeData();
        RatingAdapter ratingAdapter = new RatingAdapter(getActivity(), mRatingUsers);
        mRecyclerViewRating.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        mRecyclerViewRating.setAdapter(ratingAdapter);}

    private void fakeData(){
        RatingUser ratingUser = new RatingUser();
        ratingUser.setRank("1");
        ratingUser.setUserName("Nguyen Tran Thai Son");
        ratingUser.setPoint("100");
        ratingUser.setProfileRating(R.drawable.img_profile_test);
        mRatingUsers.add(ratingUser);

        RatingUser ratingUser1 = new RatingUser();
        ratingUser1.setRank("2");
        ratingUser1.setUserName("Nguyen Tran Thai Son");
        ratingUser1.setPoint("99");
        ratingUser1.setProfileRating(R.drawable.img_profile_test);
        mRatingUsers.add(ratingUser1);
    }
}