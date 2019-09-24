package com.beecoder77.madesubmission4.layout.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.beecoder77.madesubmission4.R;
import com.beecoder77.madesubmission4.adapter.ItemClickSupport;
import com.beecoder77.madesubmission4.adapter.TvAdapter;
import com.beecoder77.madesubmission4.db.DbCallback;
import com.beecoder77.madesubmission4.db.TvHelper;
import com.beecoder77.madesubmission4.layout.activity.TvActivity;
import com.beecoder77.madesubmission4.model.Item;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.beecoder77.madesubmission4.layout.activity.TvActivity.EXTRA_TV;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVFavoriteFragment extends Fragment implements DbCallback{

    private ProgressBar mProgressBar;
    private TvHelper mTvHelper;
    private TvAdapter tvAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Item>items = new ArrayList<>();

    public TVFavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_favorite, container, false);
        mProgressBar = view.findViewById(R.id.pb_tv_fav);
        recyclerView = view.findViewById(R.id.rv_tv_fav);

        mTvHelper = TvHelper.getINSTANCE(getContext());
        mTvHelper.open();

        showRecyclerView();
        recyclerView.addOnItemTouchListener(new ItemClickSupport(getContext(), recyclerView, new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Item mItem = new Item();
                mItem.setId(items.get(position).getId());
                mItem.setPhoto(items.get(position).getPhoto());
                mItem.setTitle(items.get(position).getTitle());
                mItem.setDescription(items.get(position).getDescription());
                Intent detail = new Intent(getContext(), TvActivity.class);

                detail.putExtra(EXTRA_TV, mItem);
                startActivity(detail);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));

        new LoadTvAsync(mTvHelper, this).execute();
        return view;
    }

    private void showRecyclerView() {
        tvAdapter = new TvAdapter(getContext());
        tvAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(tvAdapter);
    }


    @Override
    public void preExecute() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void postExecute(ArrayList<Item> mItem) {
        mProgressBar.setVisibility(View.GONE);
        tvAdapter.setItemArrayList(mItem);
        items.addAll(mItem);
    }

    private class LoadTvAsync  extends AsyncTask<Void, Void, ArrayList<Item>> {

        WeakReference<TvHelper> tvHelperWeakReference;
        WeakReference<DbCallback>loadFavCallbackWeakReference;
        public LoadTvAsync(TvHelper tvHelper, DbCallback context) {
            tvHelperWeakReference = new WeakReference<>(tvHelper);
            loadFavCallbackWeakReference = new WeakReference<>(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadFavCallbackWeakReference.get().preExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<Item> mItem) {
            super.onPostExecute(mItem);
            loadFavCallbackWeakReference.get().postExecute(mItem);
        }

        @Override
        protected ArrayList<Item> doInBackground(Void... voids) {
            Log.d("test", "test" + tvHelperWeakReference.get().getAllTv());
            return tvHelperWeakReference.get().getAllTv();
        }
    }
}
