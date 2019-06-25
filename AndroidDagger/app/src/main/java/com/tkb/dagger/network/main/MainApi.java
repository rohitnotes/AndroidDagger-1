package com.tkb.dagger.network.main;

import com.tkb.dagger.models.User;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainApi {
    @GET
    Flowable<List<User>> getPostFromUser(
            @Query("userId") int id
    );
}
