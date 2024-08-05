package com.example.sportapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sportapp.data.model.LEAGUEMATCH
import com.example.sportapp.data.repository.MatchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MatchViewModel(private val repository: MatchRepository): ViewModel()
{
    private val _idEventRemember = MutableLiveData<String>()
    val idEventRemember: LiveData<String> get() = _idEventRemember

    fun setIdEventRemember(idEvent: String)
    {
        _idEventRemember.value = idEvent
    }

    private val _matchesPrev = MutableLiveData<List<LEAGUEMATCH>>()
    val matchesPrev: LiveData<List<LEAGUEMATCH>> get() = _matchesPrev

    private val _matchesNext = MutableLiveData<List<LEAGUEMATCH>>()
    val matchesNext: LiveData<List<LEAGUEMATCH>> get() = _matchesNext

    private val _matchDetail = MutableLiveData<LEAGUEMATCH>()
    val matchDetail: LiveData<LEAGUEMATCH> get() = _matchDetail

    fun fetchLeagueMatchesPrevious(idLeague: String)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            try
            {
                val response = repository.getLeagueMatchesPrevious(idLeague)
                _matchesPrev.postValue(response.leagueMatches)
            }
            catch (e: Exception)
            {
                e.printStackTrace()
            }
        }
    }

    fun fetchLeagueMatchesNext(idLeague: String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getLeagueMatchesNext(idLeague)
                _matchesNext.postValue(response.leagueMatches)
            }
            catch (e: Exception)
            {
                e.printStackTrace()
            }
        }
    }

    fun fetchMatchDetail(idEvent: String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getMatchDetail(idEvent)
                _matchDetail.postValue(response.leagueMatch)
            }
            catch (e: Exception)
            {
                e.printStackTrace()
            }
        }
    }

    class Factory(private val matchRepository: MatchRepository): ViewModelProvider.Factory
    {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MatchViewModel::class.java))
            {
                @Suppress("UNCHECKED_CAST")
                return MatchViewModel(matchRepository) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}