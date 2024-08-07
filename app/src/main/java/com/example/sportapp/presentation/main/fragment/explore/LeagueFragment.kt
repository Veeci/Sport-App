package com.example.sportapp.presentation.main.fragment.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.sportapp.R
import com.example.sportapp.data.api.apiService
import com.example.sportapp.data.model.LEAGUEDETAIL
import com.example.sportapp.data.repository.LeagueRepository
import com.example.sportapp.databinding.FragmentLeagueBinding
import com.example.sportapp.domain.LeagueViewModel

class LeagueFragment : Fragment() {

    private var _binding: FragmentLeagueBinding? = null
    private val binding get() = _binding!!

    private val leagueViewModel: LeagueViewModel by activityViewModels {
        LeagueViewModel.Factory(LeagueRepository(apiService))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLeagueBinding.inflate(layoutInflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel()
    {
        leagueViewModel.idLeagueRemember.observe(viewLifecycleOwner, Observer { idLeague ->
            if(idLeague != null)
            {
                leagueViewModel.fetchLeagueDetail(idLeague)
            }
        })

        leagueViewModel.leagueDetail.observe(viewLifecycleOwner, Observer { leagues ->
            displayLeagueDetail(leagues[0])
        })

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_leagueFragment_to_leagueListFragment)
        }
    }

    private fun displayLeagueDetail(league: LEAGUEDETAIL)
    {
        binding.apply {
            strCurrentSeason.text = league.strCurrentSeason
            if(league.strBanner != null)
            {
                strBanner.load(league.strBanner)
            }
            else
            {
                strBanner.visibility = View.GONE
                strPoster.load(league.strPoster)
                strPoster.visibility = View.VISIBLE
            }
            strLeagueDetail.text = league.strLeague
            strCountryDetail.text = league.strCountry
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}