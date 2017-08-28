package com.example.petergabor.bakingapp.utils;

import android.graphics.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ReceptJsonParser {

    public static ArrayList<Recept> getReceptDataFromJson(String forecastJsonStr)
            throws JSONException {

        ArrayList<Recept> recepts = new ArrayList<>();

        final String OWM_RESULT = "name";

        final String OWM_INGREDIENTS = "ingredients";
        final String OWM_QUANTITY = "quantity";
        final String OWM_MEASURE = "measure";
        final String OWM_ONE_INGREDIENT = "ingredient";

        final String OWM_STEPS = "steps";
        final String OWM_SHORT_DESCRI = "shortDescription";
        final String OWM_DESCRIPTION = "description";
        final String OWM_VIDEO_URL = "videoURL";


        JSONArray receptArray = new JSONArray(forecastJsonStr);

        recepts.clear();
        Recept recept;

        for(int i = 0; i < receptArray.length(); i++) {
            JSONObject receptObject = receptArray.getJSONObject(i);
            String title = receptObject.getString(OWM_RESULT);
            System.out.println(title);
            JSONArray ingredientsArray = receptObject.getJSONArray( OWM_INGREDIENTS);
            String quantity = null;
            String measure = null;
            String ingredient = null;
            for(int j = 0; j < ingredientsArray.length(); j++){
                JSONObject ingredientObjet = ingredientsArray.getJSONObject(j);
                quantity = ingredientObjet.getString(OWM_QUANTITY);
                measure = ingredientObjet.getString(OWM_MEASURE);
                ingredient = ingredientObjet.getString(OWM_ONE_INGREDIENT);
                System.out.println(ingredient);
            }
            JSONArray stepsArray = receptObject.getJSONArray(OWM_STEPS);
            String shortDesc = null;
            String descri = null;
            String videoUrl = null;
            System.out.println(stepsArray.length());
            for(int j = 0; j < stepsArray.length(); j++){
                JSONObject ingredientObject = stepsArray.getJSONObject(j);
                shortDesc = ingredientObject.getString(OWM_SHORT_DESCRI);
                descri = ingredientObject.getString(OWM_DESCRIPTION);
                videoUrl = ingredientObject.getString(OWM_VIDEO_URL);
                if(videoUrl.isEmpty())
                    System.out.println("PRAZDNE");
                System.out.println(videoUrl);
            }


            recept = new Recept();
            recept.setTitle(title);
            recept.setQuantity(Integer.parseInt(quantity));
            recept.setMeasure(measure);
            recept.setIngreadient(ingredient);
            recept.setShortDesc(shortDesc);
            recept.setDescription(descri);
            recept.setVideoUrl(videoUrl);


            recepts.add(recept);
        }
        return recepts;
    }





}
