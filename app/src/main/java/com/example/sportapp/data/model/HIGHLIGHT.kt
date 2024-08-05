package com.example.sportapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HIGHLIGHT(
    @SerializedName("idEvent") val idEvent: String = "",
    @SerializedName("strEvent") val strEvent: String = "",
    @SerializedName("idLeague") val idLeague: String = "",
    @SerializedName("strThumb") val strThumb: String = "",
    @SerializedName("strPoster") val strPoster: String = "",
    @SerializedName("strFanart") val strFanart: String = "",
    @SerializedName("strSquare") val strSquare: String = "",
    @SerializedName("strVideo") val strVideo: String = ""
): Parcelable

data class HighlightRespond(
    @SerializedName("") val highlights: List<HIGHLIGHT>
)