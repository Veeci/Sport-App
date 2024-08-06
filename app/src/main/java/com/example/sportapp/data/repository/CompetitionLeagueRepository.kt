package com.example.sportapp.data.repository

import com.example.sportapp.data.api.APIService

class CompetitionLeagueRepository(private val apiService: APIService)
{
    suspend fun getCompetitionsByCountry(name_en: String) = apiService.getCompetitionsByCountry(name_en)
}