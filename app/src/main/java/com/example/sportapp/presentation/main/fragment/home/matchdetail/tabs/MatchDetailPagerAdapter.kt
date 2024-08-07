package com.example.sportapp.presentation.main.fragment.home.matchdetail.tabs

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MatchDetailPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position)
        {
            0 -> StatsFragment()
            1 -> LineupFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }

}