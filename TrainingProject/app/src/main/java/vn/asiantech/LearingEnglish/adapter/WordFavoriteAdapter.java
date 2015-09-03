package vn.asiantech.LearingEnglish.adapter;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import vn.asiantech.LearingEnglish.R;

/**
 * Created by hoai on 31/08/2015.
 */
public class WordFavoriteAdapter extends RecyclerView.Adapter<WordFavoriteAdapter.RecyclerViewHolder> {

    private List<vn.asiantech.LearingEnglish.models.WordFavorite> mFavorites = new ArrayList<>();
    private Context mContext;
    public WordFavoriteAdapter(List<vn.asiantech.LearingEnglish.models.WordFavorite> mFavorites, Context context) {
        this.mFavorites = mFavorites;
        this.mContext = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int positon) {
        View mViewItem = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_favorite, viewGroup, false);
        return new RecyclerViewHolder(mViewItem);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int position) {
        viewHolder.mTvWord.setText(mFavorites.get(position).getWord());
        viewHolder.mTvPronunciation.setText(mFavorites.get(position).getPronunciation());

    }

    @Override
    public int getItemCount() {
        return mFavorites.size();
    }

    /**
     * Viewholder for item view of list
     */
   class RecyclerViewHolder extends RecyclerView.ViewHolder implements TextToSpeech.OnInitListener, View.OnClickListener {
        private TextView mTvWord, mTvPronunciation;
        private ImageView mImgSound;
        private TextToSpeech mTextToSpeed;

        int a;
        public RecyclerViewHolder(View convertView) {
            super(convertView);
            mTextToSpeed = new TextToSpeech(mContext, this);
            mTvWord = (TextView) convertView.findViewById(R.id.tvWordFavorite);
            mTvPronunciation = (TextView) convertView.findViewById(R.id.tvSpellWord);
            mImgSound = (ImageView) convertView.findViewById(R.id.imgSound);
            mImgSound.setOnClickListener(this);

        }

        private void excuteSound(String word){
            mTextToSpeed.speak(word,TextToSpeech.QUEUE_FLUSH,null);
        }

        @Override
        public void onInit(int status) {
            if(status!= TextToSpeech.ERROR){
                mTextToSpeed.setLanguage(Locale.US);
            }
        }

        @Override
        public void onClick(View view) {
            excuteSound(mTvWord.getText().toString());
        }
    }
}

