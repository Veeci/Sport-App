package com.example.sportapp.presentation.main.fragment.explore.leaguelist.league.tabs.table.team.tabs

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TeamDetailPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment)
{
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position)
        {
            0 -> DetailFragment()
            1 -> SquadFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }

}