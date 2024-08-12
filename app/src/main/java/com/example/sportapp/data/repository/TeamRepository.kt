package com.example.sportapp.data.repository

import com.example.sportapp.data.api.APIService

class TeamRepository(private val apiService: APIService)
{
    suspend fun getTeamDetail(idTeam: String) = apiService.getTeamDetail(idTeam)
    suspend fun getTeamVenue(idVenue: String) = apiService.getVenue(idVenue)
    suspend fun getEquipment(idTeam: String) = apiService.getEquipment(idTeam)
}