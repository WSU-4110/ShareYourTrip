package com.example.shareyourtrip;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

public class UserPostAdapter extends RecyclerView.Adapter<UserPostAdapter.MyViewHolder> {

        //This will be a list of user posts to display to the users
        private List<Post> postList = new ArrayList<>();

        //This is a ViewHolder which holds 5 TextViews which make up our post.
        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView location, category, title, description;

            //Constructor of the ViewHolder, initializes TextViews
            public MyViewHolder(View view) {
                super(view);
                location = (TextView) view.findViewById(R.id.location);
                category = (TextView) view.findViewById(R.id.category);
                title = (TextView) view.findViewById(R.id.title);
                description = (TextView) view.findViewById(R.id.description);
            }
        }

        //Copy constructor
        public UserPostAdapter(List<Post> postList) {
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
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Post post = postList.get(position);
            holder.location.setText(post.getCity() + ", " + post.getState());
            holder.category.setText(post.getCategory());
            holder.title.setText(post.getTitle());
            holder.description.setText(post.getDescription());
        }

        //Returns size of list of posts
        @Override
        public int getItemCount() {
            return postList.size();
        }
    }
