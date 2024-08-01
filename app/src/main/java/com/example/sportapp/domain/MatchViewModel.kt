package com.example.sportapp.domain

import androidx.lifecycle.ViewModel
import com.example.sportapp.data.model.LEAGUEMATCH_PREVIOUS

class MatchViewModel: ViewModel()
{
    data class LeagueMatchPreviousState(
        val isLoading: Boolean = true,
        val list: List<LEAGUEMATCH_PREVIOUS> = emptyList(),
        val error: String? = null
    )


}