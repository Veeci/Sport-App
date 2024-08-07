package com.example.sportapp.presentation.main.fragment.explore.tabs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportapp.R
import com.example.sportapp.data.api.apiService
import com.example.sportapp.data.repository.LeagueRepository
import com.example.sportapp.databinding.FragmentTableBinding
import com.example.sportapp.domain.LeagueTableViewModel
import com.example.sportapp.domain.LeagueViewModel
import com.example.sportapp.presentation.main.adapter.TableAdapter

class TableFragment : Fragment() {

    private var _binding: FragmentTableBinding? = null
    private val binding get() = _binding!!

    private val leagueTableViewModel: LeagueTableViewModel by activityViewModels {
        LeagueTableViewModel.Factory(LeagueRepository(apiService))
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
        tableAdapter = TableAdapter()

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}