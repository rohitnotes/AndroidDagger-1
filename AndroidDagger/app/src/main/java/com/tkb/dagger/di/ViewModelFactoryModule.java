package com.tkb.dagger.di;

import androidx.lifecycle.ViewModelProvider;

import com.tkb.dagger.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ViewModelFactoryModule {

    /**
     * @Binds and @Provides are almost same , following two methods has similar meaning
     * and can be used alternatively. If there are any login inside method, then second one
     * should be used. This class will provide the dependency to ViewModelProviderFactory
     *
     * @Provides and @Binds annotations are same, when we have a method body we use @Binds and
     * when the method body is missing (abstract) the we use @Provides
     * @param factory
     * @return
     */
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory factory);

   /* @Provides
    public static ViewModelProvider.Factory
    bindViewModelFactory(ViewModelProviderFactory factory){
    return factory;
    }*/

}
