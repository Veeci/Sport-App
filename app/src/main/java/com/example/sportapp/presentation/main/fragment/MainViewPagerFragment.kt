package com.example.sportapp.presentation.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NavigationRes
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.widget.ViewPager2
import com.example.sportapp.R
import com.example.sportapp.presentation.ViewPagerAdapter
import com.example.sportapp.presentation.main.fragment.explore.ExploreFragment
import com.example.sportapp.presentation.main.fragment.favourite.FavoriteFragment
import com.example.sportapp.presentation.main.fragment.home.HomeFragment
import com.example.sportapp.presentation.main.fragment.profile.ProfileFragment

class MainViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_view_pager, container, false)

        val fragmentList = arrayListOf<Fragment>(
            createNavHostFragment(R.navigation.navigation_home_main),
            HomeFragment(),
            ExploreFragment(),
            FavoriteFragment(),
            ProfileFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        val viewPager = view.findViewById<ViewPager2>(R.id.mainViewPager)
        viewPager.adapter = adapter

        return view
    }

    private fun createNavHostFragment(@NavigationRes navGraphId: Int): Fragment{
        return NavHostFragment.create(navGraphId)
    }
}