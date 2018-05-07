package com.github.skomaromi.bugsy.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.github.skomaromi.bugsy.R;
import com.github.skomaromi.bugsy.model.PostSearchResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsActivity extends AppCompatActivity implements PostsContract.PostsView {

    private PostsContract.PostsPresenter mPostsPresenter;
    private PostSearchResultAdapter mAdapter;
    @BindView(R.id.rv_posts) RecyclerView rvPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        ButterKnife.bind(this);

        mPostsPresenter = new PostsPresenter(this);
        setUpRecyclerView();
        mPostsPresenter.fetchPosts();
    }

    private void setUpRecyclerView() {
        rvPosts.setLayoutManager(
                new LinearLayoutManager(
                        this,
                        LinearLayoutManager.VERTICAL,
                        false
                )
        );

        rvPosts.addItemDecoration(
                new DividerItemDecoration(
                        this,
                        LinearLayoutManager.VERTICAL
                )
        );

        mAdapter = new PostSearchResultAdapter();
        rvPosts.setAdapter(mAdapter);
    }

    @Override
    public void displayPosts(List<PostSearchResult> posts) {
        mAdapter.refreshData(posts);
    }

    @Override
    public void displayError() {
        Toast.makeText(this, R.string.error_default, Toast.LENGTH_SHORT).show();
    }
}
