package com.example.sportapp.data.repository

import android.util.Log
import com.example.sportapp.data.api.APIService
import com.example.sportapp.data.model.LeagueDetailResponse
import com.example.sportapp.data.model.LeagueTableResponse
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

    suspend fun getLeagueTable(idLeague: String, strCurrentSeason: String): LeagueTableResponse
    {
        val url = "https://www.thesportsdb.com/api/v1/json/3/lookuptable.php?l=$idLeague&s=$strCurrentSeason"
        Log.d("LeagueRepository", "Request URL: $url")
        return apiService.getLeagueTable(idLeague, strCurrentSeason)
    }
}