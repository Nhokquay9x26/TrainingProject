package vn.asiantech.LearingEnglish.adapter;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.models.HomeCategory;
import vn.asiantech.LearingEnglish.models.WordsEnglish;

/**
 * Created by xuanphu on 01/09/2015.
 */
public class HomeAdapter extends  RecyclerView.Adapter<HomeAdapter.HomeViewHolder>{
    Context mContext;
    private ArrayList<HomeCategory> mArraylist = new ArrayList<>();

    public HomeAdapter(Context mContext, ArrayList<HomeCategory> mArraylist) {
        this.mContext = mContext;
        this.mArraylist = mArraylist;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview_home, parent, false);
        HomeViewHolder viewHolder = new HomeViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        final HomeCategory homeCategory= mArraylist.get(position);
        holder.imgAvataHome.setImageResource(homeCategory.getMAvataHome());
        holder.tvNameHone.setText(homeCategory.getMNameHome());
    }

    @Override
    public int getItemCount() {
        return mArraylist.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
//        @ViewById(R.id.imgAvataItemHome)
//        ImageView imgAvataHome;
//        @ViewById(R.id.tvNameItemHome)
//        TextView tvNameHone;
        vn.asiantech.LearingEnglish.utils.CircleImageView imgAvataHome;
        TextView tvNameHone;
        public HomeViewHolder(View itemView) {
            super(itemView);
            imgAvataHome = (vn.asiantech.LearingEnglish.utils.CircleImageView)itemView.findViewById(R.id.imgAvataItemHome);
            tvNameHone = (TextView)itemView.findViewById(R.id.tvNameItemHome);
        }
    }

}
