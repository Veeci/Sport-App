package com.example.sportapp.data.repository

import com.example.sportapp.data.api.APIService
import com.example.sportapp.data.model.CompetitionRespond
import com.example.sportapp.data.model.LeaguesResponse

class CompetitionRepository(private val apiService: APIService)
{
    suspend fun getAllCompetitions(): CompetitionRespond
    {
        return apiService.getAllCompetitions()
    }

}