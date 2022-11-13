package com.appiwedia.apps.android.fdjtest.models.team

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamsResponse(
    @Json(name = "teams")
    val teams: List<Team?>?
): Parcelable