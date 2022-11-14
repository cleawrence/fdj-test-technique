package com.appiwedia.apps.android.fdjtest.ui.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appiwedia.apps.android.fdjtest.data.repositories.Repository
import com.appiwedia.apps.android.fdjtest.models.league.League
import com.appiwedia.apps.android.fdjtest.models.league.LeaguesResponse
import com.appiwedia.apps.android.fdjtest.models.team.Team
import com.appiwedia.apps.android.fdjtest.models.team.TeamsResponse
import com.appiwedia.apps.android.fdjtest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _leagues: MutableLiveData<Resource<LeaguesResponse>> = MutableLiveData()
    val leagues: LiveData<Resource<LeaguesResponse>> = _leagues

    private val _teamsLeague: MutableLiveData<Resource<TeamsResponse>> = MutableLiveData()
    val teamLeague: LiveData<Resource<TeamsResponse>> = _teamsLeague

    init {
        getLeagues()
    }

    fun getLeagues() {
        _teamsLeague.value = Resource.Loading
        viewModelScope.launch {
            repository.getAllLeagues().collect { values ->
                _leagues.value = values
            }
        }
    }

    fun getTeamLeagues(strLeague: String) {
        _teamsLeague.value = Resource.Loading
        viewModelScope.launch {
            repository.getAllTeamsFromLeagues(strLeague).collect {
                if (it.data?.teams.isNullOrEmpty()) {
                    _teamsLeague.value = Resource.Error("Une erreur inattendue est survenue")
                }
                _teamsLeague.value = it
            }
        }
    }

    fun getListLeagues(leagues: List<League?>?): Array<String?> {
        return leagues?.map { it?.strLeague }?.toTypedArray() ?: emptyArray()
    }

    fun reverse(teams: List<Team?>?): List<Team?>? {
        return teams?.sortedByDescending { it?.strTeam }
    }
}