package com.example.sportapp.presentation.main.fragment.explore.leaguelist.league.tabs.table

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportapp.R
import com.example.sportapp.data.api.apiService
import com.example.sportapp.data.repository.LeagueRepository
import com.example.sportapp.data.repository.TeamRepository
import com.example.sportapp.databinding.FragmentTableBinding
import com.example.sportapp.domain.LeagueTableViewModel
import com.example.sportapp.domain.TeamViewModel
import com.example.sportapp.presentation.main.adapter.TableAdapter

class TableFragment : Fragment() {

    private var _binding: FragmentTableBinding? = null
    private val binding get() = _binding!!

    private val leagueTableViewModel: LeagueTableViewModel by activityViewModels {
        LeagueTableViewModel.Factory(LeagueRepository(apiService))
    }

    private val teamViewModel: TeamViewModel by activityViewModels {
        TeamViewModel.Factory(TeamRepository(apiService))
    }

    private lateinit var tableAdapter: TableAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTableBinding.inflate(layoutInflater, container, false)

        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView()
    {
        tableAdapter = TableAdapter{ onTeamClick(it) }

        binding.leagueTableRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tableAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel()
    {
        leagueTableViewModel.idLeagueRemember.observe(viewLifecycleOwner, Observer { idLeague ->
            leagueTableViewModel.strCurrentSeasonRemember.observe(viewLifecycleOwner, Observer { strCurrentSeason ->
                leagueTableViewModel.fetchLeagueTable(idLeague, strCurrentSeason)
            })
        })

        leagueTableViewModel.leagueTable.observe(viewLifecycleOwner, Observer { leagueTable ->
            Log.d("TableFragment", "League table updated: $leagueTable")
            tableAdapter.updateTables(leagueTable)
        })
    }

    private fun onTeamClick(idTeam: String)
    {
        teamViewModel.setIdTeamRemember(idTeam)
        findNavController().navigate(R.id.action_leagueFragment_to_teamFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}