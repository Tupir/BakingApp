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
        void onClick(Recept recept);
    }

    public RecipeDescriptionAdapter(String activity, ForecastAdapterOnClickHandler clickHandler, ArrayList<String> data) {
        this.activity = activity;
        System.out.println(activity.getClass().getSimpleName());
        this.mClickHandler = clickHandler;   // for Clicking
        this.data = data;
        System.out.println(activity.getClass().getSimpleName().toString());

    }



    public class ForecastAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView text;
        public final TextView number;

        public ForecastAdapterViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.step_desc);
            number = view.findViewById(R.id.number);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            //Recept recept = data.get(adapterPosition);    // CIZE POTREBUJEM TU CELY RECEPT
            mClickHandler.onClick(recept);
        }
    }


    public class ForecastAdapterViewHolder2 extends RecyclerView.ViewHolder{

        public final TextView text;

        public ForecastAdapterViewHolder2(View view) {
            super(view);
            text = view.findViewById(R.id.step_desc);
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

        if (viewType == VIEW_INGREDIENTS) {
            View v = layoutInflater.inflate(R.layout.description_recyclerview_item, viewGroup, false);

            return new ForecastAdapterViewHolder(v);
        } else {
            //View v = layoutInflater.inflate(R.layout.review_item, viewGroup, false);

            return null;
        }

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        //String title = receptData.get(position).getShortDesc();
        //forecastAdapterViewHolder.text.setText(recept.getShortDesc());

        //forecastAdapterViewHolder.text.setText(Integer.toString(++position)+" - "+data.get(--position));

        int viewType = getItemViewType(position);
        switch(viewType){
            case VIEW_INGREDIENTS:
                ForecastAdapterViewHolder ForecastAdapterViewHolder = (RecipeDescriptionAdapter.ForecastAdapterViewHolder) holder;
                //trailerViewHolder.trailer.setText(trailers.get(position));
                ForecastAdapterViewHolder.text.setText(data.get(position));
                ForecastAdapterViewHolder.number.setText(Integer.toString(++position));
                break;
//            case VIEW_REVIEWS:
//                reviewViewHolder reviewViewHolder = (DetailAdapter.reviewViewHolder) holder;
//                reviewViewHolder.name.setText(reviews.get(position).get(0)+": ");
//                reviewViewHolder.text.setText(reviews.get(position).get(1));
//                break;
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
        int i = 0;
        if(("RecipeDescriptionActivity").equalsIgnoreCase("RecipeDescriptionActivity"))
            i = VIEW_INGREDIENTS;
        else
            i = VIEW_INGREDIENTS;

        return i;
    }


}