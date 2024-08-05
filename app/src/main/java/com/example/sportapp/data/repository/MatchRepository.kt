package com.example.sportapp.data.repository

import com.example.sportapp.data.api.APIService

class MatchRepository(private val apiService: APIService)
{
    suspend fun getLeagueMatchesPrevious(idLeague: String) = apiService.getLeagueMatchesPrevious(idLeague)
    suspend fun getLeagueMatchesNext(idLeague: String) = apiService.getLeagueMatchesNext(idLeague)
    suspend fun getMatchDetail(idEvent: String) = apiService.getMatchDetail(idEvent)
}