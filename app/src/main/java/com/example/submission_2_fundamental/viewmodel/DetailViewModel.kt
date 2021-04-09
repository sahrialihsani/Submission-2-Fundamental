package com.example.submission_2_fundamental.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.submission_2_fundamental.api.UserRetrofit
import com.example.submission_2_fundamental.model.UserModel
import com.example.submission_2_fundamental.utils.Source

class DetailViewModel: ViewModel() {

    private val username: MutableLiveData<String> = MutableLiveData()

    val dataDetail: LiveData<Source<UserModel>> = Transformations
        .switchMap(username) {
            UserRetrofit.getUserDetail(it)
        }

    fun setDetail(userid: String) {
        if (username.value == userid) {
            return
        }
        username.value = userid
    }
}