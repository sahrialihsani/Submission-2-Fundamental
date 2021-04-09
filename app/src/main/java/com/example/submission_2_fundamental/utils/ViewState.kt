package com.example.submission_2_fundamental.utils

import android.content.res.Resources
import android.view.View
import com.example.submission_2_fundamental.R
import com.example.submission_2_fundamental.databinding.FragmentFollowBinding
import com.example.submission_2_fundamental.databinding.FragmentMainBinding

class ViewState (private val case: Int) {

    fun loading(homeBinding: FragmentMainBinding?, followBinding: FragmentFollowBinding?){
        when(case){
            1 -> {
                homeBinding?.blanklayout?.notfound?.visibility = View.GONE
                homeBinding?.progressBar?.visibility = View.VISIBLE
                homeBinding?.rvHome?.visibility = View.GONE
            }
            2 -> {
                followBinding?.blankLayout?.notfound?.visibility = View.GONE
                followBinding?.progressBar?.visibility = View.VISIBLE
                followBinding?.rvFollow?.visibility = View.GONE
            }
        }
    }

    fun success(homeBinding: FragmentMainBinding?, followBinding: FragmentFollowBinding?){
        when(case){
            1 ->{
                homeBinding?.blanklayout?.notfound?.visibility = View.GONE
                homeBinding?.progressBar?.visibility = View.INVISIBLE
                homeBinding?.rvHome?.visibility = View.VISIBLE
            }

            2 ->{
                followBinding?.blankLayout?.notfound?.visibility = View.INVISIBLE
                followBinding?.progressBar?.visibility = View.INVISIBLE
                followBinding?.rvFollow?.visibility = View.VISIBLE
            }
        }
    }

    fun error(homeBinding: FragmentMainBinding? ,followBinding: FragmentFollowBinding? ,message: String?, resources: Resources){
        when(case){
            1 -> {
                homeBinding?.blanklayout?.notfound?.visibility = View.VISIBLE
                homeBinding?.blanklayout?.tvBlank?.text = message ?: resources.getString(R.string.not_found)
                homeBinding?.progressBar?.visibility = View.INVISIBLE
                homeBinding?.rvHome?.visibility = View.INVISIBLE
            }
            2 -> {
                followBinding?.blankLayout?.notfound?.visibility = View.VISIBLE
                followBinding?.blankLayout?.tvBlank?.text = message ?: resources.getString(R.string.not_found)
                followBinding?.progressBar?.visibility = View.INVISIBLE
                followBinding?.rvFollow?.visibility = View.INVISIBLE
            }
        }
    }
}