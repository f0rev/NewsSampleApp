package com.feevx.ravan.newssampleapp.ui.fragments;


import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feevx.ravan.newssampleapp.R;
import com.feevx.ravan.newssampleapp.ui.activities.MainActivity;
import com.feevx.ravan.newssampleapp.databinding.FragmentNewsDetailsBinding;
import com.feevx.ravan.newssampleapp.viewmodels.NewsViewModel;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class NewsDetailsFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    Application mContext;

    private MainActivity mMainActivity;

    public NewsDetailsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        configureDagger();
        FragmentNewsDetailsBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_news_details, container, false);
        NewsViewModel newsViewModel = ViewModelProviders.of(getActivity()).get(NewsViewModel.class);
        newsViewModel.getSelectedNews().observe(this, news -> {
            mMainActivity.getSupportActionBar().setTitle(news.getTitle());
            binding.setNews(news);
        });
        mMainActivity = (MainActivity) getActivity();
        mMainActivity.showBackButton(true);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mMainActivity.showBackButton(false);
        mMainActivity.getSupportActionBar().setTitle(getString(R.string.app_name));
    }

    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

}
