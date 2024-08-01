package com.example.sportapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LEAGUEMATCH_NEXT(
    @SerializedName("idEvent") val idEvent: String = "",
    @SerializedName("idSoccerXML") val idSoccerXML: String? = null,
    @SerializedName("idAPIfootball") val idAPIfootball: String = "",
    @SerializedName("strEvent") val strEvent: String = "",
    @SerializedName("strEventAlternate") val strEventAlternate: String = "",
    @SerializedName("strFilename") val strFilename: String = "",
    @SerializedName("strSport") val strSport: String = "",
    @SerializedName("idLeague") val idLeague: String = "",
    @SerializedName("strLeague") val strLeague: String = "",
    @SerializedName("strSeason") val strSeason: String = "",
    @SerializedName("strDescriptionEN") val strDescriptionEN: String = "",
    @SerializedName("strHomeTeam") val strHomeTeam: String = "",
    @SerializedName("strAwayTeam") val strAwayTeam: String = "",
    @SerializedName("intHomeScore") val intHomeScore: String? = null,
    @SerializedName("intRound") val intRound: String = "",
    @SerializedName("intAwayScore") val intAwayScore: String? = null,
    @SerializedName("intSpectators") val intSpectators: String? = null,
    @SerializedName("strOfficial") val strOfficial: String = "",
    @SerializedName("strTimestamp") val strTimestamp: String = "",
    @SerializedName("dateEvent") val dateEvent: String = "",
    @SerializedName("dateEventLocal") val dateEventLocal: String = "",
    @SerializedName("dateEventEnd") val dateEventEnd: String = "",
    @SerializedName("strTime") val strTime: String = "",
    @SerializedName("strTimeLocal") val strTimeLocal: String = "",
    @SerializedName("strGroup") val strGroup: String? = null,
    @SerializedName("idHomeTeam") val idHomeTeam: String = "",
    @SerializedName("strHomeTeamBadge") val strHomeTeamBadge: String = "",
    @SerializedName("idAwayTeam") val idAwayTeam: String = "",
    @SerializedName("strAwayTeamBadge") val strAwayTeamBadge: String = "",
    @SerializedName("intScore") val intScore: String? = null,
    @SerializedName("intScoreVotes") val intScoreVotes: String? = null,
    @SerializedName("strResult") val strResult: String = "",
    @SerializedName("idVenue") val idVenue: String? = null,
    @SerializedName("strVenue") val strVenue: String = "",
    @SerializedName("strCountry") val strCountry: String = "",
    @SerializedName("strCity") val strCity: String = "",
    @SerializedName("strPoster") val strPoster: String = "",
    @SerializedName("strSquare") val strSquare: String = "",
    @SerializedName("strFanart") val strFanart: String? = null,
    @SerializedName("strThumb") val strThumb: String = "",
    @SerializedName("strBanner") val strBanner: String = "",
    @SerializedName("strMap") val strMap: String? = null,
    @SerializedName("strTweet1") val strTweet1: String = "",
    @SerializedName("strTweet2") val strTweet2: String = "",
    @SerializedName("strTweet3") val strTweet3: String = "",
    @SerializedName("strVideo") val strVideo: String = "",
    @SerializedName("strStatus") val strStatus: String = "",
    @SerializedName("strPostponed") val strPostponed: String = "",
    @SerializedName("strLocked") val strLocked: String = ""
) : Parcelable

data class LeagueMatches_NextResponse(
    @SerializedName("1") val leagueMatchesNext: List<LEAGUEMATCH_NEXT>
)