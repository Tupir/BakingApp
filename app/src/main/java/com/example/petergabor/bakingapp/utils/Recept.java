package com.example.petergabor.bakingapp.utils;

import android.os.Parcel;
import android.os.Parcelable;

import static android.R.attr.id;


public class Recept implements Parcelable {


    private String title;
    private int quantity;
    private String measure;
    private String ingredient;
    private String shortDesc;
    private String description;
    private String videoUrl;


    public Recept() {
        super();
    }

    public Recept(int quantity, String title, String measure, String ingredient, String shortDesc, String description, String videoUrl) {
        this.quantity = quantity;
        this.title = title;
        this.measure = measure;
        this.ingredient = ingredient;
        this.shortDesc = shortDesc;
        this.description = description;
        this.videoUrl = videoUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getMeasure() {return measure;}

    public void setIngreadient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getIngreadient() {
        return ingredient;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(quantity);
        dest.writeString(measure);
        dest.writeString(ingredient);
        dest.writeString(shortDesc);
        dest.writeString(description);
        dest.writeString(videoUrl);
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
        quantity = in.readInt();
        measure = in.readString();
        ingredient = in.readString();
        shortDesc = in.readString();
        description = in.readString();
        videoUrl = in.readString();
    }


}
