package com.example.sportapp.data.repository

import com.example.sportapp.data.api.APIService
import com.example.sportapp.data.model.CountryRespond

class CountryRepository(private val apiService: APIService)
{
    suspend fun getAllCountries(): CountryRespond
    {
        return apiService.getAllCountries()
    }

}