package com.example.semester2finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FriendListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class FriendListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView friendListView;
    private TextView friendName;
    private TextView friendRecipe;
    private FriendAdapter friendAdapter;

    public FriendListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Friends.
     */
    // TODO: Rename and change types and number of parameters
    public static FriendListFragment newInstance(String param1, String param2) {
        FriendListFragment fragment = new FriendListFragment();
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
        View rootview =  inflater.inflate(R.layout.fragment_friends, container, false);
        wireWidgets(rootview);
        setListeners();

        Backendless.Data.of(BackendlessUser.class).find(new AsyncCallback<List<BackendlessUser>>() {
            @Override
            public void handleResponse(List<BackendlessUser> response) {

            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
        return inflater.inflate(R.layout.fragment_friends, container, false);
    }

    private void wireWidgets(View rootview) {
        friendListView=rootview.findViewById(R.id.friends_listView_friendList);
    }

    private void setListeners() {

    }

    private class FriendAdapter extends ArrayAdapter {
        private List<Recipe> friendList;
        private int position;

        public FriendAdapter(List<Recipe> friendsList) {
            super(FriendListFragment.this.getContext(), -1, friendsList);
            this.friendList = friendsList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            this.position = position;
            LayoutInflater inflater = getLayoutInflater();
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_friend, parent, false);
            }

            friendName = convertView.findViewById(R.id.textView_friendItem_friendName);
            friendRecipe = convertView.findViewById(R.id.textView_friendItem_friendRecipe);


            friendName.setText(friendList.get(position).getRecipeName());
            friendRecipe.setText(friendList.get(position).getRecipeName());

            return convertView;
        }
    }
}
