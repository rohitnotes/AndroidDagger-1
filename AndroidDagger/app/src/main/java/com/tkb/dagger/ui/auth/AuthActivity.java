package com.tkb.dagger.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.tkb.dagger.R;
import com.tkb.dagger.di.DaggerAppComponent;
import com.tkb.dagger.models.User;
import com.tkb.dagger.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

import static com.tkb.dagger.ui.auth.AuthResource.AuthStatus.LOADING;

public class AuthActivity extends DaggerAppCompatActivity {

    private static final String TAG = "AuthActivity";

    AuthViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory ;
    @Inject
    RequestManager requestManager;

    @Inject
    Drawable logo;

    EditText userId;
    Button loginButton;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        viewModel = ViewModelProviders.of(this,providerFactory).get(AuthViewModel.class);
        setLogo();

        userId = findViewById(R.id.user_id_input);
        loginButton = findViewById(R.id.login_button);

        progressBar = findViewById(R.id.progress_bar);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAttempt();
            }
        });

        subscribeObserver();
    }

    private void subscribeObserver(){
        viewModel.observeAuthState().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null){
                    switch (userAuthResource.status){
                        case LOADING:{
                            showProgressBar(true);
                            break;
                        }
                        case AUTHENTICATED:{
                            showProgressBar(false);

                            Log.e(TAG," Login Successfully :"+userAuthResource.data.getEmail());
                            break;
                        }
                        case NOT_AUTHENTICATED:{
                            showProgressBar(false);
                            break;
                        }
                        case ERROR:{
                            showProgressBar(false);
                            Toast.makeText(AuthActivity.this,"Login faile: "+userAuthResource.message,Toast.LENGTH_LONG).show();

                            break;
                        }
                    }
                }
            }
        });
    }

    private void showProgressBar(boolean status){
        if (status){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
    private void loginAttempt() {
        if (TextUtils.isEmpty(userId.getText())){
            return;
        }

        viewModel.authenticationWithId(Integer.parseInt(userId.getText().toString()));

    }

    private void setLogo() {
        requestManager.load(logo).into((ImageView)findViewById(R.id.login_logo));
    }
}
