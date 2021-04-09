package com.example.submission_2_fundamental.databinding

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submission_2_fundamental.R
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("avatar")
    fun avatar(circleImageView: CircleImageView, avatar: String) =
    Glide.with(circleImageView).load(avatar)
        .apply(RequestOptions.circleCropTransform().placeholder(R.drawable.logo)
        ).into(circleImageView)
