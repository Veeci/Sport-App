package com.example.sportapp.presentation.main.fragment.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportapp.R
import com.example.sportapp.data.api.apiService
import com.example.sportapp.data.model.LEAGUE
import com.example.sportapp.data.model.LEAGUEMATCH
import com.example.sportapp.data.repository.LeagueRepository
import com.example.sportapp.data.repository.MatchRepository
import com.example.sportapp.databinding.FragmentHomeBinding
import com.example.sportapp.domain.LeagueViewModel
import com.example.sportapp.domain.MatchViewModel
import com.example.sportapp.presentation.main.adapter.LeagueAdapter
import com.example.sportapp.presentation.main.adapter.MatchAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val leagueViewModel: LeagueViewModel by viewModels {
        LeagueViewModel.Factory(LeagueRepository(apiService))
    }

    private val matchViewModel: MatchViewModel by activityViewModels {
        MatchViewModel.Factory(MatchRepository(apiService))
    }

    private lateinit var leaguePrevAdapter: LeagueAdapter
    private lateinit var leagueNextAdapter: LeagueAdapter
    private lateinit var matchPrevAdapter: MatchAdapter
    private lateinit var matchNextAdapter: MatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViews(view)
        observeViewModels()

        leagueViewModel.fetchAllLeagues()
    }

    private fun setupRecyclerViews(view: View) {
        leaguePrevAdapter = LeagueAdapter { onLeagueClickPrev(it) }
        leagueNextAdapter = LeagueAdapter { onLeagueClickNext(it) }

        matchPrevAdapter = MatchAdapter {onMatchClickPrev(it)}
        matchNextAdapter = MatchAdapter{ onMatchClickNext(it) }

        binding.leaguesPrev.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = leaguePrevAdapter
        }

        binding.leaguesNext.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = leagueNextAdapter
        }

        binding.matchesPrevContainer.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = matchPrevAdapter
        }

        binding.matchesNextContainer.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = matchNextAdapter
        }
    }

    private fun observeViewModels() {
        leagueViewModel.leagues.observe(viewLifecycleOwner, Observer { leagues ->
            displayLeagues(leagues)
        })

        matchViewModel.matchesPrev.observe(viewLifecycleOwner, Observer { matches ->
            matchPrevAdapter.updateMatches(matches)
        })

        matchViewModel.matchesNext.observe(viewLifecycleOwner, Observer { matches ->
            matchNextAdapter.updateMatches(matches)
        })
    }

    private fun displayLeagues(leagues: List<LEAGUE>) {
        leaguePrevAdapter.updateLeagues(leagues)
        leagueNextAdapter.updateLeagues(leagues)

        if (leagues.isNotEmpty()) {
            onLeagueClickPrev(leagues[0])
            onLeagueClickNext(leagues[0])
        }
    }

    private fun onLeagueClickPrev(league: LEAGUE) {
        matchViewModel.fetchLeagueMatchesPrevious(league.idLeague)
    }

    private fun onLeagueClickNext(league: LEAGUE) {
        matchViewModel.fetchLeagueMatchesNext(league.idLeague)
    }

    private fun onMatchClickPrev(match: LEAGUEMATCH)
    {
        matchViewModel.setIdEventRemember(match.idEvent)
        findNavController().navigate(R.id.action_mainFragment_to_matchDetailFragment)
    }

    private fun onMatchClickNext(match: LEAGUEMATCH)
    {
        Toast.makeText(context, "This match result will be updated soon", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
