package com.example.petergabor.bakingapp.recipe_ingredients;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.petergabor.bakingapp.R;
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
        setContentView(R.layout.full_screen_frame_layout);
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


        IngredientListFragment ingredientListFragment = new IngredientListFragment();
        ingredientListFragment.setReceptData(recept);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.frame_layout, ingredientListFragment)
                .commit();


    }
}
