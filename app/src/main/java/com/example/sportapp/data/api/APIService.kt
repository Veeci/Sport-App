package com.example.sportapp.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private val retrofit = Retrofit.Builder().baseUrl("https://www.thesportsdb.com/").addConverterFactory(GsonConverterFactory.create()).build()

interface APIService
{

}

val apiService: APIService = retrofit.create(APIService::class.java)