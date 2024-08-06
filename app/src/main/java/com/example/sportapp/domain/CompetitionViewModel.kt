package com.example.sportapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sportapp.data.model.COMPETITION
import com.example.sportapp.data.repository.CompetitionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompetitionViewModel(private val repository: CompetitionRepository): ViewModel()
{
    private val _competitions = MutableLiveData<List<COMPETITION>>()
    val competitions: LiveData<List<COMPETITION>> get() = _competitions

    fun fetchAllCompetitions()
    {
        viewModelScope.launch(Dispatchers.IO) {
            try
            {
                val response = repository.getAllCompetitions()
                _competitions.postValue(response.countries)
            }
            catch (e: Exception)
            {
                e.printStackTrace()
            }
        }
    }

    class Factory(private val repository: CompetitionRepository): ViewModelProvider.Factory
    {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(CompetitionViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CompetitionViewModel(repository) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}