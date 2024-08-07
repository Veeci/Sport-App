package com.example.sportapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sportapp.data.model.LEAGUE
import com.example.sportapp.data.model.LEAGUEDETAIL
import com.example.sportapp.data.repository.LeagueRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LeagueViewModel(private val repository: LeagueRepository): ViewModel()
{
    private val _idLeagueRemember = MutableLiveData<String>()
    val idLeagueRemember: LiveData<String> get() = _idLeagueRemember

    fun setIdLeagueRemember(idLeague: String)
    {
        _idLeagueRemember.value = idLeague
    }

    private val _leagues = MutableLiveData<List<LEAGUE>>()
    val leagues: LiveData<List<LEAGUE>> get() = _leagues

    private val _leagueDetail = MutableLiveData<List<LEAGUEDETAIL>>()
    val leagueDetail: LiveData<List<LEAGUEDETAIL>> get() = _leagueDetail

    fun fetchAllLeagues()
    {
        viewModelScope.launch(Dispatchers.IO) {
            try
            {
                val response = repository.getAllLeagues()
                _leagues.postValue(response.leagues)
            }
            catch (e: Exception)
            {
                e.printStackTrace()
            }
        }
    }

    fun fetchLeagueDetail(idLeague: String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getLeagueDetail(idLeague)
                _leagueDetail.postValue(response.league)
            }
            catch (e: Exception)
            {
                e.printStackTrace()
            }
        }
    }

    class Factory(private val repository: LeagueRepository): ViewModelProvider.Factory
    {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(LeagueViewModel::class.java))
            {
                @Suppress("UNCHECKED_CAST")
                return LeagueViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}