package com.github.skomaromi.bugsy.networking;

import com.github.skomaromi.bugsy.model.PostSearchResult;
import com.github.skomaromi.bugsy.model.PostSearchResults;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostsApi {
    @GET("rss")
    Call<PostSearchResults> findPosts();
}
