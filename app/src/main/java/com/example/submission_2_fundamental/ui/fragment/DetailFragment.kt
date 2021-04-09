package com.example.submission_2_fundamental.ui.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.submission_2_fundamental.R
import com.example.submission_2_fundamental.databinding.FragmentDetailBinding
import com.example.submission_2_fundamental.utils.Types
import com.example.submission_2_fundamental.viewmodel.DetailViewModel
import com.google.android.material.tabs.TabLayoutMediator

class DetailFragment  : Fragment() {

    private lateinit var detailBinding: FragmentDetailBinding
    private lateinit var adapterPager: PagerAdapter
    private lateinit var viewModelDetail: DetailViewModel
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelDetail = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(DetailViewModel::class.java)
        viewModelDetail.setDetail(args.Username)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailBinding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        detailBinding.lifecycleOwner = viewLifecycleOwner
        observeDetail()
        return detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            detailBinding.content.transitionName = args.Username
        }

        val tabList = arrayOf(
            resources.getString(R.string.followers),
            resources.getString(R.string.following)
        )
        adapterPager = PagerAdapter(tabList, args.Username, this)
        detailBinding.viewPager.adapter = adapterPager

        TabLayoutMediator(detailBinding.tabs, detailBinding.viewPager) { tab, position ->
            tab.text = tabList[position]
        }.attach()
    }

    private fun observeDetail() {
        viewModelDetail.dataDetail.observe(viewLifecycleOwner, Observer {
            if (it.types == Types.Success) {
                detailBinding.data = it.data
            }
        })
    }

    inner class PagerAdapter(
        private val tabList: Array<String>,
        private val username: String,
        fragment: Fragment
    ) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = tabList.size
        override fun createFragment(position: Int): Fragment = FollowFragment.newInstance(username, tabList[position])
    }
}