package com.example.sportapp.presentation.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sportapp.data.model.COUNTRY
import com.example.sportapp.databinding.ItemCountryBinding

class CountryAdapter(private val onCountryClick: (COUNTRY) -> Unit): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>()
{
    private var countries: List<COUNTRY> = listOf()

    class CountryViewHolder(val binding: ItemCountryBinding): RecyclerView.ViewHolder(binding.root)
    {
        val nameEn = binding.nameEn
        val flagUrl32 = binding.flagUrl32
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryAdapter.CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryAdapter.CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.binding.apply {
            nameEn.text = country.name_en
            flagUrl32.load(country.flag_url_32)

            root.setOnClickListener {
                onCountryClick(country)
            }
        }
    }

    override fun getItemCount() = countries.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateCountries(countries: List<COUNTRY>)
    {
        this.countries = countries
        notifyDataSetChanged()
    }
}