package vn.asiantech.LearingEnglish.adapter;


import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.models.WordsEnglish;
import vn.asiantech.LearingEnglish.utils.NotifyDataSetChanged;

/**
 * @author mrson
 * Created by mrson on 31/08/2015.
 */
@SuppressWarnings("deprecation")
public class FavoriteAdapter extends  RecyclerView.Adapter<FavoriteAdapter.ViewHolder> implements TextToSpeech.OnInitListener{
    Context mcontext;
    public List<WordsEnglish> mListWords= new ArrayList<>();
    TextToSpeech textToSpeech;
    NotifyDataSetChanged mNotifyDataSetChanged;

    public FavoriteAdapter(Context mcontext, List<WordsEnglish> mListWords, NotifyDataSetChanged mNotifyDataSetChanged) {
        this.mcontext = mcontext;
        this.mListWords = mListWords;
        this.mNotifyDataSetChanged = mNotifyDataSetChanged;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_recycle_favorite, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final WordsEnglish wordsEnglish= mListWords.get(position);
        textToSpeech = new TextToSpeech(mcontext,this);
        holder.tvFavoriteWord.setText(wordsEnglish.getmNewWord());
        holder.tvFavoriteSpellWord.setText(wordsEnglish.getmSpellingWord());
        holder.tvFavoriteMeans.setText(wordsEnglish.getmMeans());

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNotifyDataSetChanged.NotifyDataSetChanged(position);
            }
        });

        holder.imgListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontext, "click listen", Toast.LENGTH_SHORT).show();

                toSpeed(wordsEnglish.getmNewWord());

            }
        });
    }

    @Override
    public int getItemCount() {
        return mListWords.size();
    }

    @Override
    public void onInit(int status) {
        if(status != TextToSpeech.ERROR) {
            textToSpeech.setLanguage(Locale.UK);
        }

    }
    @SuppressWarnings("deprecation")
    public void toSpeed(String toSpeak){
        textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }

    class ViewHolder extends RecyclerView.ViewHolder{


        public TextView tvFavoriteWord;
        public TextView tvFavoriteSpellWord;
        public TextView tvFavoriteMeans;
        public ImageView imgListen;
        public ImageView imgDelete;
        public ViewHolder(View itemView) {
            super(itemView);
            tvFavoriteWord = (TextView)itemView.findViewById(R.id.tvFavoriteWord);
            tvFavoriteSpellWord = (TextView)itemView.findViewById(R.id.tvFavoriteSpell);
            tvFavoriteMeans = (TextView)itemView.findViewById(R.id.tvFavoriteMeans);
            imgListen=(ImageView)itemView.findViewById(R.id.imgFavoriteSpell);
            imgDelete = (ImageView)itemView.findViewById(R.id.imgDeleteFavorite);

        }
    }

}
