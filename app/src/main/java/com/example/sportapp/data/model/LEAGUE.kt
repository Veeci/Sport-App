package com.example.sportapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LEAGUE(
    @SerializedName("idLeague") val idLeague: String = "",
    @SerializedName("strLeague") val strLeague: String = "",
    @SerializedName("strSport") val strSport: String = "",
    @SerializedName("strLeagueAlternate") val strLeagueAlternate: String = ""
): Parcelable

data class LeaguesResponse(
    @SerializedName("leagues") val leagues: List<LEAGUE>
)