package com.example.sportapp.data.repository

import com.example.sportapp.data.api.APIService
import com.example.sportapp.data.model.LeagueDetailResponse
import com.example.sportapp.data.model.LeaguesResponse

class LeagueRepository(private val apiService: APIService)
{
    suspend fun getAllLeagues(): LeaguesResponse
    {
        return apiService.getAllLeagues()
    }

    suspend fun getLeagueDetail(idLeague: String): LeagueDetailResponse
    {
        return apiService.getLeagueDetail(idLeague)
    }
}