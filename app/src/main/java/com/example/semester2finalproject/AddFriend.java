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
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.List;


public class AddFriend extends Fragment {


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddFriend() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_friend, container, false);
        String username = "";
        Backendless.Persistence.of(BackendlessUser.class).addRelation(Backendless.UserService.CurrentUser(),
                "friend", "username = " + username, new AsyncCallback<Integer>() {
                    @Override
                    public void handleResponse(Integer response) {

                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {

                    }
                });
        return rootView;
    }
}
