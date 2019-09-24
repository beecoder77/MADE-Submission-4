package com.beecoder77.madesubmission4.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.beecoder77.madesubmission4.R;
import com.beecoder77.madesubmission4.model.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TvViewModel extends AndroidViewModel {

    RequestQueue mRequestQueue;
    private MutableLiveData<ArrayList<Item>> item = new MutableLiveData<>();
    String url;
    public ArrayList<Item> itemArrayList = new ArrayList<>();
    public TvViewModel(@NonNull Application application) {
        super(application);
        mRequestQueue = Volley.newRequestQueue(application);
        url = application.getResources().getString(R.string.api_tv);
    }

    public void getData(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject result = jsonArray.getJSONObject(i);
                        String title = result.getString("name");
                        String photo = result.getString("poster_path");
                        String description = result.getString("overview");
                        Item mItem = new Item();
                        mItem.setTitle(title);
                        mItem.setPhoto(photo);
                        mItem.setDescription(description);

                        itemArrayList.add(mItem);
                    }

                    item.postValue(itemArrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }
    public LiveData<ArrayList<Item>> tv(){
        return item;
    }
}