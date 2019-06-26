package com.tkb.dagger.di;

import android.app.Application;

import com.tkb.dagger.BaseApplication;
import com.tkb.dagger.SessionManager;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**Components are essentially the glue that holds everything together.
 * They are a way of telling Dagger 2 what dependencies should be bundled
 * together and made available to a given instance so they can be used.
 *
 * This is main component of the application
 * modules = {AndroidSupportInjectionModule.class} is always necessary for application level component
 */
@Singleton
@Component (modules = {AndroidSupportInjectionModule.class,
        // ActivityBuildersModule is a class which contains sub components
        ActivityBuildersModule.class,
        AppModule.class,
        ViewModelFactoryModule.class})
public interface AppComponent extends AndroidInjector<BaseApplication> {

    //SessionManger is an application level dependency , that's why it is declared here
    SessionManager sessionManager ();

    /**
     * Override Component.Builder ,
     */
    @Component.Builder
    interface Builder{

        //Bind application instance with app component, @BindsInstance bind two instance which has
        //same lifetime, as Application and AppComponent will be alive enter application lifetime
        //so they are bounded together
        @BindsInstance
        Builder application(Application application);

        //Application will be available for injection after building AppComponent
        AppComponent build();

    }

}
