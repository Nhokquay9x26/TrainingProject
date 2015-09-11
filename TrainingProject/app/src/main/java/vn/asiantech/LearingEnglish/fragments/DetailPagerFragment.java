package vn.asiantech.LearingEnglish.fragments;

import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;

/**
 * @author DaoQuocViet
 * Created by nhokquay9x26 on 11/09/2015.
 */
@EFragment(R.layout.detail_custom_viewpaper)
public class DetailPagerFragment extends BaseFragment {
    int NumFragment;
    String[] mDetail;

    @ViewById(R.id.imgWord)
    ImageView mImgWord;
    @ViewById(R.id.imgBack)
    ImageView mImgBack;
    @ViewById(R.id.tvWord)
    TextView mTvWord;
    @ViewById(R.id.imgNext)
    ImageView mImgNext;


}
