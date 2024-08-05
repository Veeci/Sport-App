package com.example.sportapp.presentation.main.fragment.home.matchdetail.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportapp.R
import com.example.sportapp.data.api.apiService
import com.example.sportapp.data.model.STATS
import com.example.sportapp.data.repository.MatchRepository
import com.example.sportapp.databinding.FragmentStatsBinding
import com.example.sportapp.domain.MatchViewModel
import com.example.sportapp.presentation.main.adapter.StatAdapter

class StatsFragment : Fragment() {

    private var _binding: FragmentStatsBinding? = null
    private val binding get() = _binding!!

    private val matchViewModel: MatchViewModel by activityViewModels {
        MatchViewModel.Factory(MatchRepository(apiService))
    }

    private lateinit var statAdapter: StatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatsBinding.inflate(inflater, container, false)

        statAdapter = StatAdapter { onStatClick(it) }

        binding.matchStatsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = statAdapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        matchViewModel.matchStats.observe(viewLifecycleOwner, Observer { stats ->
            if(stats.isNotEmpty())
            {
                statAdapter.updateStats(stats)
            }
            else
            {
                statAdapter.updateStats(emptyList())
            }
        })

        matchViewModel.fetchMatchStats(matchViewModel.idEventRemember.value.toString())
    }

    private fun onStatClick(stat: STATS) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}