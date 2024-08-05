package com.example.sportapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LINEUP(
    @SerializedName("idLineup") val idLineup: String = "",
    @SerializedName("idEvent") val idEvent: String = "",
    @SerializedName("strEvent") val strEvent: String = "",
    @SerializedName("strPosition") val strPosition: String = "",
    @SerializedName("strPositionShort") val strPositionShort: String = "",
    @SerializedName("strFormation") val strFormation: String? = null,
    @SerializedName("strHome") val strHome: String = "",
    @SerializedName("strSubstitute") val strSubstitute: String = "",
    @SerializedName("intSquadNumber") val intSquadNumber: String = "",
    @SerializedName("strCutout") val strCutout: String = "",
    @SerializedName("idPlayer") val idPlayer: String = "",
    @SerializedName("strPlayer") val strPlayer: String = "",
    @SerializedName("idTeam") val idTeam: String = "",
    @SerializedName("strTeam") val strTeam: String = "",
    @SerializedName("strCountry") val strCountry: String = "",
    @SerializedName("strSeason") val strSeason: String = ""
): Parcelable

data class LineupRespond(
    @SerializedName("lineups") val lineups: List<LINEUP>
)