package com.appiwedia.apps.android.fdjtest.models.league

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class League(
    @Json(name = "idLeague")
    val idLeague: String?,
    @Json(name = "strLeague")
    val strLeague: String?,
    @Json(name = "strLeagueAlternate")
    val strLeagueAlternate: String?,
    @Json(name = "strSport")
    val strSport: String?,
) : Parcelable