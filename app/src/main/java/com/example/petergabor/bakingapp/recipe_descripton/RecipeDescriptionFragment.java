package com.example.petergabor.bakingapp.recipe_descripton;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.petergabor.bakingapp.R;
import com.example.petergabor.bakingapp.utils.Recept;

import java.util.ArrayList;
import java.util.Arrays;


public class RecipeDescriptionFragment extends Fragment {

    private Recept recept;
    private RecyclerView recycler;
    private RecipeDescriptionAdapter mAdapter;
    ArrayList<String> shortDescriptionArray;
    private RecipeDescriptionActivity recipeDescriptionActivity = new RecipeDescriptionActivity();

    public RecipeDescriptionFragment(){

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the Android-Me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_recept_description, container, false);



        System.out.println(Arrays.toString(shortDescriptionArray.toArray()));

        // nastavenie recyclerview
        recycler = rootView.findViewById(R.id.recepts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);
        //recycler.setItemAnimator(new DefaultItemAnimator());

        // vyplnit recyclerview datami
        mAdapter = new RecipeDescriptionAdapter(this.getClass().getSimpleName(), null, shortDescriptionArray);
        recycler.setAdapter(mAdapter);




        return rootView;
    }

    public void setDataPekne(ArrayList<String> data) {
        shortDescriptionArray = data;
    }


}
