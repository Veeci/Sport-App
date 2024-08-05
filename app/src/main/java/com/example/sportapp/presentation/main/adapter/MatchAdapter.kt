package com.example.sportapp.presentation.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sportapp.R
import com.example.sportapp.data.model.LEAGUEMATCH
import com.example.sportapp.databinding.ItemMatchBinding
import com.example.sportapp.domain.MatchViewModel

class MatchAdapter(private val onMatchClick: (LEAGUEMATCH) -> Unit) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>()
{
    private var matches: List<LEAGUEMATCH> = listOf()

    class MatchViewHolder(val binding: ItemMatchBinding) : RecyclerView.ViewHolder(binding.root)
    {
        val strThumb: ImageView = itemView.findViewById<ImageView>(R.id.strThumb)
        val strEventAlternate: TextView = itemView.findViewById<TextView>(R.id.strEventAlternate)
        val dateEvent: TextView = itemView.findViewById<TextView>(R.id.dateEvent)
        val intHomeScore: TextView = itemView.findViewById<TextView>(R.id.intHomeScore)
        val intAwayScore: TextView = itemView.findViewById<TextView>(R.id.intAwayScore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchAdapter.MatchViewHolder {
        val binding = ItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchAdapter.MatchViewHolder, position: Int) {
        val match = matches[position]
        holder.binding.apply {
            strThumb.load(match.strThumb)
            strEventAlternate.text = match.strEvent
            dateEvent.text = match.dateEvent
            intHomeScore.text = match.intHomeScore
            intAwayScore.text = match.intAwayScore

            root.setOnClickListener {
                onMatchClick(match)
            }
        }
    }

    override fun getItemCount() = matches.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateMatches(matches: List<LEAGUEMATCH>)
    {
        this.matches = matches
        notifyDataSetChanged()
    }
}