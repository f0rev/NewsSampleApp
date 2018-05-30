package com.feevx.ravan.newssampleapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feevx.ravan.newssampleapp.R;
import com.feevx.ravan.newssampleapp.api.response.NewsItem;
import com.feevx.ravan.newssampleapp.ui.adapters.NewsItemsAdapter;
import com.victor.loading.rotate.RotateLoading;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class NewsFragment extends Fragment implements NewsItemsAdapter.NewsClickHandler{

    @BindView(R.id.rotateloading)
    RotateLoading mRotateloading;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        mRotateloading.bringToFront();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    void startLoading(){
        mRotateloading.start();
    }

    void stopLoading(){
        mRotateloading.stop();
    }

    @Override
    public void onNewsClicked(NewsItem newsItem) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.right_in, R.anim.left_out, R.anim.left_in, R.anim.right_out);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.main_container, new NewsDetailsFragment());
        fragmentTransaction.commit();
    }
}
