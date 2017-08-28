package com.example.petergabor.bakingapp;

import android.net.Network;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.petergabor.bakingapp.utils.NetworkUtils;

import java.io.IOException;

public class AllRecipesActivity extends AppCompatActivity {

    private static final int FORECAST_LOADER_ID = 0;

    public static ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_recipes);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);  // loader

        getSupportLoaderManager().initLoader(FORECAST_LOADER_ID, null, new AllRecipesLoader(this));

    }
}
