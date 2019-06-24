package com.tkb.dagger.ui.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tkb.dagger.SessionManager;
import com.tkb.dagger.models.User;
import com.tkb.dagger.ui.auth.AuthResource;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {

    private static final String TAG = "ProfileViewModel";

    private final SessionManager sessionManager;
    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        Log.e(TAG,"ProfileViewModel is Ready");
        this.sessionManager = sessionManager;
    }

    LiveData<AuthResource<User>> getAuthenticatedUser (){
        return sessionManager.getAuthUser();
    }
}
