package com.example.submission_2_fundamental.api

import com.example.submission_2_fundamental.model.UserModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIClient {
    //Search User
    @GET("search/users")
    suspend fun searchUser(
        @Query("q") q: String?
    ): SearchResponse

    //Get Followers
    @GET("users/{username}/followers")
    suspend fun followerUser(
        @Path("username") username: String?
    ): List<UserModel>

    @GET("users/{username}/following")
    suspend fun followingUser(
        @Path("username") username: String?
    ): List<UserModel>

    //Get Detail user by username
    @GET("users/{username}")
    suspend fun detailUser(@Path("username") username :String?):UserModel


}