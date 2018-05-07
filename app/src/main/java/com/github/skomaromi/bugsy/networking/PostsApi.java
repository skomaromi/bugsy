package com.github.skomaromi.bugsy.networking;

import com.github.skomaromi.bugsy.model.RssResponse;


import retrofit2.Call;
import retrofit2.http.GET;

public interface PostsApi {
    @GET("rss")
    Call<RssResponse> findPosts();
}
