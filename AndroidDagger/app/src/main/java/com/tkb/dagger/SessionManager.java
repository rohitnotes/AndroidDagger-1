package com.tkb.dagger;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.tkb.dagger.models.User;
import com.tkb.dagger.ui.auth.AuthResource;

import java.lang.annotation.Target;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * This class with manage session of whole application.
 */
@Singleton
public class SessionManager {

    private static final String TAG = "SessionManager";
    // The benefits of using livedata here is, it will notify all the observer if the session is out.
    private MediatorLiveData<AuthResource<User>>cacheUser = new MediatorLiveData<>();
    @Inject
    public SessionManager(){

    }

    public void authenticateWithId(final LiveData<AuthResource<User>> source){
        if (cacheUser != null){
            cacheUser.setValue(AuthResource.loading((User)null));
            cacheUser.addSource(source, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> authResource) {
                    cacheUser.setValue(authResource);
                    cacheUser.removeSource(source);
                }
            });
        }else {
            Log.e(TAG,"Previous session detected please retrieve user from session");
        }
    }

    public void logout(){
        Log.e(TAG,"Just have logged out");
        cacheUser.setValue(AuthResource.<User>logout());
    }

    public LiveData<AuthResource<User>> getAuthUser(){
        return cacheUser;
    }
}
