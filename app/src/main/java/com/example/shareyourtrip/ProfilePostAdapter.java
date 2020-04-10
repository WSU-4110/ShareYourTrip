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

    //Returns a ViewHolder given the context its parent is in
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_post_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    //Once the ViewHolder is set in place, we retrieve the text from the UserPost
    //and put it in the TextViews
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        super.onBindViewHolder(holder, position);

        //Delete button logic
        holder.deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
                alertbox.setMessage("Are you sure you want to delete this post?");
                alertbox.setTitle("Warning");
                alertbox.setIcon(R.drawable.ic_delete);

                //If the user says "Yes" to deleting a post.
                alertbox.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0,
                                                int arg1) {
                                //todo Put delete logic here
                            }
                        });

                //If the user says "No" to deleting a post
                alertbox.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0,
                                                int arg1) {
                                //Do nothing
                            }
                        });

                alertbox.show();
            }
        });

    }

}
