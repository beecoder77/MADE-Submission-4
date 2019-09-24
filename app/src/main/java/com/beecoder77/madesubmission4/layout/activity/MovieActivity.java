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
import com.beecoder77.madesubmission4.db.MovieHelper;
import com.beecoder77.madesubmission4.model.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

    TextView title, description;
    ImageView photo;
    FloatingActionButton fab;
    ProgressBar progressBar;
    Item item;
    MovieHelper movieHelper;
    Boolean act = true;
    Boolean insert = true;
    Boolean delete = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        photo = findViewById(R.id.img_movie);
        title = findViewById(R.id.movie_title);
        description = findViewById(R.id.movie_description);
        progressBar = findViewById(R.id.pb_detail_movie);
        fab = findViewById(R.id.fab_movie);

        Item item = getIntent().getParcelableExtra(EXTRA_MOVIE);
        movieHelper = MovieHelper.getINSTANCE(getApplicationContext());
        String movieTitle = item.getTitle();
        Log.d("test", "onCreate: "+movieTitle+movieHelper.getOne(movieTitle));
        if (movieHelper.getOne(movieTitle)){
            //delete
            act = false;
            delete = false;
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_red_24dp));
        } else if (!movieHelper.getOne(movieTitle)){
            //insert
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
        Item item = getIntent().getParcelableExtra(EXTRA_MOVIE);
        title.setText(item.getTitle());
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w185" + item.getPhoto())
                .resize(200,200)
                .into(photo);
        description.setText(item.getDescription());
    }

    private void fabClick() {
        Item item = getIntent().getParcelableExtra(EXTRA_MOVIE);
        if (insert && act){
            item.setTitle(item.getTitle());
            item.setDescription(item.getDescription());
            item.setPhoto(item.getPhoto());
            long result = movieHelper.insertMovie(item);
            if (result > 0){
                Toast.makeText(MovieActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
                fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_red_24dp));
            } else {
                Toast.makeText(MovieActivity.this, R.string.failed, Toast.LENGTH_SHORT).show();
            }
        } else if (!delete && !act){
            long result = movieHelper.deleteMovie(item.getTitle());
            if(result > 0 ){
                Toast.makeText(MovieActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MovieActivity.this, MainActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(MovieActivity.this, R.string.failed, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
