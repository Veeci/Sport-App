package com.example.sportapp.presentation.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.sportapp.R
import com.example.sportapp.databinding.FragmentMainBinding
import com.example.sportapp.presentation.ViewPagerAdapter
import com.example.sportapp.presentation.main.fragment.explore.ExploreFragment
import com.example.sportapp.presentation.main.fragment.favourite.FavoriteFragment
import com.example.sportapp.presentation.main.fragment.home.HomeFragment
import com.example.sportapp.presentation.main.fragment.profile.ProfileFragment

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        val fragmentList = arrayListOf<Fragment>(
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

        val viewPager = binding.mainViewPager
        viewPager.adapter = adapter
        binding.mainViewPager.isUserInputEnabled = false

        binding.menuBar.homeSection.setOnClickListener {
            viewPager.currentItem = 0
        }

        binding.menuBar.exploreSection.setOnClickListener {
            viewPager.currentItem = 1
        }

        binding.menuBar.favSection.setOnClickListener {
            viewPager.currentItem = 2
        }

        binding.menuBar.profileSection.setOnClickListener {
            viewPager.currentItem = 3
        }

        return binding.root
    }

}