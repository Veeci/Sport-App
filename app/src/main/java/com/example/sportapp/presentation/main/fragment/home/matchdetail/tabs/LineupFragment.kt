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
import com.example.sportapp.data.model.LINEUP
import com.example.sportapp.data.repository.MatchRepository
import com.example.sportapp.databinding.FragmentLineupBinding
import com.example.sportapp.domain.MatchViewModel
import com.example.sportapp.presentation.main.adapter.LineupAdapter

class LineupFragment : Fragment() {

    private var _binding: FragmentLineupBinding? = null
    private val binding get() = _binding!!

    private val matchViewModel: MatchViewModel by activityViewModels {
        MatchViewModel.Factory(MatchRepository(apiService))
    }

    private lateinit var homeLineupAdapter: LineupAdapter
    private lateinit var awayLineupAdapter: LineupAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLineupBinding.inflate(inflater, container, false)

        homeLineupAdapter = LineupAdapter { lineup -> onLineupClick(lineup) }
        awayLineupAdapter = LineupAdapter { lineup -> onLineupClick(lineup) }

        binding.homeLineupRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeLineupAdapter
        }

        binding.awayLineupRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = awayLineupAdapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        matchViewModel.loading.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        matchViewModel.matchDetailLineups.observe(viewLifecycleOwner, Observer { lineup ->
            if(lineup.isNotEmpty())
            {
                homeLineupAdapter.updateLineups(lineup.take(11))
                awayLineupAdapter.updateLineups(lineup.takeLast(11))
            }
            else
            {
                homeLineupAdapter.updateLineups(emptyList())
                awayLineupAdapter.updateLineups(emptyList())
            }
        })

        matchViewModel.fetchMatchLineups(matchViewModel.idEventRemember.value.toString())
    }

    private fun onLineupClick(lineup: LINEUP)
    {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}