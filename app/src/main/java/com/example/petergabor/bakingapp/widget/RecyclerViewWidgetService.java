package com.example.petergabor.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.petergabor.bakingapp.R;
import com.example.petergabor.bakingapp.recipe_descripton.RecipeDescriptionActivity;

import java.util.ArrayList;


public class RecyclerViewWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecyclerViewRemoteViewsFactory(this.getApplicationContext());
    }
}

class RecyclerViewRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    Context mContext;
    ArrayList<String> data = new ArrayList<>();

    public RecyclerViewRemoteViewsFactory(Context applicationContext) {
        mContext = applicationContext;

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        data = RecipeDescriptionActivity.getReceptData().getIngreadient();
    }

    @Override
    public void onDestroy() {
        data.clear();
    }

    @Override
    public int getCount() {
        if (data == null) return 0;
        return data.size();
    }

    /**
     * This method acts like the onBindViewHolder method in an Adapter
     *
     * @param position The current position of the item in the GridView to be displayed
     * @return The RemoteViews object to display for the provided postion
     */
    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_grid_view_item);

        Intent fillInIntent = new Intent();
        views.setOnClickFillInIntent(R.id.widget_grid_view_item, fillInIntent);

        views.setTextViewText(R.id.widget_grid_view_item, String.valueOf(data.get(position)));



        return views;

    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1; // Treat all items in the GridView the same
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}

