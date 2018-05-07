package com.github.skomaromi.bugsy.view;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.github.skomaromi.bugsy.R;
import com.github.skomaromi.bugsy.model.PostSearchResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsActivity extends AppCompatActivity implements PostsContract.PostsView, SwipeRefreshLayout.OnRefreshListener {

    private PostsContract.PostsPresenter mPostsPresenter;
    private PostSearchResultAdapter mAdapter;
    public ArrayList<String> categories;
    private int lastSelectedCategoryIdx;

    private SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.rv_posts) RecyclerView rvPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        categories = new ArrayList<>();
        lastSelectedCategoryIdx = 0;

        mRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.srl_activitycontainer);
        mRefreshLayout.setOnRefreshListener(this);

        ButterKnife.bind(this);

        mPostsPresenter = new PostsPresenter(this);
        setUpRecyclerView();
        mPostsPresenter.fetchPosts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_posts_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ArrayList<String> v = new ArrayList<>();
        v.add("All");
        v.addAll(categories);

        String[] values = v.toArray(new String[0]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Choose a category");
        builder.setItems(values, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reloadPosts(which);
            }
        });

        builder.create().show();

        return true;
    }

    @Override
    public void onRefresh() {
        reloadPosts(lastSelectedCategoryIdx);
    }

    private void reloadPosts(int idx) {
        if (idx == 0) {
            mPostsPresenter.fetchPosts();
        }
        else {
            mPostsPresenter.fetchPostsByCategory(categories.get(idx - 1));
        }
        lastSelectedCategoryIdx = idx;
    }

    public void hideSwipeProgress() {
        mRefreshLayout.setRefreshing(false);
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
        if(categories.size() == 0) {
            for (PostSearchResult p : posts) {
                if(!categories.contains(p.getCategory()))
                categories.add(p.getCategory());
            }
        }

        mAdapter.refreshData(posts);
    }

    @Override
    public void displayError() {
        Toast.makeText(this, R.string.error_default, Toast.LENGTH_SHORT).show();
    }
}
