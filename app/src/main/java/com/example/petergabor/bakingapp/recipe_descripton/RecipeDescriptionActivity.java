package com.example.petergabor.bakingapp.recipe_descripton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.example.petergabor.bakingapp.AllRecipesActivity;
import com.example.petergabor.bakingapp.R;
import com.example.petergabor.bakingapp.widget.WidgetService;
import com.example.petergabor.bakingapp.recipe_detail.DetailStepActivity;
import com.example.petergabor.bakingapp.recipe_detail.DetailStepFragment;
import com.example.petergabor.bakingapp.recipe_ingredients.IngredientListActivity;
import com.example.petergabor.bakingapp.recipe_ingredients.IngredientListFragment;
import com.example.petergabor.bakingapp.utils.Recept;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class RecipeDescriptionActivity extends AppCompatActivity implements RecipeDescriptionFragment.OnImageClickListener {

    private static Recept recept;
    private RecyclerView recycler;
    private RecipeDescriptionAdapter mAdapter;
    ArrayList<String> shortDescriptionArray;
    private boolean isTablet;
//    @Bind(R.id.overview) TextView textOverview;
//    @Bind(R.id.vote) TextView textVote;
//    @Bind(R.id.release) TextView textRelease;
//    @Bind(R.id.imageView1) ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_screen_frame_layout);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this); // before setText

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("recept")) {
                recept = intentThatStartedThisActivity.getParcelableExtra("recept");
                //shortDescriptionArray = recept.getIngreadient();
            }
        }


        if(findViewById(R.id.tablet_layout) == null) {

            RecipeDescriptionFragment recipeDescriptionFragment = new RecipeDescriptionFragment();
            recipeDescriptionFragment.setReceptData(recept);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.frame_layout, recipeDescriptionFragment)
                    .commit();
        }else{
            isTablet = true;

            RecipeDescriptionFragment recipeDescriptionFragment = new RecipeDescriptionFragment();
            recipeDescriptionFragment.setReceptData(recept);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.left_side, recipeDescriptionFragment, "MY_FRAGMENT")
                    .commit();


            DetailStepFragment detailStepFragment = new DetailStepFragment();
            detailStepFragment.setReceptData(recept);
            detailStepFragment.setPosition(0);
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.right_side, detailStepFragment)
                    .commit();


        }
        // for widget
        WidgetService.startActionUpdateIngredientWidgets(this);

    }


    public void onIngredientsClick(View view){
        if(!isTablet) {
            Intent intentToStartDetailActivity = new Intent(this, IngredientListActivity.class);
            intentToStartDetailActivity.putExtra("recept", recept);
            startActivity(intentToStartDetailActivity);
        }else{
            IngredientListFragment ingredientListFragment = new IngredientListFragment();
            ingredientListFragment.setReceptData(recept);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.left_side, ingredientListFragment)
                    .commit();
        }
    }


    @Override
    public void onImageSelected(int position) {
        if(!isTablet) {
            Intent intentToStartDetailActivity = new Intent(this, DetailStepActivity.class);
            intentToStartDetailActivity.putExtra("recept", recept);
            intentToStartDetailActivity.putExtra("position", position);
            startActivity(intentToStartDetailActivity);
        }else{
            DetailStepFragment detailStepFragment = new DetailStepFragment();
            detailStepFragment.setReceptData(recept);
            detailStepFragment.setPosition(position);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.right_side, detailStepFragment)
                    .commit();
        }
    }

    /**
     * If mobile back to main
     * If tablet and on ingredients scree, back button backs to description list first
     * and after back button again return to main screen
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                if(!isTablet) {
                    startActivity(new Intent(this, AllRecipesActivity.class));
                    return true;
                }else{
                    System.out.println();
                    RecipeDescriptionFragment recipeDescriptionFragment = (RecipeDescriptionFragment)getSupportFragmentManager()
                            .findFragmentByTag("MY_FRAGMENT");
                    if(recipeDescriptionFragment != null && recipeDescriptionFragment.isVisible()){
                        startActivity(new Intent(this, AllRecipesActivity.class));
                        return true;
                    }else{
                        recipeDescriptionFragment = new RecipeDescriptionFragment();
                        recipeDescriptionFragment.setReceptData(recept);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.left_side, recipeDescriptionFragment, "MY_FRAGMENT")
                                .commit();
                    }
                }
        }
        return super.onOptionsItemSelected(item);
    }


    public static Recept getReceptData() {
        return recept;
    }


}
