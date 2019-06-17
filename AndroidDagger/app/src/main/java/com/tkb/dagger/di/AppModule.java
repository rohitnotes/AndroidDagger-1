package com.tkb.dagger.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.tkb.dagger.R;
import com.tkb.dagger.util.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * AppModule is used to provide those objects , which is not related with Activity, Rather related
 * with application.
 */
@Module
public class AppModule {

    @Singleton
    @Provides
    static Retrofit provideRetrofit(){
        return new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }
    @Singleton
    @Provides
    static RequestOptions provideRequestOption(){
        return RequestOptions.placeholderOf(R.drawable.white_background).error(R.drawable.white_background);
    }

    @Singleton
    @Provides
    static RequestManager provideRequestManager(Application application, RequestOptions requestOptions){
       return Glide.with(application).setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    static Drawable provideAppDrawable(Application application){
       return ContextCompat.getDrawable(application,R.drawable.logo);
    }
}
