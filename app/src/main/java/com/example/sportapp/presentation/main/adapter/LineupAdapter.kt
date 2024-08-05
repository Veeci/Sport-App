package com.example.sportapp.presentation.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sportapp.data.model.LINEUP
import com.example.sportapp.databinding.ItemLineupBinding

class LineupAdapter(private val onLineupClick: (LINEUP) -> Unit): RecyclerView.Adapter<LineupAdapter.LineupViewHolder>() {

    private var lineups: List<LINEUP> = listOf()

    class LineupViewHolder(val binding: ItemLineupBinding): RecyclerView.ViewHolder(binding.root)
    {
        val strCutout: ImageView = binding.strCutout
        val strPlayer: TextView = binding.strPLayer
        val strPosition: TextView = binding.strPosition
        val strTeam: TextView = binding.strTeam
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineupAdapter.LineupViewHolder {
        val binding = ItemLineupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LineupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LineupAdapter.LineupViewHolder, position: Int) {
        val lineup = lineups[position]
        holder.binding.apply {
            strCutout.load(lineup.strCutout)
            strPLayer.text = lineup.strPlayer
            strPosition.text = lineup.strPosition
            strTeam.text = lineup.strTeam

            root.setOnClickListener {
                onLineupClick(lineup)
            }
        }
    }

    override fun getItemCount() = lineups.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateLineups(lineups: List<LINEUP>)
    {
        this.lineups = lineups
        notifyDataSetChanged()
    }

}