package com.tkb.dagger.di.main;

import com.tkb.dagger.ui.main.post.PostsFragment;
import com.tkb.dagger.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @ContributesAndroidInjector annotation generates sub-components under the hood.
 */
@Module
public abstract class MainFragmentBuilderModule {
    @ContributesAndroidInjector
    public abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    public abstract PostsFragment contributePostFragment();
}
