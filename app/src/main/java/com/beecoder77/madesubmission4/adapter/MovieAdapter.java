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

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    ArrayList<Item> itemArrayList = new ArrayList<>();

    public MovieAdapter(Context context) {
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
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_tab, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
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

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView title;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_title_movie);
            photo = itemView.findViewById(R.id.img_movie);
        }
    }
}
