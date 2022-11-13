package com.appiwedia.apps.android.fdjtest.data.repositories

import com.appiwedia.apps.android.fdjtest.models.league.LeaguesResponse
import com.appiwedia.apps.android.fdjtest.models.team.TeamsResponse
import com.appiwedia.apps.android.fdjtest.utils.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getAllLeagues(): Flow<Resource<LeaguesResponse>>

    suspend fun getAllTeamsFromLeagues(language: String): Flow<Resource<TeamsResponse>>

    suspend fun getTeamDetail(teamName: String): Flow<Resource<TeamsResponse>>
}