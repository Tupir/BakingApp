package com.example.petergabor.bakingapp.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


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
            float quantity;
            String measure = null;
            String ingredient = null;
            ArrayList<Float> quant = new ArrayList<>();
            ArrayList<String> mea = new ArrayList<>();
            ArrayList<String> ingr = new ArrayList<>();
            for(int j = 0; j < ingredientsArray.length(); j++){
                JSONObject ingredientObjet = ingredientsArray.getJSONObject(j);
                quantity = Float.parseFloat(ingredientObjet.getString(OWM_QUANTITY));
                quant.add(j, quantity);
                measure = ingredientObjet.getString(OWM_MEASURE);
                mea.add(j, measure);
                ingredient = ingredientObjet.getString(OWM_ONE_INGREDIENT);
                ingr.add(j, ingredient);
                System.out.println(ingredient);
            }
            JSONArray stepsArray = receptObject.getJSONArray(OWM_STEPS);
            String shortDesc = null;
            String descri = null;
            String videoUrl = null;
            ArrayList<String> shoDes = new ArrayList<>();
            ArrayList<String> desc = new ArrayList<>();
            ArrayList<String> videUrl = new ArrayList<>();
            System.out.println(stepsArray.length());
            for(int j = 0; j < stepsArray.length(); j++){
                JSONObject ingredientObject = stepsArray.getJSONObject(j);
                shortDesc = ingredientObject.getString(OWM_SHORT_DESCRI);
                shoDes.add(j, shortDesc);
                descri = ingredientObject.getString(OWM_DESCRIPTION);
                desc.add(j, descri);
                videoUrl = ingredientObject.getString(OWM_VIDEO_URL);
                videUrl.add(j, videoUrl);
                if(videoUrl.isEmpty())
                    System.out.println("PRAZDNE");
                System.out.println(videoUrl);
            }


            recept = new Recept();
            recept.setTitle(title);
            recept.setQuantity(quant);
            recept.setMeasure(mea);
            recept.setIngredient(ingr);
            recept.setShortDesc(shoDes);
            recept.setDescription(desc);
            recept.setVideoUrl(videUrl);


            recepts.add(recept);
        }
        return recepts;
    }





}
