package com.avans2018.klasd.cineapp.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.avans2018.klasd.cineapp.R;

// Activity voor detailscherm bij onItemClick in MovieListActivity
public class DetailActivity extends AppCompatActivity {
    private final static String TAG = "DetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }
}
