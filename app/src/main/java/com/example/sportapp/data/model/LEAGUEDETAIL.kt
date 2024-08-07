package com.example.sportapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LEAGUEDETAIL(
    @SerializedName("idLeague") val idLeague: String = "",
    @SerializedName("idSoccerXML") val idSoccerXML: String? = null,
    @SerializedName("idAPIfootball") val idAPIfootball: String? = null,
    @SerializedName("strSport") val strSport: String = "",
    @SerializedName("strLeague") val strLeague: String = "",
    @SerializedName("strLeagueAlternate") val strLeagueAlternate: String? = null,
    @SerializedName("intDivision") val intDivision: String? = null,
    @SerializedName("idCup") val idCup: String? = null,
    @SerializedName("strCurrentSeason") val strCurrentSeason: String? = null,
    @SerializedName("intFormedYear") val intFormedYear: String? = null,
    @SerializedName("dateFirstEvent") val dateFirstEvent: String? = null,
    @SerializedName("strGender") val strGender: String? = null,
    @SerializedName("strCountry") val strCountry: String? = null,
    @SerializedName("strWebsite") val strWebsite: String? = null,
    @SerializedName("strFacebook") val strFacebook: String? = null,
    @SerializedName("strInstagram") val strInstagram: String? = null,
    @SerializedName("strTwitter") val strTwitter: String? = null,
    @SerializedName("strYoutube") val strYoutube: String? = null,
    @SerializedName("strRSS") val strRSS: String? = null,
    @SerializedName("strDescriptionEN") val strDescriptionEN: String? = null,
    @SerializedName("strDescriptionDE") val strDescriptionDE: String? = null,
    @SerializedName("strDescriptionFR") val strDescriptionFR: String? = null,
    @SerializedName("strDescriptionIT") val strDescriptionIT: String? = null,
    @SerializedName("strDescriptionCN") val strDescriptionCN: String? = null,
    @SerializedName("strDescriptionJP") val strDescriptionJP: String? = null,
    @SerializedName("strDescriptionRU") val strDescriptionRU: String? = null,
    @SerializedName("strDescriptionES") val strDescriptionES: String? = null,
    @SerializedName("strDescriptionPT") val strDescriptionPT: String? = null,
    @SerializedName("strDescriptionSE") val strDescriptionSE: String? = null,
    @SerializedName("strDescriptionNL") val strDescriptionNL: String? = null,
    @SerializedName("strDescriptionHU") val strDescriptionHU: String? = null,
    @SerializedName("strDescriptionNO") val strDescriptionNO: String? = null,
    @SerializedName("strDescriptionPL") val strDescriptionPL: String? = null,
    @SerializedName("strDescriptionIL") val strDescriptionIL: String? = null,
    @SerializedName("strTvRights") val strTvRights: String? = null,
    @SerializedName("strFanart1") val strFanart1: String? = null,
    @SerializedName("strFanart2") val strFanart2: String? = null,
    @SerializedName("strFanart3") val strFanart3: String? = null,
    @SerializedName("strFanart4") val strFanart4: String? = null,
    @SerializedName("strBanner") val strBanner: String? = null,
    @SerializedName("strBadge") val strBadge: String? = null,
    @SerializedName("strLogo") val strLogo: String? = null,
    @SerializedName("strPoster") val strPoster: String? = null,
    @SerializedName("strTrophy") val strTrophy: String? = null,
    @SerializedName("strNaming") val strNaming: String? = null,
    @SerializedName("strComplete") val strComplete: String? = null
) : Parcelable

data class LeagueDetailResponse(
    @SerializedName("leagues") val league: List<LEAGUEDETAIL>
)