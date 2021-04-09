package com.example.submission_2_fundamental.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.submission_2_fundamental.api.UserRetrofit
import com.example.submission_2_fundamental.model.UserModel
import com.example.submission_2_fundamental.utils.Source

class HomeViewModel: ViewModel() {
    private val username: MutableLiveData<String> = MutableLiveData()

    val searchResult: LiveData<Source<List<UserModel>>> = Transformations
        .switchMap(username) {
            UserRetrofit.userSearch(it)
        }

    fun setSearch(query: String) {
        if (username.value == query) {
            return
        }
        username.value = query
    }
}