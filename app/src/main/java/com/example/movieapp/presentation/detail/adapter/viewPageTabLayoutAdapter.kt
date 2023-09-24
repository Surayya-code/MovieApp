package com.example.movieapp.presentation.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movieapp.presentation.detail.CommentsFragment
import com.example.movieapp.presentation.detail.MoreLikeThisFragment
import com.example.movieapp.presentation.detail.TrailersFragment

class ViewPagerTabLayoutAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val id: Int
)
    : FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                TrailersFragment(id)
            }
            1->{
                MoreLikeThisFragment(id)
            }
            2->{
                CommentsFragment(id)
            }
            else->Fragment()
        }
    }
    }