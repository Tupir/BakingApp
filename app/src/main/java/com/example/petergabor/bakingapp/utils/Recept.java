package com.example.petergabor.bakingapp.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class Recept implements Parcelable {


    private String title;
    private ArrayList<Float> quantity;
    private ArrayList<String>  measure;
    private ArrayList<String> ingredient;
    private ArrayList<String> shortDesc;
    private ArrayList<String> description;
    private ArrayList<String> videoUrl;


    public Recept() {
        super();
    }

    public Recept(ArrayList<Float> quantity, String title, ArrayList<String>  measure,
                  ArrayList<String> ingredient, ArrayList<String> shortDesc, ArrayList<String> description,
                  ArrayList<String> videoUrl) {
        this.quantity = quantity;
        this.title = title;
        this.measure = measure;
        this.ingredient = ingredient;
        this.shortDesc = shortDesc;
        this.description = description;
        this.videoUrl = videoUrl;
    }

    public ArrayList<Float> getQuantity() {
        return quantity;
    }

    public void setQuantity(ArrayList<Float> quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMeasure(ArrayList<String>  measure) {
        this.measure = measure;
    }

    public ArrayList<String>  getMeasure() {return measure;}

    public void setIngredient(ArrayList<String> ingredient) {
        this.ingredient = ingredient;
    }

    public ArrayList<String> getIngreadient() {
        return ingredient;
    }

    public void setShortDesc(ArrayList<String> shortDesc) {
        this.shortDesc = shortDesc;
    }

    public ArrayList<String> getShortDesc() {
        return shortDesc;
    }

    public void setDescription(ArrayList<String> description) {
        this.description = description;
    }

    public ArrayList<String> getDescription() {
        return description;
    }

    public void setVideoUrl(ArrayList<String> videoUrl) {
        this.videoUrl = videoUrl;
    }

    public ArrayList<String> getVideoUrl() {
        return videoUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeList(quantity);
        dest.writeList(measure);
        dest.writeList(ingredient);
        dest.writeList(shortDesc);
        dest.writeList(description);
        dest.writeList(videoUrl);
    }


    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public Recept createFromParcel(Parcel in) {
            return new Recept(in);
        }

        public Recept[] newArray(int size) {
            return new Recept[size];
        }
    };

    public Recept(Parcel in) {
        title = in.readString();
        quantity = in.readArrayList(null);
        measure = in.readArrayList(null);
        ingredient = in.readArrayList(null);
        shortDesc = in.readArrayList(null);
        description = in.readArrayList(null);
        videoUrl = in.readArrayList(null);
    }


}
