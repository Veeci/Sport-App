package com.example.sportapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EQUIPMENT(
    @SerializedName("idEquipment") val idEquipment: String = "",
    @SerializedName("idTeam") val idTeam: String = "",
    @SerializedName("date") val date: String = "",
    @SerializedName("strSeason") val strSeason: String = "",
    @SerializedName("strEquipment") val strEquipment: String = "",
    @SerializedName("strType") val strType: String = "",
    @SerializedName("strUsername") val strUsername: String = ""
): Parcelable

data class EquipmentResponse(
    @SerializedName("equipment") val equipment: List<EQUIPMENT>
)