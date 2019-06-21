package com.tkb.dagger;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.tkb.dagger.models.User;
import com.tkb.dagger.ui.auth.AuthActivity;
import com.tkb.dagger.ui.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class BaseActivity extends DaggerAppCompatActivity {
    private static final String TAG = "BaseActivity";

    @Inject
   public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObserver();
    }

    private void subscribeObserver(){
        sessionManager.getAuthUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null){
                    switch (userAuthResource.status){
                        case LOADING:{
                            break;
                        }
                        case AUTHENTICATED:{
                            Log.e(TAG," Login Successfully :"+userAuthResource.data.getEmail());
                            break;
                        }
                        case NOT_AUTHENTICATED:{
                            returnToLoginPage();
                            break;
                        }
                        case ERROR:{
                            break;
                        }
                    }
                }
            }
        });
    }

    private void returnToLoginPage(){
        startActivity(new Intent(this,AuthActivity.class));
        finish();
    }
}
