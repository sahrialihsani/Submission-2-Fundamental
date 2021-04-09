package com.example.submission_2_fundamental.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission_2_fundamental.R
import com.example.submission_2_fundamental.adapter.UserAdapter
import com.example.submission_2_fundamental.databinding.FragmentFollowBinding
import com.example.submission_2_fundamental.utils.ChooseFollow
import com.example.submission_2_fundamental.utils.Types
import com.example.submission_2_fundamental.utils.ViewState
import com.example.submission_2_fundamental.viewmodel.FollowViewModel

class FollowFragment : Fragment() {
    companion object {
        fun newInstance(username: String, type: String) =
            FollowFragment().apply {
                arguments = Bundle().apply {
                    putString(USERNAME, username)
                    putString(TYPE, type)
                }
            }
        const val stateFollowId = 2
        private const val TYPE = "type"
        private const val USERNAME = "username"
    }

    private lateinit var followBinding: FragmentFollowBinding
    private lateinit var adapterUser: UserAdapter
    private lateinit var viewModelFollow: FollowViewModel
    private lateinit var username: String
    private var type: String? = null
    private var showState = ViewState(stateFollowId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(USERNAME).toString()
            type = it.getString(TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        followBinding = FragmentFollowBinding.inflate(layoutInflater, container, false)
        return followBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterUser = UserAdapter(arrayListOf()) { user, _ ->
            Toast.makeText(context, "username: "+user, Toast.LENGTH_SHORT).show()
        }

        followBinding.rvFollow.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterUser
        }

        viewModelFollow = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        ).get(FollowViewModel::class.java)

        when (type) {
            resources.getString(R.string.following) -> viewModelFollow.setFollow(username, ChooseFollow.MENGIKUTI)
            resources.getString(R.string.followers) -> viewModelFollow.setFollow(username, ChooseFollow.PENGIKUT)
            else -> showState.error(null, followBinding, null, resources)
        }
        observeFollow()
    }

    private fun observeFollow() {
        viewModelFollow.dataFollow.observe(viewLifecycleOwner, Observer {
            when (it.types) {
                Types.Success ->
                    if (!it.data.isNullOrEmpty()) {
                        showState.success(null, followBinding)
                        adapterUser.run { setData(it.data) }
                    } else {
                        showState.error(null,
                            followBinding,
                            resources.getString(R.string.not_have, username, type),
                            resources)
                    }
               Types.Loading -> showState.loading(null, followBinding)
               Types.Error -> showState.error(null, followBinding, it.messages, resources)
            }
        })
    }
}