package com.tkb.dagger.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.tkb.dagger.models.User;
import com.tkb.dagger.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;
    @Inject
   public AuthViewModel(AuthApi authApi){
        this.authApi = authApi;
        Log.e(TAG,"ViewModel is working properly");

        authApi.getUser(1)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        Log.e(TAG,"User email: "+user.getEmail());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"Error: "+e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
