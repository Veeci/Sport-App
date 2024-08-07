package com.example.sportapp.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class LEAGUETABLE(
    @SerializedName("idStanding") val idStanding: String = "",
    @SerializedName("intRank") val intRank: String = "",
    @SerializedName("idTeam") val idTeam: String = "",
    @SerializedName("strTeam") val strTeam: String = "",
    @SerializedName("strBadge") val strBadge: String = "",
    @SerializedName("idLeague") val idLeague: String = "",
    @SerializedName("strLeague") val strLeague: String = "",
    @SerializedName("strSeason") val strSeason: String = "",
    @SerializedName("strForm") val strForm: String = "",
    @SerializedName("strDescription") val strDescription: String = "",
    @SerializedName("intPlayed") val intPlayed: String = "",
    @SerializedName("intWin") val intWin: String = "",
    @SerializedName("intLoss") val intLoss: String = "",
    @SerializedName("intDraw") val intDraw: String = "",
    @SerializedName("intGoalsFor") val intGoalsFor: String = "",
    @SerializedName("intGoalsAgainst") val intGoalsAgainst: String = "",
    @SerializedName("intGoalDifference") val intGoalDifference: String = "",
    @SerializedName("intPoints") val intPoints: String = "",
    @SerializedName("dateUpdated") val dateUpdated: String = ""
) : Parcelable

data class LeagueTableResponse(
    @SerializedName("table") val table: List<LEAGUETABLE>
)