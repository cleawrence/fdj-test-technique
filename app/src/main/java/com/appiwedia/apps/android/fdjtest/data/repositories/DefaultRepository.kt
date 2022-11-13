package com.appiwedia.apps.android.fdjtest.data.repositories

import com.appiwedia.apps.android.fdjtest.data.remote.TeamServiceApi
import com.appiwedia.apps.android.fdjtest.models.league.LeaguesResponse
import com.appiwedia.apps.android.fdjtest.models.team.TeamsResponse
import com.appiwedia.apps.android.fdjtest.utils.BaseApiResponse
import com.appiwedia.apps.android.fdjtest.utils.DispatcherProvider
import com.appiwedia.apps.android.fdjtest.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val teamServiceApi: TeamServiceApi,
    private val dispatchers: DispatcherProvider,
) : Repository, BaseApiResponse() {

    override suspend fun getAllLeagues(): Flow<Resource<LeaguesResponse>> {
        return flow { emit(safeApiCall { teamServiceApi.getAllLeagues() }) }
            .catch { cause: Throwable -> emit(Resource.Error(cause.localizedMessage)) }
            .flowOn(dispatchers.io)
    }

    override suspend fun getAllTeamsFromLeagues(language: String): Flow<Resource<TeamsResponse>> {
        return flow { emit(safeApiCall { teamServiceApi.getAllTeams(language) }) }
            .catch { cause: Throwable -> emit(Resource.Error(cause.localizedMessage)) }
            .flowOn(dispatchers.io)
    }

    override suspend fun getTeamDetail(teamName: String): Flow<Resource<TeamsResponse>> {
        return flow { emit(safeApiCall { teamServiceApi.getTeamDetailByName(teamName) }) }
            .catch { cause: Throwable -> emit(Resource.Error(cause.localizedMessage)) }
            .flowOn(dispatchers.io)
    }
}