package vn.asiantech.LearingEnglish.adapter;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.models.WordDetail;

public class WordDetailAdapter extends PagerAdapter implements TextToSpeech.OnInitListener,
        View.OnClickListener {
    private List<WordDetail> mWordDetails;
    private LayoutInflater mLayoutInflater;
    private OnWordDetailListener mListener;
    private Context mContext;
    private TextToSpeech mTextToSpeech;

    /**
     * constructor of class customDetailTopNew
     */
    public WordDetailAdapter(Context context, List<WordDetail> wordDetails,
                             OnWordDetailListener listener) {
        super();
        this.mContext = context;
        this.mWordDetails = wordDetails;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mListener = listener;
    }

    /*
    * return size of arrayList
     */
    @Override
    public int getCount() {
        return mWordDetails.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void onInit(int i) {
        if (i != TextToSpeech.ERROR) {
            mTextToSpeech.setLanguage(Locale.US);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imgNext) {
            mListener.onClickNext();
        }
        if (view.getId() == R.id.imgBack) {
            mListener.onClickBack();
        }
    }

    private class ViewHolder {
        private ImageView mImgVocabulary;
        private TextView mTxtVocabulary;
        private TextView mTxtMeanVocabulary;
        private TextView mTxtExample;
        private ImageView mImgNext;
        private ImageView mImgBack;
        private ImageView mImgSpeaker;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final ViewHolder viewHolder;
        View view = mLayoutInflater.inflate(R.layout.fragment_detail_word, container, false);
        if (view != null) {
            viewHolder = new ViewHolder();
            viewHolder.mImgVocabulary = (ImageView) view.findViewById(R.id.imgVocabulary);
            viewHolder.mTxtVocabulary = (TextView) view.findViewById(R.id.tvVocabulary);
            viewHolder.mTxtMeanVocabulary = (TextView) view.findViewById(R.id.tvMeanVocabulary);
            viewHolder.mTxtExample = (TextView) view.findViewById(R.id.tvExample);
            viewHolder.mImgNext = (ImageView) view.findViewById(R.id.imgNext);
            viewHolder.mImgBack = (ImageView) view.findViewById(R.id.imgBack);
            viewHolder.mImgSpeaker = (ImageView) view.findViewById(R.id.imgSpeaker);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        WordDetail wordDetail = mWordDetails.get(position);
        mTextToSpeech = new TextToSpeech(mContext, this);
        setValue(viewHolder, wordDetail);
        setEvent(viewHolder);
        container.addView(view);
        return view;
    }

    private void setEvent(final ViewHolder viewHolder) {
        viewHolder.mImgNext.setOnClickListener(this);
        viewHolder.mImgBack.setOnClickListener(this);
        viewHolder.mImgSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextToSpeech.speak(viewHolder.mTxtVocabulary.getText().toString(),
                        TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    private void setValue(ViewHolder viewHolder, WordDetail wordDetail) {
        viewHolder.mImgVocabulary.setImageResource(wordDetail.getImage());
        viewHolder.mTxtVocabulary.setText(wordDetail.getWord());
        viewHolder.mTxtMeanVocabulary.setText(wordDetail.getMean());
        viewHolder.mTxtExample.setText(wordDetail.getExample());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    public interface OnWordDetailListener {
        void onClickNext();

        void onClickBack();
    }
}