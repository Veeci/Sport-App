package com.example.sportapp.presentation.main.fragment.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportapp.R
import com.example.sportapp.data.api.apiService
import com.example.sportapp.data.model.COUNTRY
import com.example.sportapp.data.repository.CompetitionLeagueRepository
import com.example.sportapp.data.repository.CountryRepository
import com.example.sportapp.databinding.FragmentExploreBinding
import com.example.sportapp.domain.CompetitionLeagueViewModel
import com.example.sportapp.domain.CountryViewModel
import com.example.sportapp.presentation.main.adapter.CountryAdapter

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    private val countryViewModel: CountryViewModel by activityViewModels {
        CountryViewModel.Factory(CountryRepository(apiService))
    }

    private val competitionLeagueViewModel: CompetitionLeagueViewModel by activityViewModels {
        CompetitionLeagueViewModel.Factory(CompetitionLeagueRepository(apiService))
    }

    private lateinit var countryAdapter: CountryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)

        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        countryAdapter = CountryAdapter { onCompetitionClick(it) }
        binding.countriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countryAdapter
        }
    }

    private fun onCompetitionClick(country: COUNTRY) {
        competitionLeagueViewModel.setNameEnRemember(country.name_en)
        findNavController().navigate(R.id.action_mainFragment_to_leagueListFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        countryViewModel.countries.observe(viewLifecycleOwner) { countries ->
            countryAdapter.updateCountries(countries)
        }

        countryViewModel.fetchAllCountries()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}