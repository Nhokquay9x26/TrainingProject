package vn.asiantech.LearingEnglish.adapter;

import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.imageaware.ImageAware;

import java.util.ArrayList;
import java.util.Locale;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.fragments.FavoriteFragment;
import vn.asiantech.LearingEnglish.models.AnimalCategory;
import vn.asiantech.LearingEnglish.models.WordsEnglish;
import vn.asiantech.LearingEnglish.utils.NotifyDataSetChanged;

/**
 * @
 * Created by xuanphu on 05/09/2015.
 */
public class ViewpagerDetailAdapter extends PagerAdapter implements TextToSpeech.OnInitListener {
    private Context mContext;
    private ArrayList<AnimalCategory> mArraylists = new ArrayList<>();
    private LayoutInflater inflater;
    private ImageView imgLeft;
    private ImageView imgRight;
    private CallBackNext mCallBackNext;
    private TextToSpeech mTexttoSpeech;
    private WordsEnglish mWordsEnglish;

    public ViewpagerDetailAdapter(Context mContext, ArrayList<AnimalCategory> mArraylists, CallBackNext mCallBackNext) {
        this.mContext = mContext;
        this.mArraylists = mArraylists;
        this.mCallBackNext = mCallBackNext;
    }


    @Override
    public int getCount() {
        return mArraylists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imgAvataDetail;
        TextView tvNameDetail;
        TextView tvExample;
        TextView tvSpelling;
        TextView tvMeans;
        TextView tvNameVocabulary;
        final ImageView imgFavoriteDetail;
        ImageView imgListenDetail;
        mTexttoSpeech = new TextToSpeech(mContext, this);

        inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_fragment_detail, container,
                false);
        imgAvataDetail = (ImageView) itemView.findViewById(R.id.imgAvataDetail);
        tvNameDetail = (TextView) itemView.findViewById(R.id.tvNameDetail);
        tvExample = (TextView) itemView.findViewById(R.id.tvExample);
        tvSpelling = (TextView) itemView.findViewById(R.id.tvSpelling);
        tvNameVocabulary = (TextView) itemView.findViewById(R.id.tvNameVocabulary);
        tvMeans = (TextView) itemView.findViewById(R.id.tvMean);

        imgLeft = (ImageView) itemView.findViewById(R.id.imgSelecLeftDetail);
        imgRight = (ImageView) itemView.findViewById(R.id.imgSelecRightDetail);
        imgFavoriteDetail = (ImageView) itemView.findViewById(R.id.imgFavoriteDetail);
        imgListenDetail = (ImageView) itemView.findViewById(R.id.imgListenDetail);
        imgLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBackNext.OnClickBack();
            }
        });
        imgRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBackNext.OnClickNext();
            }
        });
        imgFavoriteDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgFavoriteDetail.setVisibility(View.INVISIBLE);
                mArraylists.get(position).setMFavorite(true);
                mWordsEnglish = new WordsEnglish(mArraylists.get(position).getMNameAnimal(), mArraylists.get(position).getMSpelling(), mArraylists.get(position).getMMeans());
                mWordsEnglish.save();

                Intent in = new Intent(FavoriteFragment.ACTION);
                mContext.sendBroadcast(in);

                Toast.makeText(mContext, "Save Favorite", Toast.LENGTH_SHORT).show();
            }
        });
        imgListenDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "click listen", Toast.LENGTH_SHORT).show();
                toSpeed(mArraylists.get(position).getMNameAnimal());
            }
        });

        imgAvataDetail.setImageResource(mArraylists.get(position).getMAvataAnimal());
        tvNameDetail.setText(mArraylists.get(position).getMNameAnimal());
        tvMeans.setText(mArraylists.get(position).getMMeans());
        tvExample.setText(mArraylists.get(position).getMExample());
        tvSpelling.setText(mArraylists.get(position).getMSpelling());
        tvNameVocabulary.setText(mArraylists.get(position).getMNameAnimal());
        if (mArraylists.get(position).isMFavorite()) {
            imgFavoriteDetail.setVisibility(View.INVISIBLE);
        } else {
            imgFavoriteDetail.setImageResource(R.drawable.icon_unfavorite);
        }

        ((ViewPager) container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }

    @Override
    public void onInit(int status) {
        if (status != TextToSpeech.ERROR) {
            mTexttoSpeech.setLanguage(Locale.UK);
        }
    }

    public interface CallBackNext {
        void OnClickNext();

        void OnClickBack();
    }

    @SuppressWarnings("deprecation")
    public void toSpeed(String toSpeak) {
        mTexttoSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }
}
