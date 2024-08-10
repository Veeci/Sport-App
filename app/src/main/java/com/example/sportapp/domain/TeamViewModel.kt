package com.example.sportapp.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sportapp.data.model.TEAM
import com.example.sportapp.data.model.VENUE
import com.example.sportapp.data.repository.TeamRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamViewModel(private val teamRepository: TeamRepository) : ViewModel()
{
    private val _idTeam = MutableLiveData<String>()
    val idTeam: LiveData<String> get() = _idTeam

    fun setIdTeamRemember(idTeam: String)
    {
        _idTeam.value = idTeam
    }

    private val _idVenue = MutableLiveData<String>()
    val idVenue: LiveData<String> get() = _idVenue

    fun setIdVenueRemember(idVenue: String)
    {
        _idVenue.value = idVenue
    }

    private val _teamDetail = MutableLiveData<List<TEAM>>()
    val teamDetail: LiveData<List<TEAM>> get() = _teamDetail

    private val _teamVenue = MutableLiveData<List<VENUE>>()
    val teamVenue: LiveData<List<VENUE>> get() = _teamVenue

    fun fetchTeamDetail(idTeam: String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = teamRepository.getTeamDetail(idTeam)
                _teamDetail.postValue(response.teams)

                val team = response.teams.firstOrNull()
                team?.let {
                    fetchTeamVenue(it.idVenue)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchTeamVenue(idVenue: String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            try
            {
                val response = teamRepository.getTeamVenue(idVenue)
                Log.d("TeamViewModel", "Team venue response: $response")
                _teamVenue.postValue(response.venues)
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    class Factory(private val repository: TeamRepository): ViewModelProvider.Factory
    {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(TeamViewModel::class.java))
            {
                @Suppress("UNCHECKED_CAST")
                return TeamViewModel(repository) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}