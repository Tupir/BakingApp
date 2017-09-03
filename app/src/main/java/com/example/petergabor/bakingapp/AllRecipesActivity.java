package com.example.petergabor.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.example.petergabor.bakingapp.IdlingResource.SimpleIdlingResource;
import com.example.petergabor.bakingapp.recipe_descripton.RecipeDescriptionActivity;
import com.example.petergabor.bakingapp.utils.Recept;

import java.util.ArrayList;

public class AllRecipesActivity extends AppCompatActivity implements AllRecipeAdapter.ForecastAdapterOnClickHandler,
                                                AllRecipesDataLoader.DelayerCallback{

    private static final int FORECAST_LOADER_ID = 0;

    public static ProgressBar mLoadingIndicator;
    private RecyclerView recycler;
    private AllRecipeAdapter mAdapter;

    @Nullable
    private SimpleIdlingResource mIdlingResource;

    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_recipes);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);  // loader

        // nastavenie recyclerview
        recycler = (RecyclerView) findViewById(R.id.all_recepts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);



//        // vyplnit recyclerview datami
//        mAdapter = new AllRecipeAdapter(getBaseContext(), this);
//        recycler.setAdapter(mAdapter);


//        getSupportLoaderManager().initLoader(FORECAST_LOADER_ID, null, new AllRecipesDataLoader(this, mAdapter));
        getIdlingResource();
    }

    @Override
    public void onClick(Recept recept) {
        Intent intentToStartDetailActivity = new Intent(this, RecipeDescriptionActivity.class);
        intentToStartDetailActivity.putExtra("recept", recept);
        startActivity(intentToStartDetailActivity);

    }


    /**
     * We call ImageDownloader.downloadImage from onStart or onResume instead of in onCreate
     * to ensure there is enougth time to register IdlingResource if the download is done
     * too early (i.e. in onCreate)
     */
    @Override
    protected void onStart() {
        super.onStart();

        mAdapter = new AllRecipeAdapter(getBaseContext(), this);
        getSupportLoaderManager().initLoader(FORECAST_LOADER_ID, null, new AllRecipesDataLoader(this, mAdapter, this, mIdlingResource));
    }




    @Override
    public void onDone(ArrayList<Recept> teas) {

        recycler.setAdapter(mAdapter);

    }
}
