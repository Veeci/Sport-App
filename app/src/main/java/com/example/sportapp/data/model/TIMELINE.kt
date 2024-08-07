package com.example.sportapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TIMELINE(
    @SerializedName("idTimeline") val idTimeline: String = "",
    @SerializedName("idEvent") val idEvent: String = "",
    @SerializedName("strTimeline") val strTimeline: String = "",
    @SerializedName("strTimelineDetail") val strTimelineDetail: String = "",
    @SerializedName("strHome") val strHome: String = "",
    @SerializedName("strEvent") val strEvent: String = "",
    @SerializedName("idAPIfootball") val idAPIfootball: String = "",
    @SerializedName("idPlayer") val idPlayer: String = "",
    @SerializedName("strPlayer") val strPlayer: String = "",
    @SerializedName("strCountry") val strCountry: String? = null,
    @SerializedName("idAssist") val idAssist: String = "",
    @SerializedName("strAssist") val strAssist: String = "",
    @SerializedName("intTime") val intTime: String = "",
    @SerializedName("idTeam") val idTeam: String = "",
    @SerializedName("strTeam") val strTeam: String = "",
    @SerializedName("strComment") val strComment: String? = null,
    @SerializedName("dateEvent") val dateEvent: String = "",
    @SerializedName("strSeason") val strSeason: String = ""
) : Parcelable

data class TimelineRespond(
    @SerializedName("timelines") val timelines: List<TIMELINE>
)