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


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
