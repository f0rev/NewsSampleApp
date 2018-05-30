package com.feevx.ravan.newssampleapp.di.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.feevx.ravan.newssampleapp.di.key.ViewModelKey;
import com.feevx.ravan.newssampleapp.viewmodels.FactoryViewModel;
import com.feevx.ravan.newssampleapp.viewmodels.NewsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel.class)
    abstract ViewModel bindNewsViewModel(NewsViewModel repoViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}
