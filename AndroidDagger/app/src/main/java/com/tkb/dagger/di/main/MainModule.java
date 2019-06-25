package com.tkb.dagger.di.main;

import com.tkb.dagger.network.main.MainApi;
import com.tkb.dagger.ui.main.post.PostsRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @MainScope
    @Provides
    static MainApi provideMainApi(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }

    @MainScope
    @Provides
    static PostsRecyclerAdapter providePostRecyclerAdapter(){
        return new PostsRecyclerAdapter();
    }
}
