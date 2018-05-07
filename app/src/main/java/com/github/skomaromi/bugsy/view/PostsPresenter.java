package com.github.skomaromi.bugsy.view;

import com.github.skomaromi.bugsy.model.RssResponse;
import com.github.skomaromi.bugsy.networking.RetrofitUtility;

import okhttp3.HttpUrl;
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
        RetrofitUtility.getPostsApi()
                .findPosts()
                    .enqueue(new Callback<RssResponse>() {
                        @Override
                        public void onResponse(Call<RssResponse> call, Response<RssResponse> response) {
                            mPostsView.displayPosts(response.body().getPosts());
                        }

                        @Override
                        public void onFailure(Call<RssResponse> call, Throwable t) {
                            mPostsView.displayError();
                        }
                    });
    }

    @Override
    public void fetchPostsByCategory(String category) {
        // TODO: implement me!
        return;
    }
}
