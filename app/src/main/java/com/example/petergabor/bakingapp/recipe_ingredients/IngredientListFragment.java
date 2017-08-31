package com.example.petergabor.bakingapp.recipe_ingredients;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.petergabor.bakingapp.R;
import com.example.petergabor.bakingapp.recipe_descripton.RecipeDescriptionAdapter;
import com.example.petergabor.bakingapp.utils.Recept;

import java.util.ArrayList;

/**
 * Created by PepovPC on 8/30/2017.
 */

public class IngredientListFragment extends Fragment {
    private Recept recept;
    private RecyclerView recycler;
    private RecipeDescriptionAdapter mAdapter;
    ArrayList<String> shortDescriptionArray;


    public IngredientListFragment(){    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the Android-Me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_ingredients_list, container, false);

        recycler = rootView.findViewById(R.id.recepts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);

        mAdapter = new RecipeDescriptionAdapter(this.getClass().getSimpleName(), null, recept);
        recycler.setAdapter(mAdapter);

        return rootView;
    }

    public void setReceptData(Recept recept) {
        this.recept = recept;
    }
}
