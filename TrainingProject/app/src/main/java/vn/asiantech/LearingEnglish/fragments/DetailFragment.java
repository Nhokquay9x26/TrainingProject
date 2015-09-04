package vn.asiantech.LearingEnglish.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.models.AnimalCategory;
import vn.asiantech.LearingEnglish.models.DetailCategory;

/**
 * Created by xuanphu on 04/09/2015.
 */
public class DetailFragment extends BaseFragment {
    private ArrayList<AnimalCategory> mArraylist = new ArrayList<>();
    private int mPosition;
    private ImageView mImgAvataDetail;
    private TextView mTvNameDetail;

    public DetailFragment(ArrayList<AnimalCategory> mArraylist, int mPosition) {
        this.mArraylist = mArraylist;
        this.mPosition = mPosition;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_detail,container, false);
        mImgAvataDetail = (ImageView)mView.findViewById(R.id.imgAvataDetail);
        mTvNameDetail = (TextView)mView.findViewById(R.id.tvNameDetail);

        mImgAvataDetail.setImageResource(mArraylist.get(mPosition).getMAvataAnimal());
        mTvNameDetail.setText(mArraylist.get(mPosition).getMNameAnimal());
        return mView;
    }
}
