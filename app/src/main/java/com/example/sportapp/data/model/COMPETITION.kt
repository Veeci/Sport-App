package com.example.sportapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class COMPETITION(
    @SerializedName("name_en") val name_en: String = "",
    @SerializedName("flag_url_32") val flag_url_32: String = "",
) : Parcelable

data class CompetitionRespond(
    @SerializedName("countries") val countries: List<COMPETITION>
)