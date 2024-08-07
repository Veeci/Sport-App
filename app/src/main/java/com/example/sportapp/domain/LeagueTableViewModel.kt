package com.example.sportapp.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sportapp.data.model.LEAGUETABLE
import com.example.sportapp.data.repository.LeagueRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LeagueTableViewModel(private val repository: LeagueRepository) : ViewModel()
{
    private val _idLeagueRemember = MutableLiveData<String>()
    val idLeagueRemember: LiveData<String> get() = _idLeagueRemember

    private val _strCurrentSeasonRemember = MutableLiveData<String>()
    val strCurrentSeasonRemember: LiveData<String> get() = _strCurrentSeasonRemember

    fun setIdLeagueSeasonRemember(idLeague: String, strCurrentSeason: String)
    {
        _idLeagueRemember.value = idLeague
        _strCurrentSeasonRemember.value = strCurrentSeason
    }

    private val _leagueTable = MutableLiveData<List<LEAGUETABLE>>()
    val leagueTable: LiveData<List<LEAGUETABLE>> get() = _leagueTable

    fun fetchLeagueTable(idLeague: String, strCurrentSeason: String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getLeagueTable(idLeague, strCurrentSeason)
                Log.d("LeagueTableViewModel", "Response: ${response.table}")
                _leagueTable.postValue(response.table)
            }
            catch (e: Exception)
            {
                e.printStackTrace()
            }
        }
    }

    class Factory(private val repository: LeagueRepository): ViewModelProvider.Factory
    {
        override fun <T : ViewModel> create(modelClass: Class<T>): T{
            if(modelClass.isAssignableFrom(LeagueTableViewModel::class.java))
            {
                @Suppress("UNCHECKED_CAST")
                return LeagueTableViewModel(repository) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}