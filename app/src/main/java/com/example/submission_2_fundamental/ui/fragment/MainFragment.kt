
package com.example.submission_2_fundamental.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import com.example.submission_2_fundamental.utils.ViewState
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission_2_fundamental.R
import com.example.submission_2_fundamental.adapter.UserAdapter
import com.example.submission_2_fundamental.databinding.FragmentMainBinding
import com.example.submission_2_fundamental.utils.Types
import com.example.submission_2_fundamental.viewmodel.HomeViewModel

class MainFragment : Fragment() {
    companion object{
        const val stateHomeId = 1
    }

    private lateinit var mainBinding: FragmentMainBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var mainViewModel: HomeViewModel
    private var stateview = ViewState(stateHomeId)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainBinding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return mainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(HomeViewModel::class.java)
        mainBinding.blanklayout.tvBlank.text = resources.getString(R.string.search_hint)

        userAdapter = UserAdapter(arrayListOf()) { username, iv ->
            findNavController().navigate(
                MainFragmentDirections.actiondetail(username),
                FragmentNavigatorExtras(
                    iv to username
                )
            )
        }

        mainBinding.rvHome.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = userAdapter
        }

        mainBinding.searchView.apply {
            queryHint = resources.getString(R.string.search_hint)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    mainViewModel.setSearch(query)
                    mainBinding.searchView.clearFocus()
                    return true
                }
                override fun onQueryTextChange(newText: String): Boolean = false
            })
        }
        observeHome()
    }

    private fun observeHome() {
        mainViewModel.searchResult.observe(viewLifecycleOwner, Observer {
            it?.let { source ->
                when (source.types) {
                    Types.Success -> {
                        source.data?.let { users ->
                            if (!users.isNullOrEmpty()) {
                                stateview.success(mainBinding, null)
                                userAdapter.setData(users)
                            } else {
                                stateview.error(mainBinding,null, null, resources)
                            }
                        }
                    }
                    Types.Loading -> stateview.loading(mainBinding, null)
                    Types.Error -> stateview.error(mainBinding, null, it.messages, resources
                    )
                }
            }
        })
    }
}