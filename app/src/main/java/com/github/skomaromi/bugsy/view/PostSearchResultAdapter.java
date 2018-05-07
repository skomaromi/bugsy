package com.github.skomaromi.bugsy.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.skomaromi.bugsy.R;
import com.github.skomaromi.bugsy.model.PostSearchResult;
import com.github.skomaromi.bugsy.model.PostSearchResults;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class PostSearchResultAdapter extends RecyclerView.Adapter<PostSearchResultAdapter.PostSearchResultViewHolder> {
    private List<PostSearchResult> mPosts;

    public PostSearchResultAdapter() { mPosts = new ArrayList<>(); }

    public void refreshData(List<PostSearchResult> posts) {
        mPosts.clear();
        mPosts.addAll(posts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostSearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new PostSearchResultViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostSearchResultViewHolder holder, int position) {
        PostSearchResult result = mPosts.get(position);

        holder.tv_post_title.setText(result.getTitle());
        holder.tv_post_description.setText(result.getDescription());
        holder.tv_post_date.setText(result.getDate());
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    class PostSearchResultViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_post_title) TextView tv_post_title;
        @BindView(R.id.tv_post_description) TextView tv_post_description;
        @BindView(R.id.tv_post_date) TextView tv_post_date;

        public PostSearchResultViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
