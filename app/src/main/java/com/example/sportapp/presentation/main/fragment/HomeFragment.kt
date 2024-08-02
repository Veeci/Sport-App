package com.example.sportapp.presentation.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportapp.data.api.apiService
import com.example.sportapp.data.model.LEAGUE
import com.example.sportapp.data.repository.LeagueRepository
import com.example.sportapp.data.repository.MatchRepository
import com.example.sportapp.databinding.FragmentHomeBinding
import com.example.sportapp.domain.LeagueViewModel
import com.example.sportapp.domain.MatchViewModel
import com.example.sportapp.presentation.main.adapter.LeagueAdapter
import com.example.sportapp.presentation.main.adapter.MatchAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding?= null
    private val binding get() = _binding!!

    private val leagueViewModeL: LeagueViewModel by viewModels{
        LeagueViewModel.Factory(LeagueRepository(apiService))
    }

    private val matchViewModel: MatchViewModel by viewModels{
        MatchViewModel.Factory(MatchRepository(apiService))
    }

    private lateinit var leagueAdapterPrev: LeagueAdapter
    private lateinit var leagueAdapterNext: LeagueAdapter
    private lateinit var matchAdapterPrev: MatchAdapter
    private lateinit var matchAdapterNext: MatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        //Observe the leagues and matches LiveData in the ViewModel
        leagueViewModeL.leagues.observe(viewLifecycleOwner, Observer { leagues ->
            displayLeagues(leagues)
        })

        matchViewModel.matchesPrev.observe(viewLifecycleOwner, Observer { matches ->
            matchAdapterPrev.updateMatches(matches)
        })

        matchViewModel.matchesNext.observe(viewLifecycleOwner, Observer { matches ->
            matchAdapterNext.updateMatches(matches)
        })

        //Fetch all leagues from the API
        leagueViewModeL.fetchAllLeagues()
    }

    //Set up the recycler view to display the leagues and matches (as a horizontal list)
    private fun setupRecyclerView()
    {
        leagueAdapter = LeagueAdapter { league ->
            onLeagueClick(league)
        }

        matchAdapter = MatchAdapter()

        binding.leagues.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = leagueAdapter
        }

        binding.matchesPrevContainer.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = matchAdapter
        }

        binding.matchesNextContainer.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = matchAdapter
        }
    }

    //Initialize the LeagueAdapter with a league as List type and the onClick function as parameters
    private fun displayLeagues(leagues: List<LEAGUE>)
    {
        leagueAdapter.updateLeagues(leagues)
        if(leagues.isNotEmpty())
        {
            onLeagueClick(leagues[0])
        }
    }

    private fun onLeagueClick(league: LEAGUE)
    {
        matchViewModel.fetchLeagueMatchesPrevious(league.idLeague)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}