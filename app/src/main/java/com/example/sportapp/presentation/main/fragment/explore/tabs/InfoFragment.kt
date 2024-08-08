package com.example.sportapp.presentation.main.fragment.explore.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import coil.load
import com.example.sportapp.R
import com.example.sportapp.data.api.apiService
import com.example.sportapp.data.model.LEAGUEDETAIL
import com.example.sportapp.data.repository.LeagueRepository
import com.example.sportapp.databinding.FragmentInfoBinding
import com.example.sportapp.domain.LeagueViewModel

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    private val leagueViewModel: LeagueViewModel by activityViewModels {
        LeagueViewModel.Factory(LeagueRepository(apiService))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        leagueViewModel.leagueDetail.observe(viewLifecycleOwner, Observer { leagues ->
            displayLeagueDetail(leagues)
        })
    }

    private fun displayLeagueDetail(leagues: List<LEAGUEDETAIL>) {
        for (league in leagues) {
            binding.apply {
                strDescriptionEn.text = league.strDescriptionEN
                if (league.strDescriptionDE != null) {
                    strDescriptionDe.text = league.strDescriptionDE
                    descriptionDe.visibility = View.VISIBLE
                }
                if (league.strDescriptionFR != null) {
                    strDescriptionFr.text = league.strDescriptionFR
                    descriptionFr.visibility = View.VISIBLE
                }
                if (league.strDescriptionIT != null) {
                    strDescriptionIT.text = league.strDescriptionIT
                    descriptionIt.visibility = View.VISIBLE
                }
                if (league.strDescriptionJP != null) {
                    strDescriptionJp.text = league.strDescriptionJP
                    descriptionJp.visibility = View.VISIBLE
                }
                strLeagueAlternate.text = league.strLeagueAlternate
                intFormedYear.text = league.intFormedYear.toString()
                strBadge.load(league.strBadge)
                strLogo.load(league.strLogo)
                strPoster.load(league.strPoster)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
