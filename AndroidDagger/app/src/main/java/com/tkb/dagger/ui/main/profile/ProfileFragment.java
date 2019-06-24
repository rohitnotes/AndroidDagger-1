package com.tkb.dagger.ui.main.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.tkb.dagger.R;
import com.tkb.dagger.di.ViewModelFactoryModule;
import com.tkb.dagger.models.User;
import com.tkb.dagger.ui.auth.AuthResource;
import com.tkb.dagger.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {
    private static final String TAG = "ProfileFragment";

    private TextView email, user, website;
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;
    private ProfileViewModel profileViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.e(TAG,"Profile ViewModel is connected");
        profileViewModel = ViewModelProviders.of(this,viewModelProviderFactory).get(ProfileViewModel.class);

        email = view.findViewById(R.id.email);
        user = view.findViewById(R.id.username);
        website = view.findViewById(R.id.website);

        subcribeObserver();
    }

    private void subcribeObserver() {
        /**
         * Fragment has own lifecycle , so we need to be sure , we have removed previous observer before
         * assigning new one. It will help to prevent unwanted result
         */
        profileViewModel.getAuthenticatedUser().removeObservers(getViewLifecycleOwner());
        profileViewModel.getAuthenticatedUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> authResource) {

                if (authResource != null){
                    switch (authResource.status){
                        case AUTHENTICATED:{
                            setUserDetails(authResource.data);
                            break;
                        }
                        case ERROR:{
                            setErrorDetails(authResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void setErrorDetails(String message) {
        email.setText(message);
        website.setText("Error!!");
        user.setText("Error!!");
    }

    private void setUserDetails(User data) {
        email.setText(data.getEmail());
        website.setText(data.getWebsite());
        user.setText(data.getUsername());
    }
}
