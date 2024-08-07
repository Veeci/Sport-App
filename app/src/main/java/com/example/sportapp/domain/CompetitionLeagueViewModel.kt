package com.example.sportapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sportapp.data.model.COMPETITIONLEAGUE
import com.example.sportapp.data.repository.CompetitionLeagueRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompetitionLeagueViewModel(private val repository: CompetitionLeagueRepository): ViewModel()
{
    private val _competitionLeagues = MutableLiveData<List<COMPETITIONLEAGUE>>()
    val competitionLeagues: LiveData<List<COMPETITIONLEAGUE>> get() = _competitionLeagues

    private val _nameEnRemember = MutableLiveData<String>()
    val nameEnRemember: LiveData<String> get() = _nameEnRemember

    fun setNameEnRemember(nameEn: String)
    {
        _nameEnRemember.value = nameEn
    }

    fun fetchCompetitionsByCountry(name_en: String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            try
            {
                val response = repository.getCompetitionsByCountry(name_en)
                _competitionLeagues.postValue(response.competitionLeagues)
            }
            catch (e: Exception)
            {
                e.printStackTrace()
            }
        }
    }

    class Factory(private val repository: CompetitionLeagueRepository): ViewModelProvider.Factory
    {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(CompetitionLeagueViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CompetitionLeagueViewModel(repository) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}