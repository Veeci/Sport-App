package com.example.sportapp.presentation.auth.fragment.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.sportapp.R
import com.example.sportapp.presentation.auth.fragment.onboarding.screen.FirstScreen
import com.example.sportapp.presentation.auth.fragment.onboarding.screen.SecondScreen
import com.example.sportapp.presentation.auth.fragment.onboarding.screen.ThirdScreen
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class ViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        val wormDotsIndicator = view.findViewById<WormDotsIndicator>(R.id.worm_dots_indicator)
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = adapter
        wormDotsIndicator.attachTo(viewPager)

        return view
    }

}