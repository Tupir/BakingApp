package com.example.petergabor.bakingapp.recipe_detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.petergabor.bakingapp.R;
import com.example.petergabor.bakingapp.recipe_descripton.RecipeDescriptionAdapter;
import com.example.petergabor.bakingapp.utils.Recept;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import java.util.ArrayList;

public class DetailStepActivity extends AppCompatActivity {

    private Recept recept;
    private RecyclerView recycler;
    private RecipeDescriptionAdapter mAdapter;
    ArrayList<String> shortDescriptionArray;
    int position;
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    private boolean destroyVideo = true;
    String videoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_screen_frame_layout);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("recept")) {
                recept = intentThatStartedThisActivity.getParcelableExtra("recept");
                shortDescriptionArray = recept.getIngreadient();
                System.out.println("pocet: "+shortDescriptionArray.size());

            }
            if (intentThatStartedThisActivity.hasExtra("position")) {
                position = intentThatStartedThisActivity.getIntExtra("position", 0);
                //setTitle(recept.getShortDesc().get(position));
            }
        }

        DetailStepFragment detailStepFragment = new DetailStepFragment();
        detailStepFragment.setReceptData(recept);
        detailStepFragment.setPosition(position);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.frame_layout, detailStepFragment)
                .commit();


    }


    public void onNextClick(View view){
        if(position == recept.getShortDesc().size()-1)
            return;
        DetailStepFragment detailStepFragment = new DetailStepFragment();
        detailStepFragment.setReceptData(recept);
        detailStepFragment.setPosition(++position);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, detailStepFragment)
                .commit();
    }


    public void onPreviousClick(View view){
        if(position == 0)
            return;
        DetailStepFragment detailStepFragment = new DetailStepFragment();
        detailStepFragment.setReceptData(recept);
        detailStepFragment.setPosition(--position);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, detailStepFragment)
                .commit();

    }


}