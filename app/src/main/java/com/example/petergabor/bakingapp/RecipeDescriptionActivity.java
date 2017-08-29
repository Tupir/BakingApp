package com.example.petergabor.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.petergabor.bakingapp.utils.Recept;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class RecipeDescriptionActivity extends AppCompatActivity implements RecipeDescriptionAdapter.ForecastAdapterOnClickHandler {

    private Recept recept;
    private RecyclerView recycler;
    private RecipeDescriptionAdapter mAdapter;
    ArrayList<String> shortDescriptionArray;
//    @Bind(R.id.overview) TextView textOverview;
//    @Bind(R.id.vote) TextView textVote;
//    @Bind(R.id.release) TextView textRelease;
//    @Bind(R.id.imageView1) ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recept_description);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this); // before setText

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("recept")) {
                recept = intentThatStartedThisActivity.getParcelableExtra("recept");
                setTitle(recept.getTitle());
                shortDescriptionArray = recept.getIngreadient();
                System.out.println("pocet: "+shortDescriptionArray.size());
            }
        }


        RecipeDescriptionFragment recipeDescriptionFragment = new RecipeDescriptionFragment();
        recipeDescriptionFragment.setDataPekne(shortDescriptionArray);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.head_container, recipeDescriptionFragment)
                .commit();


    }


    @Override
    public void onClick(Recept recept) {

    }


    public void onIngredientsClick(View view){
        Intent intentToStartDetailActivity = new Intent(this, IngredientListActivity.class);
        intentToStartDetailActivity.putExtra("recept", recept);
        startActivity(intentToStartDetailActivity);
    }


}
