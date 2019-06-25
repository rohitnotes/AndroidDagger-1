package com.tkb.dagger.di.main;

import androidx.lifecycle.ViewModel;

import com.tkb.dagger.di.ViewModelKey;
import com.tkb.dagger.ui.auth.AuthViewModel;
import com.tkb.dagger.ui.main.post.PostsViewModel;
import com.tkb.dagger.ui.main.profile.ProfileFragment;
import com.tkb.dagger.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindMainViewModel(ProfileViewModel profileViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    public abstract ViewModel bindMainViewModel(PostsViewModel postsViewModel);
}
