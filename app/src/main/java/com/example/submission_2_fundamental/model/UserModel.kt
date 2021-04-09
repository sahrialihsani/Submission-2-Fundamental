package com.example.submission_2_fundamental.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel (
    val id: Int,
    val followers: Int,
    val public_repos: Int,
    val following: Int,
    val name:String,
    val login:String,
    val avatar_url: String,
    val location:String,
    val company: String,
    val type: String
):Parcelable