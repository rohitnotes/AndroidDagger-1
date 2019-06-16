package com.tkb.dagger.di.auth;

import androidx.lifecycle.ViewModel;

import com.tkb.dagger.di.ViewModelKey;
import com.tkb.dagger.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.MapKey;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * This class will assign a key to viewModel and viewModel will be provided for injection.
 * If there are multiple viewModels in the project . All the ViewModels will be add in this class.
 */
@Module
public abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel authViewModel);

}
