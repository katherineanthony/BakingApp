package com.example.semester2finalproject;

public class Recipe {
    // make it so that it can hold the variables
    // we know what to do
    private String ingredients;
    private String name;
    private String recipe;

    public Recipe() {
    }

    public Recipe(String ingredients, String name, String recipe) {
        this.ingredients = ingredients;
        this.name = name;
        this.recipe = recipe;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }
}
