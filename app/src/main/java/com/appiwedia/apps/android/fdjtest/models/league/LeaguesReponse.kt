package com.appiwedia.apps.android.fdjtest.models.league

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class LeaguesResponse(
    @Json(name = "leagues")
    val leagues: List<League?>?
) : Parcelable