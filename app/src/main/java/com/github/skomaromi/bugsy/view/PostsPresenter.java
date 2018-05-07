package com.github.skomaromi.bugsy.view;

import com.github.skomaromi.bugsy.model.PostSearchResult;
import com.github.skomaromi.bugsy.model.PostSearchResults;
import com.github.skomaromi.bugsy.networking.RetrofitUtility;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsPresenter implements PostsContract.PostsPresenter {
    private PostsContract.PostsView mPostsView;

    public PostsPresenter(PostsContract.PostsView postsView) {
        mPostsView = postsView;
    }

    @Override
    public void fetchPosts() {
        //com.github.skomaromi.bugsy.networking.PostsApi c = RetrofitUtility.getPostsApi();
        //Call<PostSearchResults> l = c.findPosts();

        //mPostsView.displayPosts(c.findPosts().getPosts());

        RetrofitUtility.getPostsApi()
                .findPosts()
                    .enqueue(new Callback<PostSearchResults>() {
                        @Override
                        public void onResponse(Call<PostSearchResults> call, Response<PostSearchResults> response) {
                            int k = 6;
                            android.util.Log.d("HEY", "HEY");
                        }

                        @Override
                        public void onFailure(Call<PostSearchResults> call, Throwable t) {
                            android.util.Log.d("HEY", "HEY");
                        }
                    });

        // TODO: remove this code once functionality finished
        /*
        RetrofitUtility.getPostsApi()
                .findPosts()
                    .enqueue(new Callback<List<PostSearchResult>>() {
                        @Override
                        public void onResponse(Call<List<PostSearchResult>> call, Response<List<PostSearchResult>> response) {
                            mPostsView.displayPosts(response.body());
                        }

                        @Override
                        public void onFailure(Call<List<PostSearchResult>> call, Throwable t) {
                            mPostsView.displayError();
                        }
                    });
        */
    }

    @Override
    public void fetchPostsByCategory(String category) {
        // TODO: implement me!
        return;
    }
}
