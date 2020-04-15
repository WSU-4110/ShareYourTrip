package com.example.shareyourtrip;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
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
    protected List<Post> postList;
    int count_dislike = 0;
    int count_like = 0;
    boolean flag = false;
    String[] colarr = new String[] {"t"};
    String[] valarr = new String[] {"t"};
    String[] argsarr = new String[] {"t"};
    protected PostDAO postDAO;
    private List<Post> favPostList;
    Context currentContext;
    protected Post clickedPost;


    private boolean isProfilePage;



    //Copy constructor
    public PostAdapter(List<Post> postList) {
        this.postList = postList;
    }

    public static void alertDisplay(Context context,String title, String msg){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    //This is a ViewHolder which holds 5 TextViews which make up our post.
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView location, category, title, description, user, date, likeCount, dislikecount, id;
        public ToggleButton favButton, likeButton, dislikeButton;
        public Button deleteButton;

        //Constructor of the ViewHolder, initializes TextViews
        public MyViewHolder(View view) {
            super(view);
            //id = (TextView) view.findViewById(R.id.id);
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
            deleteButton = (Button) view.findViewById(R.id.deleteButton);
        }
    }


    //Copy constructor
    public PostAdapter(List<Post> postList, Context context) {

        this.postList = postList;
        postDAO = new PostDAO(context);
        currentContext = context;
        favPostList = new ArrayList<Post>();
    }

    //Returns a ViewHolder given the context its parent is in
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        postDAO = new PostDAO(parent.getContext());

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    //Once the ViewHolder is set in place, we retrieve the text from the UserPost
    //and put it in the TextViews
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        //Setting views with values from postList
        final Post post = postList.get(position);

        holder.location.setText(post.getCity() + ", " + post.getState());
        holder.category.setText(post.getCategory());
        holder.title.setText(post.getTitle());
        holder.description.setText(post.getDescription());
        holder.user.setText(post.getUser());
        holder.date.setText(post.getDate());
        holder.likeCount.setText( post.getUp());
        holder.dislikecount.setText( post.getDown());
        //holder.id.setText( post.getId());


        //TODO: add isFavorited to database
        //If the post is favorited, fill in heart and set button on by default
        if (post.getFavorited()) {
            holder.favButton.setBackgroundResource(R.drawable.ic_favorite);
            holder.favButton.setChecked(true);
        }
        //If the post isnt favorited, empty heart and set button off by default
        else{
            holder.favButton.setBackgroundResource(R.drawable.ic_unfavorited);
            holder.favButton.setChecked(false);
        }


        holder.favButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if( (currentContext instanceof SearchActivity) || (currentContext instanceof HomePageActivity) ) {
                    if (isChecked) {
                        holder.favButton.setBackgroundResource(R.drawable.ic_favorite);
                        int pos = holder.getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            Post post = postList.get(pos);
                            clickedPost = new Post(post);
                            post.setFavorited(true);
                            postDAO.insertFavPost(post);
                        }
                    }
                    else{
                        int pos = holder.getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            Post post = postList.get(pos);
                            clickedPost = new Post(post);
                            if (post.getFavorited()) {
                                //Toast.makeText(currentContext, "This post is already you favortie!", Toast.LENGTH_LONG).show();
                                alertDisplay(currentContext,"Unsuccessful Favoriting", "This post is already your favorite!");
                            }
                        }
                    }
                }

                if(currentContext instanceof FavoriteActivity) {
                    if (!isChecked) {
                        int pos = holder.getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            Post post = postList.get(pos);
                            clickedPost = new Post(post);
                            post.setFavorited(false);
                            String[] args = {clickedPost.getId().toString(), FirebaseAuth.getInstance().getCurrentUser().getEmail()};
                            postDAO.deleteFavPost("postid = '" + clickedPost.getId().toString() + "' and useremail = '" + FirebaseAuth.getInstance().getCurrentUser().getEmail() + "'", null);
                            holder.favButton.setBackgroundResource(R.drawable.ic_unfavorited);
                            alertDisplay(currentContext,"Unfavorited Successfully","You unfavorited this post!");
                            //Toast.makeText(currentContext,"You unfavorited this post!",Toast.LENGTH_LONG).show();
                            currentContext.startActivity(new Intent(currentContext, FavoriteActivity.class));
                        }
                    }
                }

            }
        });

        //like/dislike logic
        holder.likeButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //If like button toggled on
                if (isChecked) {
                    holder.likeButton.setBackgroundResource(R.drawable.ic_like_enabled);
                    //..and dislike is toggled on
                    if (holder.dislikeButton.isChecked()) {
                        holder.dislikeButton.setChecked(false);//toggle off dislike.
                    }

                    // Increases like count by 1
                    count_like = Integer.parseInt(holder.likeCount.getText().toString());
                    count_like++;
                    post.setUp(Integer.toString(count_like));
                    holder.likeCount.setText(Integer.toString(count_like));

                }

                //If like button is toggled off
                else {
                    holder.likeButton.setBackgroundResource(R.drawable.ic_like);

                    // Decreases like count by 1
                    count_like = Integer.parseInt(holder.likeCount.getText().toString());
                    count_like = count_like - 1;
                    post.setUp(Integer.toString(count_like));
                    holder.likeCount.setText(Integer.toString(count_like));

                }

                // Set array values for database
                colarr[0] = "up";
                valarr[0] = holder.likeCount.getText().toString();
                argsarr[0] = post.getId();

                //Code to update database
                flag = postDAO.updatePost( colarr, valarr, "id=?", argsarr);

            }
        });

        holder.dislikeButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //If dislike toggled on
                if (isChecked) {
                    holder.dislikeButton.setBackgroundResource(R.drawable.ic_dislike_enabled);
                    //...and if like is toggled on
                    if (holder.likeButton.isChecked()) {
                        holder.likeButton.setChecked(false);//toggle off like
                    }

                    // Increases dislike count by 1
                    count_dislike = Integer.parseInt(holder.dislikecount.getText().toString());
                    count_dislike++;
                    post.setDown(Integer.toString(count_dislike));
                    holder.dislikecount.setText(Integer.toString(count_dislike));


                }
                //If dislike is toggled off..
                else {
                    holder.dislikeButton.setBackgroundResource(R.drawable.ic_dislike);

                    // Decreases dislike count by 1
                    count_dislike = Integer.parseInt(holder.dislikecount.getText().toString());
                    count_dislike--;
                    post.setDown(Integer.toString(count_dislike));
                    holder.dislikecount.setText(Integer.toString(count_dislike));

                }

                colarr[0] = "down";
                valarr[0] = holder.dislikecount.getText().toString();
                argsarr[0] = post.getId();

                //Code to update database
                flag = postDAO.updatePost(colarr, valarr, "id=?", argsarr);

            }
        });
    }


    //Returns size of list of posts
    @Override
    public int getItemCount() {
        return postList.size();
    }

    public Post getClickedPost(){
        return clickedPost;
    }

}
