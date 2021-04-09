package com.example.submission_2_fundamental.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.submission_2_fundamental.databinding.CardUserBinding
import com.example.submission_2_fundamental.model.UserModel

class UserAdapter(private val users: ArrayList<UserModel>, private val clickListener: (String, View) -> Unit) : RecyclerView.Adapter<UserAdapter.UsersViewHolder>() {

    inner class UsersViewHolder(private val binding: CardUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(users: UserModel, click: (String, View) -> Unit) {
            binding.data = users
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.root.transitionName = users.login
            }
            binding.root.setOnClickListener { click(users.login, binding.root) }
        }
    }
    //set Data dari Model
    fun setData(items: List<UserModel>){
        users.apply {
            clear()
            addAll(items)
        }
        //notifikasi perubahan data ke recycleview
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(
            CardUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) = holder.bind(users[position], clickListener)

    override fun getItemCount(): Int = users.size
}
