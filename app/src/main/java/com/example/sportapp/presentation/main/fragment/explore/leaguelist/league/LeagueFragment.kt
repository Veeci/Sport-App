package com.example.sportapp.presentation.main.fragment.explore.leaguelist.league

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.sportapp.R
import com.example.sportapp.data.api.apiService
import com.example.sportapp.data.model.LEAGUEDETAIL
import com.example.sportapp.data.repository.LeagueRepository
import com.example.sportapp.data.repository.MatchRepository
import com.example.sportapp.databinding.FragmentLeagueBinding
import com.example.sportapp.domain.LeagueViewModel
import com.example.sportapp.domain.MatchViewModel
import com.example.sportapp.presentation.main.fragment.explore.leaguelist.league.tabs.LeagueDetailPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class LeagueFragment : Fragment() {

    private var _binding: FragmentLeagueBinding? = null
    private val binding get() = _binding!!

    private val leagueViewModel: LeagueViewModel by activityViewModels {
        LeagueViewModel.Factory(LeagueRepository(apiService))
    }

    private val matchViewModel: MatchViewModel by activityViewModels {
        MatchViewModel.Factory(MatchRepository(apiService))
    }

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLeagueBinding.inflate(layoutInflater, container, false)

        setupFunction()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel()
    {
        leagueViewModel.idLeagueRemember.observe(viewLifecycleOwner, Observer { idLeague ->
            if(idLeague != null)
            {
                leagueViewModel.fetchLeagueDetail(idLeague)
            }
        })

        leagueViewModel.leagueDetail.observe(viewLifecycleOwner, Observer { leagues ->
            displayLeagueDetail(leagues[0])
        })

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_leagueFragment_to_leagueListFragment)
            matchViewModel.clearIdLeagueRemember()
        }
    }

    private fun setupFunction()
    {
        tabLayout = binding.leagueDetailTabLayout
        viewPager = binding.leagueDetailViewPager

        val adapter = LeagueDetailPagerAdapter(this)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 3

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Table"
                1 -> tab.text = "Fixture"
                2 -> tab.text = "Info"
            }
        }.attach()
    }

    private fun displayLeagueDetail(league: LEAGUEDETAIL)
    {
        binding.apply {
            strCurrentSeason.text = league.strCurrentSeason
            if(league.strBanner != null)
            {
                strBanner.load(league.strBanner)
            }
            else
            {
                strBanner.load(league.strPoster)
                val layoutParams = strBanner.layoutParams
                layoutParams.width = 350
                layoutParams.height = 280
                strBanner.layoutParams = layoutParams
                strBanner.requestLayout()
            }
            strLeagueDetail.text = league.strLeague
            strCountryDetail.text = league.strCountry
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}