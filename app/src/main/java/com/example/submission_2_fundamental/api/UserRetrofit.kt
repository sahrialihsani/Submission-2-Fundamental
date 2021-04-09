package com.example.submission_2_fundamental.api

import androidx.lifecycle.liveData
import com.example.submission_2_fundamental.utils.Source
import kotlinx.coroutines.Dispatchers
object UserRetrofit {
    //Function untuk mencari user
    fun userSearch(query: String) = liveData(Dispatchers.IO) {
        emit(Source.loading(null))
        try {
            val userSearch = ApiRetrofit.API_Client.searchUser(query)
            emit(Source.success(userSearch.items))
        } catch (exception: Exception) {
            emit(Source.error(null, exception.message ?: "Error"))
        }
    }
    //mendapatkan data detail user
    fun getUserDetail(username: String) = liveData(Dispatchers.IO) {
        emit(Source.loading(null))
        try {
            emit(Source.success(ApiRetrofit.API_Client.detailUser(username)))
        } catch (exception: Exception) {
            emit(Source.error(null, exception.message ?: "Error"))
        }
    }
    //mendapatkan data follower user terkait
    fun getUserFollowers(username: String) = liveData(Dispatchers.IO) {
        emit(Source.loading(null))
        try {
            emit(Source.success(ApiRetrofit.API_Client.followerUser(username)))
        } catch (exception: Exception) {
            emit(Source.error(null, exception.message ?: "Error"))
        }
    }
    //mendapatkan data following user terkait
    fun getUsersFollowing(username: String) = liveData(Dispatchers.IO) {
        emit(Source.loading(null))
        try {
            emit(Source.success(ApiRetrofit.API_Client.followingUser(username)))
        } catch (exception: Exception) {
            emit(Source.error(null, exception.message ?: "Error"))
        }
    }
}