package com.beecoder77.madesubmission4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beecoder77.madesubmission4.R;
import com.beecoder77.madesubmission4.model.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder> {

    private Context context;
    ArrayList<Item> itemArrayList = new ArrayList<>();

    public TvAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Item> getItemArrayList() {
        return itemArrayList;
    }

    public void setItemArrayList(ArrayList<Item> itemArrayList) {
        this.itemArrayList = itemArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tv_tab, parent, false);
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder holder, int position) {
        Item item = itemArrayList.get(position);
        holder.title.setText(item.getTitle());
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w185" + item.getPhoto())
                .resize(100,100)
                .into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public class TvViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView title;
        public TvViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_title_tv);
            photo = itemView.findViewById(R.id.img_tv);
        }
    }
}
