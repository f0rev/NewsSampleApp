package com.feevx.ravan.newssampleapp.ui.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.feevx.ravan.newssampleapp.R;
import com.feevx.ravan.newssampleapp.api.response.NewsItem;
import com.feevx.ravan.newssampleapp.databinding.NewsItemLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class NewsItemsAdapter extends RecyclerView.Adapter<NewsItemsAdapter.ViewHolder> {

    private final List<NewsItem> mData = new ArrayList<>();
    private NewsClickHandler mNewsClickHandler;

    public NewsItemsAdapter(NewsClickHandler newsClickHandler) {
        mNewsClickHandler = newsClickHandler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemLayoutBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.news_item_layout, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final NewsItem mNewsItem = mData.get(position);
        holder.mBinding.setNews(mNewsItem);
        holder.mBinding.cardView.setOnClickListener(v -> mNewsClickHandler.onNewsClicked(mNewsItem));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void updateData(List<NewsItem> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public interface NewsClickHandler{
        void onNewsClicked(NewsItem newsItem);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final NewsItemLayoutBinding mBinding;

        ViewHolder(final NewsItemLayoutBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(NewsItem newsItem) {
            mBinding.setVariable(BR.news, newsItem);
            mBinding.executePendingBindings();
        }
    }


}
