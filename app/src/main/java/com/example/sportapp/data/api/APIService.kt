package com.example.sportapp.data.api

import com.example.sportapp.data.model.LeagueMatchDetailResponse
import com.example.sportapp.data.model.LeagueMatchesResponse
import com.example.sportapp.data.model.LeaguesResponse
import com.example.sportapp.data.model.LineupRespond
import com.example.sportapp.data.model.StatsRespond
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

private val retrofit = Retrofit.Builder().baseUrl("https://www.thesportsdb.com/").addConverterFactory(GsonConverterFactory.create()).build()

interface APIService
{
    //APIs of Home screen---------------------------------------------------------------------------
    @GET("/api/v1/json/3/all_leagues.php")
    suspend fun getAllLeagues(): LeaguesResponse

    @GET("/api/v2/json/3/schedual/previous/league/{idLeague}")
    suspend fun getLeagueMatchesPrevious(@Path("idLeague") idLeague: String): LeagueMatchesResponse

    @GET("/api/v2/json/3/schedual/next/league/{idLeague}")
    suspend fun getLeagueMatchesNext(@Path("idLeague") idLeague: String): LeagueMatchesResponse

    @GET("/api/v2/json/3/lookup/event/{idEvent}")
    suspend fun getMatchDetail(@Path("idEvent") idEvent: String): LeagueMatchDetailResponse

    @GET("/api/v2/json/3/lookup/event_lineup/{idEvent}")
    suspend fun getMatchLineups(@Path("idEvent") idEvent: String): LineupRespond

    @GET("/api/v2/json/3/lookup/event_stats/{idEvent}")
    suspend fun getMatchStats(@Path("idEvent") idEvent: String): StatsRespond
    //----------------------------------------------------------------------------------------------
}

val apiService: APIService = retrofit.create(APIService::class.java)