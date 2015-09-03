package vn.asiantech.LearingEnglish.adapter;


import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
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

/**
 * @author mrson
 * Created by mrson on 31/08/2015.
 */
public class FavoriteAdapter extends  RecyclerView.Adapter<FavoriteAdapter.ViewHolder> implements TextToSpeech.OnInitListener{
    Context mcontext;
    public List<WordsEnglish> mlist= new ArrayList<WordsEnglish>();
    TextToSpeech textToSpeech;

    public FavoriteAdapter(FragmentActivity mcontext, List<WordsEnglish> mlist) {
        this.mcontext = mcontext;
        this.mlist = mlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_recycle_favorite, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final WordsEnglish wordsEnglish= mlist.get(position);
        textToSpeech = new TextToSpeech(mcontext,this);
        holder.tvFavoriteWord.setText(wordsEnglish.getNewWord());
        holder.tvSpellWord.setText(wordsEnglish.getSpellingWord());


        holder.imgListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontext,"click listen",Toast.LENGTH_SHORT).show();

                toSpeed(wordsEnglish.getNewWord().toString());

            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    @Override
    public void onInit(int status) {
        if(status != TextToSpeech.ERROR) {
            textToSpeech.setLanguage(Locale.UK);
        }

    }
    public void toSpeed(String toSpeak){
        textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }

    class ViewHolder extends RecyclerView.ViewHolder{


        public TextView tvFavoriteWord;
        public TextView tvSpellWord;
        public ImageView imgListen;
        public ViewHolder(View itemView) {
            super(itemView);
            tvFavoriteWord = (TextView)itemView.findViewById(R.id.tvFavoriteWord);
            tvSpellWord = (TextView)itemView.findViewById(R.id.tvFavoriteSpell);
            imgListen=(ImageView)itemView.findViewById(R.id.imgFavoriteSpell);

        }
    }

}
