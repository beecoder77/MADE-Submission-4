package com.beecoder77.madesubmission4.layout.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.beecoder77.madesubmission4.R;
import com.beecoder77.madesubmission4.adapter.ItemClickSupport;
import com.beecoder77.madesubmission4.adapter.TvAdapter;
import com.beecoder77.madesubmission4.layout.activity.TvActivity;
import com.beecoder77.madesubmission4.model.Item;
import com.beecoder77.madesubmission4.viewmodel.TvViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVFragment extends Fragment {

    TvAdapter tvAdapter;
    TvViewModel tvViewModel;
    ProgressBar progressBar;
    ImageView photo;
    private RecyclerView recyclerViewTV;
    private ArrayList<Item> itemArrayList = new ArrayList<>();

    public TVFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv, container, false);

        progressBar = view.findViewById(R.id.pb_tv);
        progressBar.setVisibility(View.VISIBLE);

        recyclerViewTV = view.findViewById(R.id.rv_tv);
        photo = view.findViewById(R.id.img_tv);
        
        prepare();

        showRecyclerView();

        recyclerViewTV.addOnItemTouchListener(new ItemClickSupport(getContext(), recyclerViewTV, new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Item item = new Item();
                item.setTitle(tvViewModel.itemArrayList.get(position).getTitle());
                item.setPhoto(tvViewModel.itemArrayList.get(position).getPhoto());
                item.setDescription(tvViewModel.itemArrayList.get(position).getDescription());
                Intent detailMovie = new Intent(getContext(), TvActivity.class);
                detailMovie.putExtra(TvActivity.EXTRA_TV, item);
                startActivity(detailMovie);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Item item = new Item();
                item.setTitle(tvViewModel.itemArrayList.get(position).getTitle());
                item.setPhoto(tvViewModel.itemArrayList.get(position).getPhoto());
                item.setDescription(tvViewModel.itemArrayList.get(position).getDescription());
                Intent detailMovie = new Intent(getContext(), TvActivity.class);
                detailMovie.putExtra(TvActivity.EXTRA_TV, item);
                startActivity(detailMovie);
            }
        }));

        return view;
    }

    private void showRecyclerView() {
        tvAdapter = new TvAdapter(getContext());
        tvAdapter.notifyDataSetChanged();
        recyclerViewTV.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewTV.setAdapter(tvAdapter);
    }

    private void prepare() {
        tvViewModel = ViewModelProviders.of(getActivity()).get(TvViewModel.class);
        tvViewModel.tv().observe(TVFragment.this, getTv);
        tvViewModel.getData();
    }

    private Observer<ArrayList<Item>> getTv = new Observer<ArrayList<Item>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Item> items) {
            if(items != null){
                tvAdapter.setItemArrayList(items);
                progressBar.setVisibility(View.GONE);
            }
        }
    };
}
