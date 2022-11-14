package com.appiwedia.apps.android.fdjtest.ui.team

import com.appiwedia.apps.android.fdjtest.data.repositories.Repository
import com.appiwedia.apps.android.fdjtest.models.league.LeaguesResponse
import com.appiwedia.apps.android.fdjtest.models.team.TeamsResponse
import com.appiwedia.apps.android.fdjtest.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTeamRepository: Repository {
    override suspend fun getAllLeagues(): Flow<Resource<LeaguesResponse>> {
        return flow { emit(Resource.Success(LeaguesResponse(emptyList()))) }
    }

    override suspend fun getAllTeamsFromLeagues(language: String): Flow<Resource<TeamsResponse>> {
        return flow { emit(Resource.Success(TeamsResponse(emptyList()))) }
    }

    override suspend fun getTeamDetail(teamName: String): Flow<Resource<TeamsResponse>> {
        return flow { emit(Resource.Success(TeamsResponse(emptyList()))) }
    }
}