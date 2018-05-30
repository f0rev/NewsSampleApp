package com.feevx.ravan.newssampleapp.ui.fragments;


import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feevx.ravan.newssampleapp.R;
import com.feevx.ravan.newssampleapp.api.response.NewsItem;
import com.feevx.ravan.newssampleapp.repositories.NewsRepository;
import com.feevx.ravan.newssampleapp.ui.adapters.NewsItemsAdapter;
import com.feevx.ravan.newssampleapp.viewmodels.NewsViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


public class FootballFragment extends NewsFragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    Application mContext;

    private View mRootView;
    private RecyclerView mNewsRecyclerView;
    private NewsItemsAdapter mNewsAdapter;
    private NewsViewModel mNewsViewModel;

    public FootballFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRootView = this.getView();
        assert mRootView != null;
        mNewsRecyclerView = mRootView.findViewById(R.id.news_list);
        configureList();
        configureDagger();
        configureViewModel();
    }

    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    private void configureViewModel() {
        startLoading();
        mNewsViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(NewsViewModel.class);
        mNewsViewModel.init(NewsRepository.FOOTBALL);
        mNewsViewModel.getFootballNews().observe(this, user -> {
            stopLoading();
            updateUI(user);
        });
    }

    private void configureList() {
        mNewsRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mNewsAdapter = new NewsItemsAdapter(this);
        mNewsRecyclerView.setAdapter(mNewsAdapter);
    }

    private void updateUI(List<NewsItem> newsItems) {
        mNewsAdapter.updateData(newsItems);
    }

    @Override
    public void onNewsClicked(NewsItem newsItem) {
        super.onNewsClicked(newsItem);
        mNewsViewModel.setSelectedNews(newsItem);
    }
}
