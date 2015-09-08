package vn.asiantech.LearingEnglish.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.models.WordDetail;

public class WordDetailAdapter extends PagerAdapter {
    private List<WordDetail> mWordDetails;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    /**
     * contrustor of class customDetaiTopNew
     */
    public WordDetailAdapter(Context context, List<WordDetail> wordDetails) {
        super();
        this.mContext = context;
        this.mWordDetails = wordDetails;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /*
    * return size of arraylist
     */
    @Override
    public int getCount() {
        return mWordDetails.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    private class ViewHolder {
        ImageView mImgVocabulary;
        TextView mTxtVocabulary;
        TextView mTxtMeanVocabulary;
        TextView mTxtExample;
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
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        WordDetail wordDetail = mWordDetails.get(position);
        viewHolder.mImgVocabulary.setImageResource(wordDetail.getImage());
        viewHolder.mTxtVocabulary.setText(wordDetail.getWord());
        viewHolder.mTxtMeanVocabulary.setText(wordDetail.getMean());
        viewHolder.mTxtExample.setText(wordDetail.getExample());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}