package com.appiwedia.apps.android.fdjtest.data.remote

import com.appiwedia.apps.android.fdjtest.models.league.LeaguesResponse
import com.appiwedia.apps.android.fdjtest.models.team.TeamsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamServiceApi {

    @GET("all_leagues.php")
    suspend fun getAllLeagues(): Response<LeaguesResponse>

    @GET("search_all_teams.php")
    suspend fun getAllTeams(@Query("l") language: String): Response<TeamsResponse>

    @GET("searchteams.php")
    suspend fun getTeamDetailByName(@Query("t") teamName: String): Response<TeamsResponse>
}