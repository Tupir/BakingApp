package com.example.petergabor.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.petergabor.bakingapp.recipe_descripton.RecipeDescriptionAdapter;
import com.example.petergabor.bakingapp.utils.Recept;

import java.util.ArrayList;

public class IngredientListActivity extends AppCompatActivity {
    private Recept recept;
    private RecyclerView recycler;
    private RecipeDescriptionAdapter mAdapter;
    ArrayList<String> shortDescriptionArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_list);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("recept")) {
                recept = intentThatStartedThisActivity.getParcelableExtra("recept");
                setTitle(recept.getTitle());
                shortDescriptionArray = recept.getIngreadient();
                System.out.println("pocet: "+shortDescriptionArray.size());
            }
        }


        // nastavenie recyclerview
        recycler = (RecyclerView) findViewById(R.id.recepts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);
        //recycler.setItemAnimator(new DefaultItemAnimator());

        // vyplnit recyclerview datami
        mAdapter = new RecipeDescriptionAdapter(this.getClass().getSimpleName(), null, shortDescriptionArray);
        recycler.setAdapter(mAdapter);



    }
}
