package com.example.sportapp.presentation.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sportapp.R
import com.example.sportapp.data.model.LEAGUE

class LeagueAdapter(private val onLeagueClick: (LEAGUE) -> Unit): RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>()
{
    private var leagues: List<LEAGUE> = listOf()

    class LeagueViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val strLeague: TextView = itemView.findViewById(R.id.strLeague)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_league, parent, false)
        return LeagueViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        val league = leagues[position]
        holder.strLeague.text = league.strLeague
        holder.itemView.setOnClickListener { onLeagueClick(league) }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateLeagues(newLeagues: List<LEAGUE>)
    {
        leagues = newLeagues
        notifyDataSetChanged()
    }

    override fun getItemCount() = leagues.size
}