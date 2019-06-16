package com.tkb.dagger.di;

import dagger.Module;
import dagger.Provides;

/**
 * AppModule is used to provide those objects , which is not related with Activity, Rather related
 * with application.
 */
@Module
public class AppModule {

    @Provides
    static String provideString(){
        return "Hi Tarun";
    }
}
