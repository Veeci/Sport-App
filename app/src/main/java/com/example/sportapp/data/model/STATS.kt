package com.example.sportapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class STATS(
    @SerializedName("idStatistic") val idStatistic: String = "",
    @SerializedName("idEvent") val idEvent: String = "",
    @SerializedName("idApiFootball") val idApiFootball: String = "",
    @SerializedName("strEvent") val strEvent: String = "",
    @SerializedName("strStat") val strStat: String = "",
    @SerializedName("intHome") val intHome: String = "",
    @SerializedName("intAway") val intAway: String = ""
): Parcelable

data class StatsRespond(
    @SerializedName("event_stats") val eventStats: List<STATS>
)