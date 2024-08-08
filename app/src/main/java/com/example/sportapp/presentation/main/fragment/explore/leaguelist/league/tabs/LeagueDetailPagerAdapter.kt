package com.example.sportapp.presentation.main.fragment.explore.leaguelist.league.tabs

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sportapp.presentation.main.fragment.explore.leaguelist.league.tabs.fixture.FixtureFragment
import com.example.sportapp.presentation.main.fragment.explore.leaguelist.league.tabs.info.InfoFragment
import com.example.sportapp.presentation.main.fragment.explore.leaguelist.league.tabs.table.TableFragment

class LeagueDetailPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment)
{
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position)
        {
            0 -> TableFragment()
            1 -> FixtureFragment()
            2 -> InfoFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}