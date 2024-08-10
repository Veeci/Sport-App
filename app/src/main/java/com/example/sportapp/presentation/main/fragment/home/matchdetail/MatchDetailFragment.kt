package com.example.sportapp.presentation.main.fragment.home.matchdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.sportapp.R
import com.example.sportapp.data.api.apiService
import com.example.sportapp.data.model.LEAGUEMATCH
import com.example.sportapp.data.repository.MatchRepository
import com.example.sportapp.databinding.FragmentMatchDetailBinding
import com.example.sportapp.domain.MatchViewModel
import com.example.sportapp.presentation.ViewPagerAdapter
import com.example.sportapp.presentation.main.fragment.home.matchdetail.tabs.LineupFragment
import com.example.sportapp.presentation.main.fragment.home.matchdetail.tabs.MatchDetailPagerAdapter
import com.example.sportapp.presentation.main.fragment.home.matchdetail.tabs.StatsFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MatchDetailFragment : Fragment() {

    private var _binding: FragmentMatchDetailBinding? = null
    private val binding get() = _binding!!

    private val matchViewModel: MatchViewModel by activityViewModels{
        MatchViewModel.Factory(MatchRepository(apiService))
    }

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchDetailBinding.inflate(inflater, container, false)

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_matchDetailFragment_to_mainFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = binding.matchDetailTabLayout
        viewPager = binding.matchDetailViewPager

        val adapter = MatchDetailPagerAdapter(this)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 2

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Stats"
                1 -> tab.text = "Lineup"
            }
        }.attach()

        matchViewModel.idEventRemember.observe(viewLifecycleOwner, Observer { idEvent ->
            if(idEvent != null)
            {
                matchViewModel.fetchMatchDetail(idEvent)
                matchViewModel.fetchMatchLineups(idEvent)
                matchViewModel.fetchMatchStats(idEvent)
                matchViewModel.fetchMatchTimeline(idEvent)
                matchViewModel.fetchMatchHighlights(idEvent)
            }
            else
            {
                Log.e("MatchDetailFragment", "idEventRemember is null")
            }
        })

        matchViewModel.matchDetail.observe(viewLifecycleOwner, Observer { matchList ->
            displayMatchDetail(matchList[0])
        })
    }

    private fun displayMatchDetail(match: LEAGUEMATCH)
    {
        binding.apply {
            homeTeamBadge.load(match.strHomeTeamBadge)
            awayTeamBadge.load(match.strAwayTeamBadge)
            intHomeScore.text = match.intHomeScore
            intAwayScore.text = match.intAwayScore
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}