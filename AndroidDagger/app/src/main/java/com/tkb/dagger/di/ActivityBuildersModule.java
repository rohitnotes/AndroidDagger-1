package com.tkb.dagger.di;

import com.tkb.dagger.di.auth.AuthModule;
import com.tkb.dagger.di.auth.AuthViewModelModule;
import com.tkb.dagger.network.auth.AuthApi;
import com.tkb.dagger.ui.auth.AuthActivity;
import com.tkb.dagger.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    /**
     * Activity AuthViewModelModule.class is added here ,because it will be only necessary
     * for Authentication. It would be added inside AppComponent, if it is necessary for
     * whole application
     * @return
     */
    @ContributesAndroidInjector (modules = {AuthViewModelModule.class, AuthModule.class})
    abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();
}
