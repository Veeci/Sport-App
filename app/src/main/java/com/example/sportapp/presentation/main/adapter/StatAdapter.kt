package com.example.sportapp.presentation.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportapp.data.model.STATS
import com.example.sportapp.databinding.ItemStatBinding

class StatAdapter(private val onStatClick: (STATS) -> Unit) : RecyclerView.Adapter<StatAdapter.StatViewHolder>() {

    private var stats: List<STATS> = listOf()

    class StatViewHolder(val binding: ItemStatBinding) : RecyclerView.ViewHolder(binding.root) {
        val intHome = binding.intHome
        val intAway = binding.intAway
        val strStat = binding.strStat
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatAdapter.StatViewHolder {
        val binding = ItemStatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatAdapter.StatViewHolder, position: Int) {
        val stat = stats[position]
        holder.binding.apply {
            intHome.text = stat.intHome
            intAway.text = stat.intAway
            strStat.text = stat.strStat

            root.setOnClickListener {
                onStatClick(stat)
            }
        }
    }

    override fun getItemCount() = stats.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateStats(newStats: List<STATS>) {
        stats = newStats
        notifyDataSetChanged()
    }
}