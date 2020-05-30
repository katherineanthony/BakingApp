package com.example.semester2finalproject;

import android.os.Parcel;
import android.os.Parcelable;

public class Recipe implements Parcelable{
    private String recipeName;
    private String ingredients;
    private String instructions;
    //backendless specific
    private String objectId;
    private String ownerId;

    public Recipe(){}

    public String toString(){
        return recipeName;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.recipeName);
        dest.writeString(this.ingredients);
        dest.writeString(this.instructions);
        dest.writeString(this.objectId);
        dest.writeString(this.ownerId);
    }

    protected Recipe(Parcel in) {

        this.recipeName = in.readString();
        this.ingredients = in.readString();
        this.instructions = in.readString();
        this.objectId = in.readString();
        this.ownerId = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
