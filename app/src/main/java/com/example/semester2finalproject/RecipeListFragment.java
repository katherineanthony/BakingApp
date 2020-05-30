package com.example.semester2finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipeListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeListFragment extends Fragment {
    public static final String EXTRA_RECIPE = "Recipe";

    private ListView listView;
    private RecipeAdapter recipeAdapter;
    private TextView textViewName;
    private Comparator<Recipe> comparator;



    // TODO: Rename and change types and number of parameters
//    public static RecipeListFragment newInstance(String param1, String param2) {
//        RecipeListFragment fragment = new RecipeListFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
    public void loadDataFromBackendless(){
        Backendless.initApp(this, Backendless.getApplicationId(), Backendless.getApiKey());
        //search only for Friends with ownerIds that match the user's objectId
        String userId = Backendless.UserService.CurrentUser().getObjectId();
        String whereClause = "ownerId = " + "'" + userId + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);

        Backendless.Data.of(Recipe.class).find(queryBuilder, new AsyncCallback<List<Recipe>>(){
            @Override
            public void handleResponse(final List<Recipe> foundRecipes)
            {
                recipeAdapter = new RecipeAdapter(foundRecipes);
                listView.setAdapter(recipeAdapter);

                // we're sure that the list of friends exists at this point in the code
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent detailIntent = new Intent(RecipeListFragment.this.getContext(), RecipeDetailActivity.class);
                        detailIntent.putExtra(EXTRA_RECIPE, foundRecipes.get(i));
                        startActivity(detailIntent);
                    }
                });



            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                Toast.makeText(RecipeListFragment.this.getContext(), fault.getDetail(), Toast.LENGTH_SHORT).show();
                // an error has occurred, the error code can be retrieved with fault.getCode()
            }
        });
    }



    private class RecipeAdapter extends ArrayAdapter {
        private List<Recipe> friendsList;
        private int position;

        public RecipeAdapter(List<Recipe> friendsList) {
            super(RecipeListFragment.this.getContext(), -1, friendsList);
            this.friendsList = friendsList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            this.position = position;
            LayoutInflater inflater = getLayoutInflater();
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_friend, parent, false);
            }

            textViewName = convertView.findViewById(R.id.textview_recipe_name);

            textViewName.setText(friendsList.get(position).getRecipeName());

            return convertView;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_recipes, container, false);
        loadDataFromBackendless();

        return rootview;


    }

}
