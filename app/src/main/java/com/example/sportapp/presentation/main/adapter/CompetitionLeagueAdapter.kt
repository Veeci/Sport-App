package com.example.sportapp.presentation.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sportapp.data.model.COMPETITIONLEAGUE
import com.example.sportapp.databinding.ItemCompetitionLeagueBinding

class CompetitionLeagueAdapter(private val onCompetitionLeagueClick: (COMPETITIONLEAGUE) -> Unit) :
    RecyclerView.Adapter<CompetitionLeagueAdapter.CompetitionLeagueViewHolder>() {
    private var competitionLeagues: List<COMPETITIONLEAGUE> = listOf()

    class CompetitionLeagueViewHolder(val binding: ItemCompetitionLeagueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val strBadge = binding.strBadge
        val strLeague = binding.strLeague
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionLeagueAdapter.CompetitionLeagueViewHolder {
        val binding = ItemCompetitionLeagueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CompetitionLeagueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CompetitionLeagueAdapter.CompetitionLeagueViewHolder, position: Int) {
        val competitionLeague = competitionLeagues[position]
        if (competitionLeague.strSport == "Soccer") {
            holder.binding.apply {
                strBadge.load(competitionLeague.strBadge)
                strLeague.text = competitionLeague.strLeague

                root.setOnClickListener {
                    onCompetitionLeagueClick(competitionLeague)
                }
            }
        }
        else
        {
            holder.binding.root.visibility = ViewGroup.GONE
            holder.binding.root.layoutParams = RecyclerView.LayoutParams(0, 0)
        }
    }

    override fun getItemCount() = competitionLeagues.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateCompetitionLeagues(competitionLeagues: List<COMPETITIONLEAGUE>) {
        this.competitionLeagues = competitionLeagues
        notifyDataSetChanged()
    }
}