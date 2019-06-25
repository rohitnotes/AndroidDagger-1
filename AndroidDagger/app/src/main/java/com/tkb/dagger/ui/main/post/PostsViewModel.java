package com.tkb.dagger.ui.main.post;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.tkb.dagger.SessionManager;
import com.tkb.dagger.models.Post;
import com.tkb.dagger.models.User;
import com.tkb.dagger.network.main.MainApi;
import com.tkb.dagger.ui.main.Resource;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.xml.transform.Source;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PostsViewModel extends ViewModel {
    private static final String TAG = "PostsViewModel";
    final SessionManager sessionManager;
    final MainApi mainApi;

    private MediatorLiveData<Resource<List<Post>>> posts;
    @Inject
    public PostsViewModel(SessionManager sessionManager, MainApi mainApi){
        this.mainApi = mainApi;
        this.sessionManager = sessionManager;

        Log.e(TAG, "PostViewModel is working");
    }

    LiveData<Resource<List<Post>>> observePosts(){

        if (posts == null){
            posts = new MediatorLiveData<>();
            posts.setValue(Resource.loading((List<Post>)null));

          final LiveData<Resource<List<Post>>> source = LiveDataReactiveStreams.fromPublisher(
                  mainApi.getPostFromUser(sessionManager.getAuthUser().getValue().data.getId())
                  .onErrorReturn(new Function<Throwable, List<Post>>() {
                      @Override
                      public List<Post> apply(Throwable throwable) throws Exception {
                          Log.e(TAG,"Error");
                          Post post = new Post();
                          post.setId(-1);
                          List<Post> postList = new ArrayList<>();
                          postList.add(post);
                          return postList;
                      }
                  }).map(new Function<List<Post>, Resource<List<Post>>>() {
                      @Override
                      public Resource<List<Post>> apply(List<Post> posts) throws Exception {
                          if (posts.size() > 0 ){
                              if (posts.get(0).getId() == -1){
                                  return Resource.error("Something went wrong",null);
                              }
                          }

                          return Resource.success(posts);
                      }
                  }).subscribeOn(Schedulers.io())
          );

          posts.addSource(source, new Observer<Resource<List<Post>>>() {
              @Override
              public void onChanged(Resource<List<Post>> listResource) {
                  posts.setValue(listResource);
                  posts.removeSource(source);
              }
          });
        }
        return posts;
    }
}
