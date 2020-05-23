package com.example.semester2finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.semester2finalproject.ui.recipedetail.RecipeDetailFragment;

public class RecipeDetailActivity extends AppCompatActivity {

    private ImageView recipeImage;
    private EditText recipeName;
    private EditText recipeIngredients;
    private EditText recipeSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, RecipeDetailFragment.newInstance())
                    .commitNow();
        }

        wireWidgets();
        setListeners();


    }

    private void setListeners() {
    }

    private void wireWidgets() {
        recipeImage=findViewById(R.id.imageView_recipeDetail_recipeImage);
        recipeIngredients=findViewById(R.id.editText_recipeDetail_ingredients);
        recipeName=findViewById(R.id.editText_recipeDetail_name);
        recipeSteps=findViewById(R.id.editText_recipeDetail_recipe);
    }
}
