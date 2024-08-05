package com.example.sportapp.presentation.main.fragment.home.matchdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.sportapp.R
import com.example.sportapp.data.api.apiService
import com.example.sportapp.data.model.LEAGUEMATCH
import com.example.sportapp.data.repository.MatchRepository
import com.example.sportapp.databinding.FragmentMatchDetailBinding
import com.example.sportapp.domain.MatchViewModel
import com.example.sportapp.presentation.ViewPagerAdapter


class MatchDetailFragment : Fragment() {

    private var _binding: FragmentMatchDetailBinding? = null
    private val binding get() = _binding!!

    private val matchViewModel: MatchViewModel by viewModels{
        MatchViewModel.Factory(MatchRepository(apiService))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMatchDetailBinding.inflate(inflater, container, false)

        val fragmentList = arrayListOf(
            StatsFragment(),
            LineupFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.machDetailViewPager.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        matchViewModel.idEventRemember.observe(viewLifecycleOwner, Observer { idEvent ->
            if(idEvent != null)
            {
                matchViewModel.fetchMatchDetail(idEvent)
            }
            else
            {
                Log.e("MatchDetailFragment", "idEventRemember is null")
            }
        })

        matchViewModel.matchDetail.observe(viewLifecycleOwner, Observer { match ->
            if(match != null)
            {
                displayMatchDetail(match)
            }
            else
            {
                Log.e("MatchDetailFragment", "matchDetail is null")
            }
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