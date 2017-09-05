package com.example.petergabor.bakingapp.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.example.petergabor.bakingapp.R;
import com.example.petergabor.bakingapp.recipe_descripton.RecipeDescriptionActivity;
import com.example.petergabor.bakingapp.utils.Recept;

import java.util.ArrayList;


/**
 * Created by PepovPC on 9/4/2017.
 */

public class WidgetService extends IntentService{


    public static final String SOME_ACTION = "com.example.android.mygarden.action.water_plants";
    public static final String SHOW_INGREDIENTS = "update_ingredient_widgets";

    public WidgetService() {
        super("WidgetService");
    }



    /**
     * Starts this service to perform UpdatePlantWidgets action with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionUpdateIngredientWidgets(Context context) {
        Intent intent = new Intent(context, WidgetService.class);
        intent.setAction(SHOW_INGREDIENTS);
        context.startService(intent);
    }

    /**
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (SOME_ACTION.equals(action)) {
                //something
            } else if (SHOW_INGREDIENTS.equals(action)) {
                handleActionUpdatePlantWidgets();
            }
        }
    }

    // update background
    // TODO: create widget not only for current recept, but let user to choose in widget which recept to choose
    // TODO: maybe create button in ingredients screen, where user can add current ingredients to widget?
    private void handleActionUpdatePlantWidgets() {
        ArrayList<String> ingredients = null;
        if(RecipeDescriptionActivity.getReceptData() != null){
            Recept str = RecipeDescriptionActivity.getReceptData();
            ingredients = str.getIngreadient();
        }

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, BakingWidgetProvider.class));
        //Trigger data update to handle the GridView widgets and force a data refresh
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_grid_view);
        //Now update all widgets
        BakingWidgetProvider.updatePlantWidgets(this, appWidgetManager, appWidgetIds, ingredients);
    }



}
