package com.tkb.dagger.ui.main.post;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tkb.dagger.R;
import com.tkb.dagger.models.Post;
import com.tkb.dagger.ui.main.Resource;
import com.tkb.dagger.util.VerticalSpacingItemDecoration;
import com.tkb.dagger.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;

import dagger.android.support.DaggerFragment;

public class PostsFragment extends DaggerFragment {
    private static final String TAG = "PostsFragment";

    private PostsViewModel postsViewModel;
    private RecyclerView recyclerView;

    @Inject
    PostsRecyclerAdapter adapter;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_posts, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view);
        postsViewModel = ViewModelProviders.of(this,providerFactory).get(PostsViewModel.class);

        initRecyclerView();
        subscribeObserver();
    }

    private void subscribeObserver() {
        postsViewModel.observePosts().removeObservers(getViewLifecycleOwner());
        postsViewModel.observePosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if (listResource !=null ){
                    Log.e(TAG,"onChanges: "+listResource.data);
                    switch(listResource.status){
                        case LOADING:{
                            Log.e(TAG,"Data is loading");
                            break;
                        }
                        case SUCCESS:{
                            Log.e(TAG,"Successful");
                            adapter.setPosts(listResource.data);
                            break;
                        }
                        case ERROR:{
                            Log.e(TAG,"Error loading data");
                            break;
                        }
                    }
                }
            }
        });
    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpacingItemDecoration itemDecoration = new VerticalSpacingItemDecoration(15);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
    }

}
