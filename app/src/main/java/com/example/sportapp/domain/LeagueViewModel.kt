package com.example.sportapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sportapp.data.model.LEAGUE
import com.example.sportapp.data.repository.LeagueRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LeagueViewModel(private val repository: LeagueRepository): ViewModel()
{
    private val _leagues = MutableLiveData<List<LEAGUE>>()
    val leagues: LiveData<List<LEAGUE>> get() = _leagues

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