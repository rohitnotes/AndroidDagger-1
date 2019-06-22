package com.tkb.dagger.di.main;

import com.tkb.dagger.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuilderModule {
    @ContributesAndroidInjector
    public abstract ProfileFragment contributeProfileFragment();
}
