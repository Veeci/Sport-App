package com.example.sportapp.presentation.main.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.sportapp.R
import com.example.sportapp.data.api.apiService
import com.example.sportapp.data.model.LEAGUE
import com.example.sportapp.data.repository.LeagueRepository
import com.example.sportapp.databinding.FragmentHomeBinding
import com.example.sportapp.domain.LeagueViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding?= null
    private val binding get() = _binding!!

    private val leagueViewModeL: LeagueViewModel by viewModels{
        LeagueViewModel.Factory(LeagueRepository(apiService))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        leagueViewModeL.leagues.observe(viewLifecycleOwner, Observer { leagues ->
            displayLeagues(leagues)
        })

        leagueViewModeL.fetchAllLeagues()
    }

    @SuppressLint("ResourceAsColor")
    private fun displayLeagues(leagues: List<LEAGUE>)
    {
        val leagueContainer1 = binding.leagueContainer1
        leagueContainer1.removeAllViews()

        val leagueContainer2 = binding.leagueContainer2
        leagueContainer2.removeAllViews()

        for(league in leagues)
        {
            val textView1 = TextView(context).apply {
                text = league.strLeague
                textSize = 16f
                setPadding(8, 8, 8, 8)
                setTextColor(resources.getColor(R.color.text_main, resources.newTheme()))
            }

            val textView2 = TextView(context).apply {
                text = league.strLeague
                textSize = 16f
                setPadding(8, 8, 8, 8)
                setTextColor(resources.getColor(R.color.text_main, resources.newTheme()))
            }
            leagueContainer1.addView(textView1)
            leagueContainer2.addView(textView2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}