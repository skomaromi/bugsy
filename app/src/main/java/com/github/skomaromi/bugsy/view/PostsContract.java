package com.github.skomaromi.bugsy.view;

import java.util.List;

import com.github.skomaromi.bugsy.model.PostSearchResult;

public interface PostsContract {
    interface PostsPresenter {
        void fetchPosts();
        void fetchPostsByCategory(String category);
    }

    interface PostsView {
        void displayPosts(List<PostSearchResult> posts);
        void displayError();
        void hideSwipeProgress();
    }
}
