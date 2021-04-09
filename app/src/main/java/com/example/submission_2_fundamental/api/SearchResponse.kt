package com.example.submission_2_fundamental.api

import android.os.Parcelable
import com.example.submission_2_fundamental.model.UserModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResponse (
    val total : String,
    val incomplete_result: Boolean? = null,
    val items : List<UserModel>
): Parcelable