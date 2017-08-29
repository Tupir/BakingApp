package com.example.petergabor.bakingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.view.View;

import com.example.petergabor.bakingapp.utils.NetworkUtils;
import com.example.petergabor.bakingapp.utils.Recept;
import com.example.petergabor.bakingapp.utils.ReceptJsonParser;

import java.net.URL;
import java.util.ArrayList;

import static com.example.petergabor.bakingapp.AllRecipesActivity.mLoadingIndicator;


// is ok to do it on Cursor Loader,but try to meanwhile save all other movie items into DB as SERVICE?
public class AllRecipesDataLoader implements LoaderManager.LoaderCallbacks<ArrayList<Recept>>{

    public AllRecipeAdapter mAdapter;
    private Context context;

    AllRecipesDataLoader(Context context, AllRecipeAdapter mAdapter){
        this.context = context;
        this.mAdapter = mAdapter;
    }



    @Override
    public Loader<ArrayList<Recept>> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<ArrayList<Recept>>(context) {

            /* This Movie array will hold and help cache our movie data */
            ArrayList<Recept> recepts = null;

            /**
             * Subclasses of AsyncTaskLoader must implement this to take care of loading their data.
             * The same as onPreExecute() v AsyncTask
             */
            @Override
            protected void onStartLoading() {
                if (recepts != null) {
                    deliverResult(recepts);
                } else {
                   mLoadingIndicator.setVisibility(View.VISIBLE);
                    forceLoad();
                }
            }

            /**
             * This is the method of the AsyncTaskLoader that will load and parse the JSON data
             *
             * @return Movie data as an array of Movies.
             *         null if an error occurs
             */

            @Override
            public ArrayList<Recept> loadInBackground() {


                URL receptRequestUrl = NetworkUtils.buildUrl();

                try {
                    String jsonWeatherResponse = NetworkUtils
                            .getResponseFromHttpUrl(receptRequestUrl);


                    System.out.println(jsonWeatherResponse);
                    return ReceptJsonParser.getReceptDataFromJson(jsonWeatherResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            /**
             * Sends the result of the load to the registered listener.
             *
             * @param data The result of the load
             */
            public void deliverResult(ArrayList<Recept> data) {
                recepts = data;
                super.deliverResult(recepts);
            }



        };
    }


    @Override
    public void onLoadFinished(Loader<ArrayList<Recept>> loader, ArrayList<Recept> data) {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        mAdapter.setReceptData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Recept>> loader) {

    }
}
