package com.appiwedia.apps.android.fdjtest.ui.teamdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appiwedia.apps.android.fdjtest.data.repositories.Repository
import com.appiwedia.apps.android.fdjtest.models.team.TeamsResponse
import com.appiwedia.apps.android.fdjtest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamDetailViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    private val _teamDetails: MutableLiveData<Resource<TeamsResponse>> = MutableLiveData()
    val teamDetails: LiveData<Resource<TeamsResponse>> = _teamDetails

    fun getTeamDetail(teamName: String) {
        _teamDetails.value = Resource.Loading
        viewModelScope.launch {
            repository.getTeamDetail(teamName).collect {
                _teamDetails.value = it
            }
        }
    }
}