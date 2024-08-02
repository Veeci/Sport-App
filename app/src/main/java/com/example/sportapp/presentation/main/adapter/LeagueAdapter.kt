package com.example.sportapp.presentation.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sportapp.R
import com.example.sportapp.data.model.LEAGUE

class LeagueAdapter(private var leagues: List<LEAGUE> = listOf(), private val onLeagueClick: (LEAGUE) -> Unit): RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>()
{
    private var selectedPosition = RecyclerView.NO_POSITION

    inner class LeagueViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val strLeague: TextView = itemView.findViewById(R.id.strLeague)

        init {
            itemView.setOnClickListener{
                val previousPosition = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)
                onLeagueClick(leagues[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_league, parent, false)
        return LeagueViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        val league = leagues[position]
        holder.strLeague.text = league.strLeague

        if(position == selectedPosition)
        {
            holder.itemView.setBackgroundResource(R.color.text_secondary)
        }
        else
        {
            holder.itemView.setBackgroundResource(R.color.theme_main)
        }
    }

    override fun getItemCount() = leagues.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateLeagues(leagues: List<LEAGUE>) {
        this.leagues = leagues
        notifyDataSetChanged()
    }
}