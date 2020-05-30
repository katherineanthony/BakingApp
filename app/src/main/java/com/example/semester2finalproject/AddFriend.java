package com.example.semester2finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.List;


public class AddFriend extends Fragment {


    // TODO: Rename and change types of parameters
    private EditText username_editText;
    private Button addFriend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_friend, container, false);

        wireWidgets(rootView);
        setListeners();
        
        return rootView;
    }

    private void setListeners() {
        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String whereClasue = "username = " + "'" + username_editText.getText().toString() + "'";
                
                Backendless.Persistence.of(BackendlessUser.class).addRelation(Backendless.UserService.CurrentUser(),
                        "users:Users:n", whereClasue, new AsyncCallback<Integer>() {
                            @Override
                            public void handleResponse(Integer response) {
                                Toast.makeText(AddFriend.this.getContext(), "Friend Added", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Toast.makeText(AddFriend.this.getContext(), fault.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });
    }

    private void wireWidgets(View rootView) {
        username_editText = rootView.findViewById(R.id.editText_addFriend_username);
        addFriend = rootView.findViewById(R.id.button_addFriend);
    }
}
