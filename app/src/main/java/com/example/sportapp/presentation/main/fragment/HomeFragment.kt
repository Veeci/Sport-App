package com.example.sportapp.presentation.main.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportapp.R
import com.example.sportapp.data.api.apiService
import com.example.sportapp.data.model.LEAGUE
import com.example.sportapp.data.repository.LeagueRepository
import com.example.sportapp.databinding.FragmentHomeBinding
import com.example.sportapp.domain.LeagueViewModel
import com.example.sportapp.presentation.main.adapter.LeagueAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding?= null
    private val binding get() = _binding!!

    private val leagueViewModeL: LeagueViewModel by viewModels{
        LeagueViewModel.Factory(LeagueRepository(apiService))
    }

    private lateinit var leagueAdapter: LeagueAdapter

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

        leagueViewModeL.leagues.observe(viewLifecycleOwner, Observer { leagues ->
            displayLeagues(leagues)
        })

        leagueViewModeL.fetchAllLeagues()
    }

    private fun setupRecyclerView()
    {
        leagueAdapter = LeagueAdapter { league ->
            onLeagueClick(league)
        }

        binding.leagueMatchesPrevRV.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = leagueAdapter
        }

        binding.leagueMatchesNextRV.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = leagueAdapter
        }
    }

    private fun onLeagueClick(league: LEAGUE)
    {

    }

    private fun displayLeagues(leagues: List<LEAGUE>)
    {
        leagueAdapter.updateLeagues(leagues)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}