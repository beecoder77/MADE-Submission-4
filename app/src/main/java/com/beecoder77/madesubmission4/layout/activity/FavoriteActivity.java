package com.beecoder77.madesubmission4.layout.activity;

import android.content.Intent;
import android.os.Bundle;

import com.beecoder77.madesubmission4.R;
import com.beecoder77.madesubmission4.pager.FavoritePager;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class FavoriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        FavoritePager favoritePager = new FavoritePager(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.fav_view_pager);
        viewPager.setAdapter(favoritePager);
        TabLayout tabs = findViewById(R.id.fav_tabs);
        tabs.setupWithViewPager(viewPager);

        setSupportActionBar((androidx.appcompat.widget.Toolbar) findViewById(R.id.fav_toolbar));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fav_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings){
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        } else if (item.getItemId() == R.id.activity_menu){
            Intent mIntent = new Intent(FavoriteActivity.this, MainActivity.class);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}