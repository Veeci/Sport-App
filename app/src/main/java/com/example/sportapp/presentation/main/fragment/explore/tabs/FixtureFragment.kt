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
import com.example.sportapp.data.repository.MatchRepository
import com.example.sportapp.databinding.FragmentFixtureBinding
import com.example.sportapp.domain.MatchViewModel
import com.example.sportapp.presentation.main.adapter.SchedualAdapter

class FixtureFragment : Fragment() {

    private var _binding: FragmentFixtureBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MatchViewModel by activityViewModels{
        MatchViewModel.Factory(MatchRepository(apiService))
    }

    private lateinit var schedualAdapter: SchedualAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFixtureBinding.inflate(layoutInflater, container, false)

        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView()
    {
        schedualAdapter = SchedualAdapter()

        binding.schedualRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = schedualAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.idLeagueRemember.observe(viewLifecycleOwner, Observer { idLeague ->
            if (idLeague != null) {
                Log.d("FixtureFragment", "Fetching league matches for league ID: $idLeague")
                viewModel.fetchLeagueMatchesNext(idLeague)
            } else {
                Log.d("FixtureFragment", "League ID is null")
            }
        })

        viewModel.matchesNext.observe(viewLifecycleOwner, Observer { matches ->
            Log.d("FixtureFragment", "Matches received: $matches")
            schedualAdapter.updateScheduals(matches?: listOf())
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}