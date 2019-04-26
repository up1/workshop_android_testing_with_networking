package com.example.networkingtesting;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {

    @GET("users/{user}")
    Call<User> getUser(@Path("user") String user);

}
