package com.example.semester2finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateRecipe#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateRecipe extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText directions;
    private EditText ingredients;
    private EditText recipeName;
    private Button saveButton;
    private Recipe recipe;

    public CreateRecipe() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateRecipe.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateRecipe newInstance(String param1, String param2) {
        CreateRecipe fragment = new CreateRecipe();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_create_recipe, container, false);
        wireWidgets(rootview);
        setListeners();
        return inflater.inflate(R.layout.fragment_create_recipe, container, false);
    }

    private void setListeners() {
        recipe.setDirections(directions.getText().toString());
        recipe.setRecipeName(recipeName.getText().toString());
        recipe.setIngredients(ingredients.getText().toString());
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Backendless.Data.of(Recipe.class).save(recipe, new AsyncCallback<Recipe>() {
                    @Override
                    public void handleResponse(Recipe response) {
                        Toast.makeText(CreateRecipe.this.getContext(), "it worked", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(CreateRecipe.this.getContext(), "it did not work", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

    private void wireWidgets(View rootview) {

        ingredients=rootview.findViewById(R.id.editText_createRecipe_ingredients);
        recipeName=rootview.findViewById(R.id.editText_createRecipe_recipeName);
        directions=rootview.findViewById(R.id.editText_createRecipe_directions);
        saveButton=rootview.findViewById(R.id.button_createRecipe_save);

    }


}
