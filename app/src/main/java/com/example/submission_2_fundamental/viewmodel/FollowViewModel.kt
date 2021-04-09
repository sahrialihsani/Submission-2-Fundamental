package com.example.submission_2_fundamental.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.submission_2_fundamental.api.UserRetrofit
import com.example.submission_2_fundamental.model.UserModel
import com.example.submission_2_fundamental.utils.ChooseFollow
import com.example.submission_2_fundamental.utils.Source

class FollowViewModel: ViewModel() {
    private val username: MutableLiveData<String> = MutableLiveData()

    private lateinit var choose: ChooseFollow

    val dataFollow: LiveData<Source<List<UserModel>>> = Transformations
        .switchMap(username) {
            when (choose) {
                ChooseFollow.PENGIKUT -> {
                    UserRetrofit.getUserFollowers(it)
                }
                ChooseFollow.MENGIKUTI -> {
                    UserRetrofit.getUsersFollowing(it)
                }
            }
        }

    fun setFollow(user: String, chooseFollow: ChooseFollow) {
        if (username.value == user) {
            return
        }
        username.value = user
        choose = chooseFollow
    }
}