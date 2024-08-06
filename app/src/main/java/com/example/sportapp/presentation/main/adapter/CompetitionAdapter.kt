package com.example.sportapp.presentation.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sportapp.data.model.COMPETITION
import com.example.sportapp.databinding.ItemCompetitionBinding

class CompetitionAdapter(private val onCompetitionClick: (COMPETITION) -> Unit): RecyclerView.Adapter<CompetitionAdapter.CompetitionViewHolder>()
{
    private var competitions: List<COMPETITION> = listOf()

    class CompetitionViewHolder(val binding: ItemCompetitionBinding): RecyclerView.ViewHolder(binding.root)
    {
        val nameEn = binding.nameEn
        val flagUrl32 = binding.flagUrl32
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionAdapter.CompetitionViewHolder {
        val binding = ItemCompetitionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CompetitionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CompetitionAdapter.CompetitionViewHolder, position: Int) {
        val competition = competitions[position]
        holder.binding.apply {
            nameEn.text = competition.name_en
            flagUrl32.load(competition.flag_url_32)

            root.setOnClickListener {
                onCompetitionClick(competition)
            }
        }
    }

    override fun getItemCount() = competitions.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateCompetitions(competitions: List<COMPETITION>)
    {
        this.competitions = competitions
        notifyDataSetChanged()
    }
}