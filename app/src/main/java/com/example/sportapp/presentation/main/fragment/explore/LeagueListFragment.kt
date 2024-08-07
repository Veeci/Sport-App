package com.example.sportapp.presentation.main.fragment.explore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportapp.R
import com.example.sportapp.data.api.apiService
import com.example.sportapp.data.model.COMPETITIONLEAGUE
import com.example.sportapp.data.repository.CompetitionLeagueRepository
import com.example.sportapp.data.repository.LeagueRepository
import com.example.sportapp.databinding.FragmentLeagueListBinding
import com.example.sportapp.domain.CompetitionLeagueViewModel
import com.example.sportapp.domain.LeagueTableViewModel
import com.example.sportapp.domain.LeagueViewModel
import com.example.sportapp.presentation.main.adapter.CompetitionLeagueAdapter
import kotlinx.coroutines.awaitAll

class LeagueListFragment : Fragment() {

    private var _binding: FragmentLeagueListBinding?= null
    private val binding get() = _binding!!

    private val competitionLeagueViewModel: CompetitionLeagueViewModel by activityViewModels {
        CompetitionLeagueViewModel.Factory(CompetitionLeagueRepository(apiService))
    }

    private val leagueViewModel: LeagueViewModel by activityViewModels {
        LeagueViewModel.Factory(LeagueRepository(apiService))
    }

    private val leagueTableViewModel: LeagueTableViewModel by activityViewModels {
        LeagueTableViewModel.Factory(LeagueRepository(apiService))
    }

    private lateinit var competitionLeagueAdapter: CompetitionLeagueAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLeagueListBinding.inflate(layoutInflater, container, false)

        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        competitionLeagueAdapter = CompetitionLeagueAdapter { onCompetitionLeagueClick(it) }
        binding.leagueListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = competitionLeagueAdapter
        }

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_leagueListFragment_to_mainFragment)
        }
    }

    private fun onCompetitionLeagueClick(competitionLeague: COMPETITIONLEAGUE) {
        leagueViewModel.setIdLeagueRemember(competitionLeague.idLeague)
        competitionLeague.strCurrentSeason?.let {
            leagueTableViewModel.setIdLeagueSeasonRemember(competitionLeague.idLeague, it)
        }
        findNavController().navigate(R.id.action_leagueListFragment_to_leagueFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        competitionLeagueViewModel.nameEnRemember.observe(viewLifecycleOwner, Observer { nameEn ->
            if(nameEn != null) {
                competitionLeagueViewModel.fetchCompetitionsByCountry(nameEn)
            }
        })

        competitionLeagueViewModel.competitionLeagues.observe(viewLifecycleOwner, Observer { competitionLeagues ->
            if(competitionLeagues.isEmpty())
            {
                Toast.makeText(context, "This country/competition will be updated soon", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_leagueListFragment_to_mainFragment)
            }
            else
            {
                competitionLeagueAdapter.updateCompetitionLeagues(competitionLeagues)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}