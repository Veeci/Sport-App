package com.example.sportapp.presentation.main.fragment.explore.leaguelist.league.tabs.table.team

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.sportapp.R
import com.example.sportapp.data.api.APIService
import com.example.sportapp.data.api.apiService
import com.example.sportapp.data.model.TEAM
import com.example.sportapp.data.repository.TeamRepository
import com.example.sportapp.databinding.FragmentTeamBinding
import com.example.sportapp.domain.TeamViewModel
import com.example.sportapp.presentation.main.fragment.explore.leaguelist.league.tabs.table.team.tabs.TeamDetailPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TeamFragment : Fragment() {

    private var _binding: FragmentTeamBinding? = null
    val binding get() = _binding!!

    private val teamViewModel: TeamViewModel by activityViewModels {
        TeamViewModel.Factory(TeamRepository(apiService))
    }

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentTeamBinding.inflate(inflater, container, false)

        observeViewModel()

        return binding.root
    }

    private fun observeViewModel()
    {
        teamViewModel.idTeam.observe(viewLifecycleOwner, Observer { idTeam ->
            teamViewModel.fetchTeamDetail(idTeam)
        })

        teamViewModel.teamDetail.observe(viewLifecycleOwner, Observer { team ->
            displayTeamDetail(team)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = binding.teamDetailTabLayout
        viewPager = binding.teamDetailViewPager

        val adapter = TeamDetailPagerAdapter(this)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 2

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Detail"
                1 -> tab.text = "Squad"
            }
        }.attach()
    }

    private fun displayTeamDetail(team: List<TEAM>)
    {
        val team = team[0]
        binding.apply {
            strTeamShort.text = team.strTeam
            if(team.strFanart2.isNotEmpty())
            {
                strFanart2.load(team.strFanart2)
            }
            else
            {
                strFanart2.visibility = View.GONE
            }
            val color = if (team.strColour1.isNotEmpty()) {
                Color.parseColor(team.strColour1)
            } else {
                Color.GRAY
            }
            if(color.equals(Color.WHITE))
            {
                strTeamShort.setTextColor(ContextCompat.getColor(requireContext(), R.color.theme_main))
                strTeam.setTextColor(ContextCompat.getColor(requireContext(), R.color.theme_main))
                intFormedYear.setTextColor(ContextCompat.getColor(requireContext(), R.color.theme_main))
                intStadiumCapacity.setTextColor(ContextCompat.getColor(requireContext(), R.color.theme_main))
            }
            topBar.setBackgroundColor(color)
            strTeamContainer.setBackgroundColor(color)
            strBadge.load(team.strBadge)
            strTeam.text = team.strTeam
            intFormedYear.text = "Est: ${team.intFormedYear} - ${team.strLocation}"
            intStadiumCapacity.text = "Capacity: ${team.intStadiumCapacity}"

            back.setOnClickListener {
                findNavController().navigate(R.id.action_teamFragment_to_leagueFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}