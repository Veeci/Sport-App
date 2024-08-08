package com.example.sportapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sportapp.data.model.COUNTRY
import com.example.sportapp.data.repository.CountryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountryViewModel(private val repository: CountryRepository) : ViewModel() {
    private val _countries = MutableLiveData<List<COUNTRY>>()
    val countries: LiveData<List<COUNTRY>> get() = _countries

    fun fetchAllCountries() = viewModelScope.launch {
        try {
            val response = repository.getAllCountries()
            _countries.postValue(response.countries)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    class Factory(private val repository: CountryRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CountryViewModel(repository) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}