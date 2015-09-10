package vn.asiantech.LearingEnglish.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.models.AnimalCategory;

/**
 * @Author xuanphu
 * Created by xuanphu on 04/09/2015.
 */
public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {
    Context mContext;
    private ArrayList<AnimalCategory> mArraylists = new ArrayList<>();

    public AnimalAdapter(Context mContext, ArrayList<AnimalCategory> mArraylist) {
        this.mContext = mContext;
        this.mArraylists = mArraylist;
    }

    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycleview_animal, parent, false);
        AnimalViewHolder viewHolder = new AnimalViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AnimalViewHolder holder, int position) {
        final AnimalCategory animalCategory = mArraylists.get(position);
        holder.imgAvataAnimal.setImageResource(animalCategory.getMAvataAnimal());
        holder.tvNameAnimal.setText(animalCategory.getMNameAnimal());
    }

    @Override
    public int getItemCount() {
        return mArraylists.size();
    }

    public class AnimalViewHolder extends RecyclerView.ViewHolder {
        vn.asiantech.LearingEnglish.utils.CircleImageView imgAvataAnimal;
        TextView tvNameAnimal;

        public AnimalViewHolder(View itemView) {
            super(itemView);
            imgAvataAnimal = (vn.asiantech.LearingEnglish.utils.CircleImageView) itemView.findViewById(R.id.imgAvataItemAnimal);
            tvNameAnimal = (TextView) itemView.findViewById(R.id.tvNameItemAnimal);
        }
    }
}
