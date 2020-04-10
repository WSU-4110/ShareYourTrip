package com.example.shareyourtrip;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.app.AlertDialog;

import java.util.List;


public class ProfilePostAdapter extends PostAdapter {

    //Copy Constructor
    public ProfilePostAdapter(List<Post> postList){
        super(postList);
    }

    //This ViewHolder has a delete button
    public class MyProfileViewHolder extends PostAdapter.MyViewHolder {
        public Button deleteButton;

        //Constructor of the ViewHolder, initializes TextViews
        public MyProfileViewHolder(View view) {
            super(view);
            deleteButton = (Button) view.findViewById(R.id.deleteButton);
        }
    }

    //Returns a ViewHolder given the context its parent is in
    @Override
    public MyProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_post_list_row, parent, false);

        return new MyProfileViewHolder(itemView);
    }

    //Once the ViewHolder is set in place, we retrieve the text from the UserPost
    //and put it in the TextViews

    public void onBindViewHolder(final MyProfileViewHolder holder, int position) {


    }

}
