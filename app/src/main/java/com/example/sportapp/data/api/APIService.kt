package com.example.sportapp.data.api

import com.example.sportapp.data.model.CompetitionLeaguesResponse
import com.example.sportapp.data.model.CountryRespond
import com.example.sportapp.data.model.EquipmentResponse
import com.example.sportapp.data.model.HighlightRespond
import com.example.sportapp.data.model.LeagueDetailResponse
import com.example.sportapp.data.model.LeagueMatchDetailResponse
import com.example.sportapp.data.model.LeagueMatchesResponse
import com.example.sportapp.data.model.LeagueTableResponse
import com.example.sportapp.data.model.LeaguesResponse
import com.example.sportapp.data.model.LineupRespond
import com.example.sportapp.data.model.StatsRespond
import com.example.sportapp.data.model.TeamResponse
import com.example.sportapp.data.model.TimelineRespond
import com.example.sportapp.data.model.VenueResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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

    @GET("/api/v2/json/3/lookup/event_timeline/{idEvent}")
    suspend fun getMatchTimeline(@Path("idEvent") idEvent: String): TimelineRespond

    @GET("/api/v2/json/3/lookup/event_highlights/{idEvent}")
    suspend fun getMatchHighlights(@Path("idEvent") idEvent: String): HighlightRespond
    //----------------------------------------------------------------------------------------------

    //APIs of Explore screen-----------------------------------------------------------------------
    @GET("/api/v1/json/3/all_countries.php")
    suspend fun getAllCountries(): CountryRespond

    @GET("/api/v1/json/3/search_all_leagues.php")
    suspend fun getCompetitionsByCountry(@Query("c") name_en: String): CompetitionLeaguesResponse

    @GET("/api/v2/json/3/lookup/league/{idLeague}")
    suspend fun getLeagueDetail(@Path("idLeague") idLeague: String): LeagueDetailResponse

    @GET("/api/v1/json/3/lookuptable.php")
    suspend fun getLeagueTable(
        @Query("l") idLeague: String,
        @Query("s") strCurrentSeason: String
    ): LeagueTableResponse

    @GET("/api/v2/json/3/lookup/team/{idTeam}")
    suspend fun getTeamDetail(@Path("idTeam") idTeam: String): TeamResponse

    @GET("/api/v2/json/3/lookup/venue/{idVenue}")
    suspend fun getVenue(@Path("idVenue") idVenue: String): VenueResponse

    @GET("/api/v1/json/3/lookupequipment.php")
    suspend fun getEquipment(@Query("id") idTeam: String): EquipmentResponse
    //----------------------------------------------------------------------------------------------
}

val apiService: APIService = retrofit.create(APIService::class.java)