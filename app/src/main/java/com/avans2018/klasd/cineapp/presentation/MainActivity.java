package com.avans2018.klasd.cineapp.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.avans2018.klasd.cineapp.R;

// Login-scherm waar gebruiker begint bij opstarten App
public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity (login-screen)";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



}
