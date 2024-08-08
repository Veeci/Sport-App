package com.example.sportapp.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sportapp.data.model.TEAM
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

    private val _teamDetail = MutableLiveData<List<TEAM>>()
    val teamDetail: LiveData<List<TEAM>> get() = _teamDetail

    fun fetchTeamDetail(idTeam: String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = teamRepository.getTeamDetail(idTeam)
                Log.d("TeamViewModel", "Team detail response: $response")
                _teamDetail.postValue(response.teams)
            } catch (e: Exception) {
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