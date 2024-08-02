package com.example.sportapp.presentation.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sportapp.R
import com.example.sportapp.data.model.LEAGUEMATCH

class MatchAdapter : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>()
{
    private var matches: List<LEAGUEMATCH> = listOf()

    class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val strThumb: ImageView = itemView.findViewById<ImageView>(R.id.strThumb)
        val strEventAlternate: TextView = itemView.findViewById<TextView>(R.id.strEventAlternate)
        val dateEvent: TextView = itemView.findViewById<TextView>(R.id.dateEvent)
        val intHomeScore: TextView = itemView.findViewById<TextView>(R.id.intHomeScore)
        val intAwayScore: TextView = itemView.findViewById<TextView>(R.id.intAwayScore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchAdapter.MatchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match_prev, parent, false)
        return MatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchAdapter.MatchViewHolder, position: Int) {
        val match = matches[position]
        holder.strThumb.load(match.strThumb)
        holder.strEventAlternate.text = match.strEvent
        holder.dateEvent.text = match.dateEvent
        holder.intHomeScore.text = match.intHomeScore
        holder.intAwayScore.text = match.intAwayScore
    }

    override fun getItemCount() = matches.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateMatches(matches: List<LEAGUEMATCH>)
    {
        this.matches = matches
        notifyDataSetChanged()
    }
}