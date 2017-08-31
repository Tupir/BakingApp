package com.example.petergabor.bakingapp.recipe_descripton;

/**
 * Created by PepovPC on 7/16/2017.
 * Adapter, ktory zobrazi jednotlive views
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.petergabor.bakingapp.R;
import com.example.petergabor.bakingapp.utils.Recept;

import java.util.ArrayList;


public class RecipeDescriptionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = RecipeDescriptionAdapter.class.getSimpleName();
    private ArrayList<String> data;
    private Context context;
    private final ForecastAdapterOnClickHandler mClickHandler;
    private Recept recept;
    private String activity;
    private static final int VIEW_STEPS = 0;
    private static final int VIEW_INGREDIENTS = 1;


    /**
     * Rozhranie, ktore urcuje, co sa vykona po kliknuti na konkretny view
     */
    public interface ForecastAdapterOnClickHandler {
        void onClick(int stepPosition);
    }

    public RecipeDescriptionAdapter(String activity, ForecastAdapterOnClickHandler clickHandler, Recept recept) {
        this.activity = activity;
        this.mClickHandler = clickHandler;   // for Clicking
        this.recept = recept;
        data = recept.getShortDesc();
    }



    public class ForecastAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView text;
        private final TextView number;

        public ForecastAdapterViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.step_desc);
            number = view.findViewById(R.id.number);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickHandler.onClick(getAdapterPosition());
        }
    }


    public class ForecastAdapterViewHolder2 extends RecyclerView.ViewHolder{

        public final TextView ingredient;
        private final TextView numberOf;

        public ForecastAdapterViewHolder2(View view) {
            super(view);
            ingredient = view.findViewById(R.id.ingredient);
            numberOf = view.findViewById(R.id.numberOf);
        }
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     * @return A new ForecastAdapterViewHolder that holds the View for each list item
     *
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {    //description_recyclerview_item;

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        if (viewType == VIEW_STEPS) {
            View v = layoutInflater.inflate(R.layout.description_recyclerview_item, viewGroup, false);

            return new ForecastAdapterViewHolder(v);
        } else {
            View v = layoutInflater.inflate(R.layout.ingredient_recyclerview_item, viewGroup, false);

            return new ForecastAdapterViewHolder2(v);
        }

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int viewType = getItemViewType(position);
        switch(viewType){
            case VIEW_STEPS:
                ForecastAdapterViewHolder ForecastAdapterViewHolder = (RecipeDescriptionAdapter.ForecastAdapterViewHolder) holder;
                ForecastAdapterViewHolder.text.setText(data.get(position));
                ForecastAdapterViewHolder.number.setText(Integer.toString(++position));
                break;
            case VIEW_INGREDIENTS:
                ForecastAdapterViewHolder2 ForecastAdapterViewHolder2 = (RecipeDescriptionAdapter.ForecastAdapterViewHolder2) holder;
                ForecastAdapterViewHolder2.ingredient.setText(data.get(position));
                float quant = recept.getQuantity().get(position);
                String prefix = "";
                if(quant > 1){
                    prefix = "'S";
                }
                if(quant == (int)quant)
                    ForecastAdapterViewHolder2.numberOf.setText((int)quant+" "+recept.getMeasure().get(position)+prefix+" of:");
                else
                    ForecastAdapterViewHolder2.numberOf.setText(quant+" "+recept.getMeasure().get(position)+prefix+" of:");
                break;
            default:
                System.out.println("Error in onBindViewHolder method");
        }


    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our forecast
     */
    @Override
    public int getItemCount() {
        if (null == data) return 0;
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        int view;
        if((activity).equalsIgnoreCase("RecipeDescriptionFragment")) {
            view = VIEW_STEPS;
            data = recept.getShortDesc();
        }else{
            view = VIEW_INGREDIENTS;
            data = recept.getIngreadient();
        }
        return view;
    }


}