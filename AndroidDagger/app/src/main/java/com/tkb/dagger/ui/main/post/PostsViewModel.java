package com.tkb.dagger.ui.main.post;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.tkb.dagger.SessionManager;
import com.tkb.dagger.network.main.MainApi;

import java.lang.annotation.Target;

import javax.inject.Inject;

public class PostsViewModel extends ViewModel {
    private static final String TAG = "PostsViewModel";
    final SessionManager sessionManager;
    final MainApi mainApi;

    @Inject
    PostsViewModel(SessionManager sessionManager, MainApi mainApi){
        this.mainApi = mainApi;
        this.sessionManager = sessionManager;

        Log.e(TAG, "PostViewModel is working");
    }
}
