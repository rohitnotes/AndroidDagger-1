package com.tkb.dagger;

import com.tkb.dagger.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * This is the entry point of the application and it will allow to inject an Application object
 */
public class BaseApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        //after building AppComponent's interface Builder following code will be
        // generated to pass application context
        return DaggerAppComponent.builder().application(this).build();
    }
}
