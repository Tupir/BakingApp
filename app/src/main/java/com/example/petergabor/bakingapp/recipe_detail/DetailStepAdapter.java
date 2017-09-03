package com.example.petergabor.bakingapp.recipe_detail;

/**
 * Created by PepovPC on 7/16/2017.
 * Adapter, ktory zobrazi jednotlive views
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petergabor.bakingapp.R;
import com.example.petergabor.bakingapp.utils.Recept;

import java.util.ArrayList;


public class DetailStepAdapter extends RecyclerView.Adapter<DetailStepAdapter.ForecastAdapterViewHolder> {

    private static final String TAG = DetailStepAdapter.class.getSimpleName();
    private ArrayList<Recept> receptData;
    private Context context;
    private final ForecastAdapterOnClickHandler mClickHandler;

    /**
     * Rozhranie, ktore urcuje, co sa vykona po kliknuti na konkretny view
     */
    public interface ForecastAdapterOnClickHandler {
        void onClick(Recept recept);
    }

    public DetailStepAdapter(Context context, ForecastAdapterOnClickHandler clickHandler) {
        this.context = context;         // for Picasso
        mClickHandler = clickHandler;   // for Clicking
    }

    /**
     * Cache of the children views for a forecast list item.
     * Tato mala trieda obsahuje v sebe vsetko co ma obsahovat kazdy jednotlivy view
     * Vytvori a inicializuje ich
     * Tiez implementuje rozhranie, ktore umozni click (tak ako v main), ale tu sa nastavuju data (pozicia, movie)
     */
    public class ForecastAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView text;
        public final ImageView image;

        public ForecastAdapterViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.textView);
            image = view.findViewById(R.id.imageView2);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Recept recept = receptData.get(adapterPosition);
            mClickHandler.onClick(recept);
        }
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     * @return A new ForecastAdapterViewHolder that holds the View for each list item
     *
     */
    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.all_recipes_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new ForecastAdapterViewHolder(view);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the weather
     * details for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param forecastAdapterViewHolder The ViewHolder which should be updated to represent the
     *                                  contents of the item at the given position in the data set.
     * @param position                  The position of the item within the adapter's data set.
     *
     * Vykresluje/nastavuje/vizualizuje data
     *
     */
    @Override
    public void onBindViewHolder(ForecastAdapterViewHolder forecastAdapterViewHolder, int position) {

        String title = receptData.get(position).getTitle();
        forecastAdapterViewHolder.text.setText(title);
        setPic(title, forecastAdapterViewHolder);

    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our forecast
     */
    @Override
    public int getItemCount() {
        if (null == receptData) return 0;
        return receptData.size();
    }

    /**
     * This method is used to set the movies on a Adapter if we've already
     * created one. This is handy when we get new data from the web but don't want to create a
     * new ForecastAdapter to display it.
     *
     * @param data The new weather data to be displayed.
     */
    public void setReceptData(ArrayList<Recept> data) {
        System.out.println(data.size());
        receptData = data;
        notifyDataSetChanged();
    }



    public void setPic(String title, ForecastAdapterViewHolder forecastAdapterViewHolder){

        switch (title) {
            case "Nutella Pie": forecastAdapterViewHolder.image.setBackgroundResource(R.drawable.nutella_pie);
                break;
            case "Brownies": forecastAdapterViewHolder.image.setBackgroundResource(R.drawable.brownies);
                break;
            case "Yellow Cake": forecastAdapterViewHolder.image.setBackgroundResource(R.drawable.cake);
                break;
            case "Cheesecake": forecastAdapterViewHolder.image.setBackgroundResource(R.drawable.cheesecake);
                break;
            default:
                forecastAdapterViewHolder.image.setBackgroundResource(R.drawable.no_image);
                break;
        }
        forecastAdapterViewHolder.image.setScaleType(ImageView.ScaleType.FIT_XY);
    }


}