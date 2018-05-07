package com.github.skomaromi.bugsy.view;

import com.github.skomaromi.bugsy.model.PostSearchResult;
import com.github.skomaromi.bugsy.model.RssResponse;
import com.github.skomaromi.bugsy.networking.RetrofitUtility;

import java.util.ArrayList;
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
        RetrofitUtility.getPostsApi()
                .findPosts()
                    .enqueue(new Callback<RssResponse>() {
                        @Override
                        public void onResponse(Call<RssResponse> call, Response<RssResponse> response) {
                            mPostsView.displayPosts(response.body().getPosts());
                            mPostsView.hideSwipeProgress();
                        }

                        @Override
                        public void onFailure(Call<RssResponse> call, Throwable t) {
                            mPostsView.displayError();
                            mPostsView.hideSwipeProgress();
                        }
                    });
    }

    @Override
    public void fetchPostsByCategory(final String category) {
        RetrofitUtility.getPostsApi()
                .findPosts()
                .enqueue(new Callback<RssResponse>() {
                    @Override
                    public void onResponse(Call<RssResponse> call, Response<RssResponse> response) {
                        List<PostSearchResult> o = response.body().getPosts();
                        ArrayList<PostSearchResult> modified = new ArrayList<>();
                        for (PostSearchResult p : o) {
                            if(p.getCategory().equals(category)) {
                                modified.add(p);
                            }
                        }

                        mPostsView.displayPosts(modified);
                        mPostsView.hideSwipeProgress();
                    }

                    @Override
                    public void onFailure(Call<RssResponse> call, Throwable t) {
                        mPostsView.displayError();
                        mPostsView.hideSwipeProgress();
                    }
                });
    }
}
