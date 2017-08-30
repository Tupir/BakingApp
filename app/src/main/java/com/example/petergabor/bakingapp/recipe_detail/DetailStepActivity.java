package com.example.petergabor.bakingapp.recipe_detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.petergabor.bakingapp.R;
import com.example.petergabor.bakingapp.recipe_descripton.RecipeDescriptionAdapter;
import com.example.petergabor.bakingapp.utils.Recept;

import java.util.ArrayList;

public class DetailStepActivity extends AppCompatActivity {

    private Recept recept;
    private RecyclerView recycler;
    private RecipeDescriptionAdapter mAdapter;
    ArrayList<String> shortDescriptionArray;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_step);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("recept")) {
                recept = intentThatStartedThisActivity.getParcelableExtra("recept");
                setTitle(recept.getTitle());
                shortDescriptionArray = recept.getIngreadient();
                System.out.println("pocet: "+shortDescriptionArray.size());

            }
            if (intentThatStartedThisActivity.hasExtra("position")) {
                position = intentThatStartedThisActivity.getIntExtra("position", 0);
            }
        }

        System.out.println(position);



    }
}
