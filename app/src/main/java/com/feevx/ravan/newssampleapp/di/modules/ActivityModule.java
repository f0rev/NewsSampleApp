package com.feevx.ravan.newssampleapp.di.modules;



import com.feevx.ravan.newssampleapp.ui.activities.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MainActivity contributeMainActivity();
}
