package com.example.sportapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sportapp.data.model.LEAGUEMATCH_NEXT
import com.example.sportapp.data.model.LEAGUEMATCH_PREVIOUS
import com.example.sportapp.data.repository.MatchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MatchViewModel(private val repository: MatchRepository): ViewModel()
{
    private val _matchesPrev = MutableLiveData<List<LEAGUEMATCH_PREVIOUS>>()
    val matchesPrev: LiveData<List<LEAGUEMATCH_PREVIOUS>> get() = _matchesPrev

    private val _matchesNext = MutableLiveData<List<LEAGUEMATCH_NEXT>>()
    val matchesNext: LiveData<List<LEAGUEMATCH_NEXT>> get() = _matchesNext

    fun fetchLeagueMatchesPrevious(idLeague: String)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            try
            {
                val response = repository.getLeagueMatchesPrevious(idLeague)
                _matchesPrev.postValue(response.leagueMatchesPrevious)
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
                _matchesNext.postValue(response.leagueMatchesNext)
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