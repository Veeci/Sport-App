package com.example.sportapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class VENUE(
    @SerializedName("idVenue") val idVenue: String = "",
    @SerializedName("idDupe") val idDupe: String? = null,
    @SerializedName("strVenue") val strVenue: String = "",
    @SerializedName("strVenueAlternate") val strVenueAlternate: String? = null,
    @SerializedName("strVenueSponsor") val strVenueSponsor: String? = null,
    @SerializedName("strSport") val strSport: String = "",
    @SerializedName("strDescriptionEN") val strDescriptionEN: String? = null,
    @SerializedName("strArchitect") val strArchitect: String? = null,
    @SerializedName("intCapacity") val intCapacity: String? = null,
    @SerializedName("strCost") val strCost: String? = null,
    @SerializedName("strCountry") val strCountry: String = "",
    @SerializedName("strLocation") val strLocation: String = "",
    @SerializedName("strTimezone") val strTimezone: String? = null,
    @SerializedName("intFormedYear") val intFormedYear: String? = null,
    @SerializedName("strFanart1") val strFanart1: String? = null,
    @SerializedName("strFanart2") val strFanart2: String? = null,
    @SerializedName("strFanart3") val strFanart3: String? = null,
    @SerializedName("strFanart4") val strFanart4: String? = null,
    @SerializedName("strThumb") val strThumb: String? = null,    @SerializedName("strLogo") val strLogo: String? = null,
    @SerializedName("strMap") val strMap: String? = null,
    @SerializedName("strWebsite") val strWebsite: String? = null,
    @SerializedName("strFacebook") val strFacebook: String? = null,
    @SerializedName("strInstagram") val strInstagram: String? = null,
    @SerializedName("strTwitter") val strTwitter: String? = null,
    @SerializedName("strYoutube") val strYoutube: String? = null,
    @SerializedName("strLocked") val strLocked: String? = null
) : Parcelable

data class VenueResponse(
    @SerializedName("venues") val venues: List<VENUE>
)