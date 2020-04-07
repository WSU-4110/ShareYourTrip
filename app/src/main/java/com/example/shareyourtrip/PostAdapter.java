package com.example.shareyourtrip;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Toolbar;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//This code showcases the design pattern "Adapters"

/*
    This class is an adapter for the UserPost object. UserPostAdapter is the UI element
    which will display the contents of posts stored in postList. The UserPost class contains
    the information to be displayed, and the post_list_row.xml file lays out the actual structure
    of the adapter.
 */

//Todo: Make fav button do something
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

        //This will be a list of user posts to display to the users
        private List<Post> postList;

        //This is a ViewHolder which holds 5 TextViews which make up our post.
        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView location, category, title, description, user, date, likeCount, dislikecount;
            public ToggleButton favButton, likeButton, dislikeButton;

            //Constructor of the ViewHolder, initializes TextViews
            public MyViewHolder(View view) {
                super(view);
                location = (TextView) view.findViewById(R.id.location);
                category = (TextView) view.findViewById(R.id.category);
                title = (TextView) view.findViewById(R.id.title);
                description = (TextView) view.findViewById(R.id.description);
                user = (TextView) view.findViewById(R.id.user);
                date = (TextView) view.findViewById(R.id.date);
                favButton = (ToggleButton) view.findViewById(R.id.fav_button);
                likeButton = (ToggleButton) view.findViewById(R.id.like_button);
                likeCount = (TextView) view.findViewById(R.id.like_count);
                dislikeButton = (ToggleButton) view.findViewById(R.id.dislike_button);
                dislikecount = (TextView) view.findViewById(R.id.dislike_count);
            }
        }

        //Copy constructor
        public PostAdapter(List<Post> postList) {
            this.postList = postList;
        }

        //Returns a ViewHolder given the context its parent is in
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.post_list_row, parent, false);
            return new MyViewHolder(itemView);
        }

        //Once the ViewHolder is set in place, we retrieve the text from the UserPost
        //and put it in the TextViews
        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {

            //Setting views with values from postList
            Post post = postList.get(position);
            holder.location.setText(post.getCity() + ", " + post.getState());
            holder.category.setText(post.getCategory());
            holder.title.setText(post.getTitle());
            holder.description.setText(post.getDescription());
            holder.user.setText(post.getUser());
            holder.date.setText(post.getDate());
            //todo holder.likeCount.setText();
            //todo holder.dislikeCount.setText();

            //Favorite Button Logic
            holder.favButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //If favorite is toggled on...
                    if (isChecked) {
                        holder.favButton.setBackgroundResource(R.drawable.ic_favorite);
                        //todo: Put favorite database stuff here
                    }
                    //If favorite is toggled off
                    else {
                        holder.favButton.setBackgroundResource(R.drawable.ic_unfavorited);
                    }
                }
            });

            //like/dislike logic
            holder.likeButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //If like button toggled on
                    if (isChecked){
                        holder.likeButton.setBackgroundResource(R.drawable.ic_like_enabled);
                        //..and dislike is toggled on
                        if (holder.dislikeButton.isChecked()) {
                            holder.dislikeButton.setChecked(false);//toggle off dislike.
                        }
                        //todo: Put like database stuff here
                    }

                    //If like button is toggled off
                    else{
                        holder.likeButton.setBackgroundResource(R.drawable.ic_like);
                    }
                }
            });

            holder.dislikeButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //If dislike toggled on
                    if (isChecked){
                        holder.dislikeButton.setBackgroundResource(R.drawable.ic_dislike_enabled);
                        //...and if like is toggled on
                        if (holder.likeButton.isChecked()) {
                            holder.likeButton.setChecked(false);//toggle off like
                        }
                        //todo: Put dislike database stuff here
                    }
                    //If dislike is toggled off..
                    else{
                        holder.dislikeButton.setBackgroundResource(R.drawable.ic_dislike);
                    }
                }
            });
        }

        //Returns size of list of posts
        @Override
        public int getItemCount() {
            return postList.size();
        }
    }
