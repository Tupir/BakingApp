package com.example.petergabor.bakingapp.recipe_descripton;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.petergabor.bakingapp.R;
import com.example.petergabor.bakingapp.utils.Recept;


public class RecipeDescriptionFragment extends Fragment implements RecipeDescriptionAdapter.ForecastAdapterOnClickHandler{

    private Recept recept;
    private RecyclerView recycler;
    private RecipeDescriptionAdapter mAdapter;

    // Define a new interface OnImageClickListener that triggers a callback in the host activity
    OnImageClickListener mCallback;

    public RecipeDescriptionFragment(){

    }

    @Override
    public void onClick(int stepPosition) {
        System.out.println(stepPosition);
        mCallback.onImageSelected(stepPosition);
    }


    // OnImageClickListener interface, calls a method in the host activity named onImageSelected
    public interface OnImageClickListener {
        void onImageSelected(int position);
    }

    // Override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            mCallback = (OnImageClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement onItemClicked");
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the Android-Me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_recept_description, container, false);
        getActivity().setTitle(recept.getTitle());

        // nastavenie recyclerview
        recycler = rootView.findViewById(R.id.recepts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);
        //recycler.setItemAnimator(new DefaultItemAnimator());

        // vyplnit recyclerview datami
        mAdapter = new RecipeDescriptionAdapter(this.getClass().getSimpleName(), this, recept);
        recycler.setAdapter(mAdapter);

        return rootView;
    }

    public void setReceptData(Recept recept) {
        this.recept = recept;
    }


}
