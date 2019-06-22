package com.tkb.dagger.di;

import com.tkb.dagger.di.auth.AuthModule;
import com.tkb.dagger.di.auth.AuthViewModelModule;
import com.tkb.dagger.di.main.MainFragmentBuilderModule;
import com.tkb.dagger.di.main.MainViewModelModule;
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

    // In every module , two sub module have been added, one for VIew (Activity or Fragment) and another for ViewModel
    @ContributesAndroidInjector(modules = {MainFragmentBuilderModule.class, MainViewModelModule.class})
    abstract MainActivity contributeMainActivity();
}
