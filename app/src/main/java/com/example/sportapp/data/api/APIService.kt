package com.example.sportapp.data.api

import com.example.sportapp.data.model.LeagueMatches_NextResponse
import com.example.sportapp.data.model.LeagueMatches_PreviousResponse
import com.example.sportapp.data.model.LeaguesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

private val retrofit = Retrofit.Builder().baseUrl("https://www.thesportsdb.com/").addConverterFactory(GsonConverterFactory.create()).build()

interface APIService
{
    @GET("/api/v1/json/3/all_leagues.php")
    suspend fun getAllLeagues(): LeaguesResponse

    @GET("/api/v2/json/3/schedual/previous/league/{idLeague}")
    suspend fun getLeagueMatchesPrevious(@Path("idLeague") idLeague: String): LeagueMatches_PreviousResponse

    @GET("/api/v2/json/3/schedual/next/league/{idLeague}")
    suspend fun getLeagueMatchesNext(@Path("idLeague") idLeague: String): LeagueMatches_NextResponse
}

val apiService: APIService = retrofit.create(APIService::class.java)