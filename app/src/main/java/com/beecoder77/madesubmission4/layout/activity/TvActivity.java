package com.beecoder77.madesubmission4.layout.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.beecoder77.madesubmission4.R;
import com.beecoder77.madesubmission4.db.TvHelper;
import com.beecoder77.madesubmission4.model.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class TvActivity extends AppCompatActivity {

    public static final String EXTRA_TV = "extra_tv";

    TextView title, description;
    ImageView photo;
    FloatingActionButton fab;
    ProgressBar progressBar;
    Item item;
    TvHelper tvHelper;
    Boolean act = true;
    Boolean insert = true;
    Boolean delete = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv);

        photo = findViewById(R.id.img_tv);
        title = findViewById(R.id.tv_title);
        description = findViewById(R.id.tv_description);
        progressBar = findViewById(R.id.pb_detail_tv);
        fab = findViewById(R.id.fab_tv);

        Item item = getIntent().getParcelableExtra(EXTRA_TV);
        tvHelper = TvHelper.getINSTANCE(getApplicationContext());
        String tvTitle = item.getTitle();
        if (tvHelper.getOne(tvTitle)){
            act = false;
            delete = false;
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_red_24dp));
        } else if (!tvHelper.getOne(tvTitle)){
            act = true;
            insert = true;
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_red_24dp));
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabClick();
            }
        });

        data();

    }

    private void data() {
        progressBar.setVisibility(View.GONE);
        Item item = getIntent().getParcelableExtra(EXTRA_TV);
        title.setText(item.getTitle());
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w185" + item.getPhoto())
                .resize(200,200)
                .into(photo);
        description.setText(item.getDescription());
    }

    private void fabClick() {
        Item item = getIntent().getParcelableExtra(EXTRA_TV);
        Log.d("test", "fabClick: "+delete+act+insert);
        if (insert && act){
            item.setTitle(item.getTitle());
            item.setDescription(item.getDescription());
            item.setPhoto(item.getPhoto());
            long result = tvHelper.insertTv(item);
            if (result > 0){
                Toast.makeText(TvActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
                fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_red_24dp));
            } else {
                Toast.makeText(TvActivity.this, R.string.failed, Toast.LENGTH_SHORT).show();
            }
        } else if (!delete && !act){
            long result = tvHelper.deleteTv(item.getTitle());
            if(result > 0 ){
                Toast.makeText(TvActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TvActivity.this, MainActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(TvActivity.this, R.string.failed, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
