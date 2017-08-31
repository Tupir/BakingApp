package com.example.petergabor.bakingapp.recipe_detail;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.petergabor.bakingapp.R;
import com.example.petergabor.bakingapp.recipe_descripton.RecipeDescriptionAdapter;
import com.example.petergabor.bakingapp.utils.Recept;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import java.util.ArrayList;

/**
 * Created by PepovPC on 9/1/2017.
 */

public class DetailStepFragment extends Fragment {

    private Recept recept;
    private RecyclerView recycler;
    private RecipeDescriptionAdapter mAdapter;
    ArrayList<String> shortDescriptionArray;
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    private boolean destroyVideo = true;
    String videoUrl;
    private int position;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the Android-Me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_detail_step, container, false);

//        // nastavenie recyclerview
//        recycler = rootView.findViewById(R.id.recepts);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        recycler.setLayoutManager(layoutManager);
//        recycler.setHasFixedSize(true);
//        //recycler.setItemAnimator(new DefaultItemAnimator());
//
//        // vyplnit recyclerview datami
//        mAdapter = new RecipeDescriptionAdapter(this.getClass().getSimpleName(), this, recept);
//        recycler.setAdapter(mAdapter);


        // Initialize the player view.
        mPlayerView = rootView.findViewById(R.id.playerView);
        videoUrl = recept.getVideoUrl().get(position);

        return rootView;
    }

    public void setReceptData(Recept recept) {
        this.recept = recept;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(mPlayerView != null){
            ExoPlayerVideoHandler.getInstance()
                    .prepareExoPlayerForUri(getContext(),
                            Uri.parse(videoUrl), mPlayerView);
            ExoPlayerVideoHandler.getInstance().goToForeground();
        }

        if(videoUrl == null || videoUrl.isEmpty()){
            mPlayerView.setDefaultArtwork(BitmapFactory.decodeResource
                    (getResources(), R.drawable.no_video));
            mPlayerView.hideController();
            mPlayerView.setUseController(false);
        }

    }


    @Override
    public void onPause(){
        super.onPause();
        ExoPlayerVideoHandler.getInstance().goToBackground();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        ExoPlayerVideoHandler.getInstance().releaseVideoPlayer();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(videoUrl == null || videoUrl.isEmpty()){
            return;
        }

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Intent intent = new Intent(getContext(), FullScreenVideoActivity.class);
            intent.putExtra("videoUrl", videoUrl);
            this.startActivity(intent);
        }
    }


}
