package com.feevx.ravan.newssampleapp.di.modules;

import com.feevx.ravan.newssampleapp.ui.fragments.AllNewsFragment;
import com.feevx.ravan.newssampleapp.ui.fragments.BasketballFragment;
import com.feevx.ravan.newssampleapp.ui.fragments.FootballFragment;
import com.feevx.ravan.newssampleapp.ui.fragments.HockeyFragment;
import com.feevx.ravan.newssampleapp.ui.fragments.NewsDetailsFragment;
import com.feevx.ravan.newssampleapp.ui.fragments.VolleyballFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract AllNewsFragment contributeAllNewsFragment();

    @ContributesAndroidInjector
    abstract FootballFragment contributeFootballFragment();

    @ContributesAndroidInjector
    abstract BasketballFragment contributeBasketballFragment();

    @ContributesAndroidInjector
    abstract VolleyballFragment contributeVolleyballFragment();

    @ContributesAndroidInjector
    abstract HockeyFragment contributeHockeyFragment();

    @ContributesAndroidInjector
    abstract NewsDetailsFragment contributeNewsDetailsFragment();
}
