package com.example.sportapp.presentation.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sportapp.data.model.LEAGUEMATCH
import com.example.sportapp.databinding.ItemSchedualBinding

class SchedualAdapter: RecyclerView.Adapter<SchedualAdapter.SchedualViewHolder>()
{
    private var scheduals: List<LEAGUEMATCH> = listOf()

    class SchedualViewHolder(val binding: ItemSchedualBinding): RecyclerView.ViewHolder(binding.root)
    {
        val strHomeTeam = binding.strHomeTeam
        val strHomeTeamBadge = binding.strHomeTeamBadge
        val strAwayTeam = binding.strAwayTeam
        val strAwayTeamBadge = binding.strAwayTeamBadge
        val dateEventLocal = binding.dateEventLocal
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchedualAdapter.SchedualViewHolder {
        val binding = ItemSchedualBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SchedualViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SchedualAdapter.SchedualViewHolder, position: Int) {
        val schedual = scheduals[position]
        holder.binding.apply {
            strHomeTeam.text = schedual.strHomeTeam
            strHomeTeamBadge.load(schedual.strHomeTeamBadge)
            strAwayTeam.text = schedual.strAwayTeam
            strAwayTeamBadge.load(schedual.strAwayTeamBadge)
            dateEventLocal.text = schedual.strTime
        }
    }

    override fun getItemCount() = scheduals.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateScheduals(scheduals: List<LEAGUEMATCH>)
    {
        this.scheduals = scheduals
        notifyDataSetChanged()
    }
}