package com.tkb.dagger.di;

import com.tkb.dagger.di.auth.AuthModule;
import com.tkb.dagger.di.auth.AuthScope;
import com.tkb.dagger.di.auth.AuthViewModelModule;
import com.tkb.dagger.di.main.MainFragmentBuilderModule;
import com.tkb.dagger.di.main.MainModule;
import com.tkb.dagger.di.main.MainScope;
import com.tkb.dagger.di.main.MainViewModelModule;
import com.tkb.dagger.ui.auth.AuthActivity;
import com.tkb.dagger.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * This class contains sub-components
 * @ContributesAndroidInjector annotation generates sub-components under the hood.
 *
 * @AuthScope and @MainScope are two custom scope, they indicates where a object is Bounded
 * and how long will it remain . Object Bounded with scope will be automatically removed if
 * Scope is removed
 */
@Module
public abstract class ActivityBuildersModule {

    /**
     * Activity AuthViewModelModule.class is added here ,because it will be only necessary
     * for Authentication. It would be added inside AppComponent, if it is necessary for
     * whole application
     *
     */
    @AuthScope
    @ContributesAndroidInjector (modules = {AuthViewModelModule.class, AuthModule.class})
    abstract AuthActivity contributeAuthActivity();

    // In every module , two sub module have been added, one for VIew (Activity or Fragment) and another for ViewModel
    @MainScope
    @ContributesAndroidInjector(modules = {MainFragmentBuilderModule.class, MainViewModelModule.class, MainModule.class})
    abstract MainActivity contributeMainActivity();
}
