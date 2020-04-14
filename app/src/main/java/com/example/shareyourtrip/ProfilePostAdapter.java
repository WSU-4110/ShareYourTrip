package com.example.shareyourtrip;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.app.AlertDialog;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ProfilePostAdapter extends PostAdapter {

    //Copy Constructor
    public ProfilePostAdapter(List<Post> postList, Context context){
        super(postList, context);
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
            public void onClick(final View v) {
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
                                int pos = holder.getAdapterPosition();
                                if (pos != RecyclerView.NO_POSITION) {
                                    Post post = postList.get(pos);
                                    String[] id = new String[]{post.getId()};
                                    //clickedPost = new Post(post);
                                    boolean isDeleted = postDAO.deletePost("id=?", id);
                                    if (!isDeleted) {
                                        //Toast.makeText(currentContext, "This post is already you favortie!", Toast.LENGTH_LONG).show();
                                        alertDisplay(currentContext,"Error!", "Unsuccessful deletion.");
                                    }else{
                                        notifyDataSetChanged();
                                        currentContext.startActivity(new Intent(currentContext, ProfileActivity.class));
                                    }
                                }
                            //    deletePost(id, v);
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
/*
    public void deletePost(String[] id, View v){
        String query = "id=?";
        if(!super.postDAO.deletePost(query, id)){
            final AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
            alertbox.setMessage("Could not delete from database.");
            alertbox.setTitle("Error");
//            alertbox.setIcon(R.drawable.ic_delete);

            //If the user says "Yes" to deleting a post.
            alertbox.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0,
                                            int arg1) {
                            //doing nothing
                        }
                    });

            alertbox.show();
        }else {
            super.notifyDataSetChanged();
        }
    }*/

}
