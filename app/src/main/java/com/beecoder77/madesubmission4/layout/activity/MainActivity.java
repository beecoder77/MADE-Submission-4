package com.beecoder77.madesubmission4.layout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.beecoder77.madesubmission4.R;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.beecoder77.madesubmission4.pager.MainPager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainPager mainPager = new MainPager(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.main_view_pager);
        viewPager.setAdapter(mainPager);
        TabLayout tabs = findViewById(R.id.main_tabs);
        tabs.setupWithViewPager(viewPager);

        setSupportActionBar((androidx.appcompat.widget.Toolbar) findViewById(R.id.main_toolbar));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings){
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        } else if (item.getItemId() == R.id.favorite_menu){
            Intent mIntent = new Intent(MainActivity.this, FavoriteActivity.class);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }

}