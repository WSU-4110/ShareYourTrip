package com.example.shareyourtrip;

import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shareyourtrip.R;

import java.util.List;

    public class UserPostAdapter extends RecyclerView.Adapter<UserPostAdapter.MyViewHolder> {

        private List<UserPost> postList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView city, state, category, title, description;

            public MyViewHolder(View view) {
                super(view);
                city = (TextView) view.findViewById(R.id.city);
                state = (TextView) view.findViewById(R.id.state);
                category = (TextView) view.findViewById(R.id.category);
                title = (TextView) view.findViewById(R.id.title);
                description = (TextView) view.findViewById(R.id.description);
            }
        }

        public UserPostAdapter(List<Movie> moviesList) {
            this.postList = postList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.post_list_row, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            UserPost post = postList.get(position);
            holder.city.setText(post.getCity());
            holder.state.setText(post.getState());
            holder.category.setText(post.getCategory());
            holder.title.setText(post.getTitle());
            holder.description.setText(post.getDescription());

        }

        @Override
        public int getItemCount() {
            return postList.size();
        }
    }
