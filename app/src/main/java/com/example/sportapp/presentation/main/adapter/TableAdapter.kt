package com.example.sportapp.presentation.main.adapter

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sportapp.data.model.LEAGUETABLE
import com.example.sportapp.data.model.TEAM
import com.example.sportapp.databinding.ItemTableBinding

class TableAdapter(private val onTeamClick: (String) -> Unit): RecyclerView.Adapter<TableAdapter.TableViewHolder>()
{
    private var tables: List<LEAGUETABLE> = listOf()

    class TableViewHolder(val binding: ItemTableBinding): RecyclerView.ViewHolder(binding.root)
    {
        val intRank = binding.intRank
        val strBadge = binding.strBadge
        val strTeam = binding.strTeam
        val intPlayed = binding.intPlayed
        val intWin = binding.intWin
        val intDraw = binding.intDraw
        val intLoss = binding.intLoss
        val intGoalsFor = binding.intGoalsFor
        val intGoalsAgainst = binding.intGoalsAgainst
        val intGoalDifference = binding.intGoalDifference
        val intPoints = binding.intPoints
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableAdapter.TableViewHolder {
        val binding = ItemTableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TableAdapter.TableViewHolder, position: Int) {
        val table = tables[position]
        holder.binding.apply {
            intRank.text = table.intRank
            strBadge.load(table.strBadge)
            strTeam.text = table.strTeam
            intPlayed.text = table.intPlayed
            intWin.text = table.intWin
            intDraw.text = table.intDraw
            intLoss.text = table.intLoss
            intGoalsFor.text = table.intGoalsFor
            intGoalsAgainst.text = table.intGoalsAgainst
            intGoalDifference.text = table.intGoalDifference
            intPoints.text = table.intPoints

            root.setOnClickListener {
                onTeamClick(table.idTeam)
            }

            when(intRank.text.toString().toInt())
            {
                in 1..4 -> intRank.setBackgroundColor(Color.GREEN)
                5 -> intRank.setBackgroundColor(Color.BLUE)
                in 18..20 -> intRank.setBackgroundColor(Color.RED)
            }
        }
    }

    override fun getItemCount() = tables.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateTables(tables: List<LEAGUETABLE>)
    {
        this.tables = tables
        Log.d("TableAdapter", "Tables updated: $tables")
        notifyDataSetChanged()
    }
}