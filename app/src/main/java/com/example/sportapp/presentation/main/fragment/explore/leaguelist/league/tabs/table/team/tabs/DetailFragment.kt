package com.example.sportapp.presentation.main.fragment.explore.leaguelist.league.tabs.table.team.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.sportapp.R
import com.example.sportapp.data.api.apiService
import com.example.sportapp.data.model.EQUIPMENT
import com.example.sportapp.data.model.TEAM
import com.example.sportapp.data.model.VENUE
import com.example.sportapp.data.repository.TeamRepository
import com.example.sportapp.databinding.FragmentDetailBinding
import com.example.sportapp.domain.TeamViewModel
import com.example.sportapp.presentation.main.adapter.EquipmentAdapter

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val teamViewModel: TeamViewModel by activityViewModels {
        TeamViewModel.Factory(TeamRepository(apiService))
    }

    private lateinit var equipmentAdapter: EquipmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)

        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView()
    {
        equipmentAdapter = EquipmentAdapter()

        binding.kitRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = equipmentAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel()
    {
        teamViewModel.teamDetail.observe(viewLifecycleOwner, Observer { team ->
            displayTeamDetail(team)
        })

        teamViewModel.teamVenue.observe(viewLifecycleOwner, Observer { teamVenue ->
            displayTeamVenue(teamVenue)
        })

        teamViewModel.fetchEquipment(teamViewModel.idTeam.value.toString())

        teamViewModel.equipment.observe(viewLifecycleOwner, Observer { equipment ->
            equipmentAdapter.updateEquipments(equipment)
        })
    }

    private fun displayTeamDetail(team: List<TEAM>)
    {
        val team = team[0]
        binding.apply {
            strDescriptionEnTeam.text = team.strDescriptionEN
        }
    }

    private fun displayTeamVenue(venue: List<VENUE>)
    {
        val venue = venue[0]
        binding.apply {
            strLogo.load(venue.strLogo)
            strDescriptionEnVenue.text = venue.strDescriptionEN
            strFanart1.load(venue.strFanart1)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}