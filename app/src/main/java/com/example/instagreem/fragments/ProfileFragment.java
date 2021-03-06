package com.example.instagreem.fragments;

import android.util.Log;

import com.example.instagreem.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostsFragment {

    @Override
    protected void queryPosts() {
        ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);
        postQuery.include(Post.KEY_USER);
        postQuery.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        postQuery.setLimit(20);
        postQuery.addDescendingOrder(Post.KEY_CREATED_KEY);
        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "error with query");
                    e.printStackTrace();
                    return;
                }
                for (int i = 0; i < posts.size(); i++)
                    Log.d(TAG, "Post: " + posts.get(i).getDescription() + " username: " + posts.get(i).getUser().getUsername());
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
