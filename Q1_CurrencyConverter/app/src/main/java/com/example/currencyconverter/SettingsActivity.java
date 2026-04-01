package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    Button lightBtn, darkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        lightBtn = findViewById(R.id.lightBtn);
        darkBtn = findViewById(R.id.darkBtn);

        lightBtn.setOnClickListener(v ->
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO));

        darkBtn.setOnClickListener(v ->
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES));
    }
}