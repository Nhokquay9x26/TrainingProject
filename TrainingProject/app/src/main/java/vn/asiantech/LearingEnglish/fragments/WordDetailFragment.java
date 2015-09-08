package vn.asiantech.LearingEnglish.fragments;

import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.models.WordDetail;

@EFragment(R.layout.fragment_detail_word)
public class WordDetailFragment extends Fragment {
    @ViewById(R.id.imgVocabulary)
    ImageView mImgVocabulary;
    @ViewById(R.id.tvVocabulary)
    TextView mTxtVocabulary;
    @ViewById(R.id.tvMeanVocabulary)
    TextView mTxtMeanVocabulary;
    @ViewById(R.id.tvExample)
    TextView mTxtExample;
    private OnSlidePagerListener mListener;

    @AfterViews
    void afterView() {
        WordDetail wordDetail = (WordDetail) getArguments().getSerializable("wordDetail");
        setValue(wordDetail);
    }

    private void setValue(WordDetail wordDetail) {
        mImgVocabulary.setImageResource(wordDetail.getImage());
        mTxtVocabulary.setText(wordDetail.getWord());
        mTxtMeanVocabulary.setText(wordDetail.getMean());
        mTxtExample.setText(wordDetail.getExample());
    }

    @Click(R.id.imgNext)
    void onClickNext(){
        mListener.onSlide(1);
    }

    @Click(R.id.imgBack)
    void onClickBack(){
        mListener.onSlide(-1);
    }

    public interface OnSlidePagerListener{
        void onSlide(int direction);
    }

    public void setOnSlidePagerListener(OnSlidePagerListener mListener){
        this.mListener=mListener;
    }
}
