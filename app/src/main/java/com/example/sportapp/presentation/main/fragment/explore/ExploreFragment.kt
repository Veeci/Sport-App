package com.example.sportapp.presentation.main.fragment.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportapp.R
import com.example.sportapp.data.api.apiService
import com.example.sportapp.data.model.COMPETITION
import com.example.sportapp.data.repository.CompetitionRepository
import com.example.sportapp.databinding.FragmentExploreBinding
import com.example.sportapp.domain.CompetitionViewModel
import com.example.sportapp.presentation.main.adapter.CompetitionAdapter

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    private val competitionViewModel: CompetitionViewModel by activityViewModels {
        CompetitionViewModel.Factory(CompetitionRepository(apiService))
    }

    private lateinit var competitionAdapter: CompetitionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)

        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        competitionAdapter = CompetitionAdapter { onCompetitionClick(it) }
        binding.countriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = competitionAdapter
        }
    }

    private fun onCompetitionClick(competition: COMPETITION) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        competitionViewModel.competitions.observe(viewLifecycleOwner) { competitions ->
            competitionAdapter.updateCompetitions(competitions)
        }

        competitionViewModel.fetchAllCompetitions()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}