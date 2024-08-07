package com.example.sportapp.presentation.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportapp.data.model.TIMELINE
import com.example.sportapp.databinding.ItemTimelineBinding

class TimelineAdapter(private val onTimelineClick: (TIMELINE) -> Unit): RecyclerView.Adapter<TimelineAdapter.TimelineViewHolder>()
{
    private var timelines: List<TIMELINE> = listOf()

    class TimelineViewHolder(val binding: ItemTimelineBinding) : RecyclerView.ViewHolder(binding.root)
    {
        val intTime = binding.intTime
        val strTimeline = binding.strTimeline
        val strPlayer = binding.strPlayer
        val strAssist = binding.strAssist
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineAdapter.TimelineViewHolder {
        val binding = ItemTimelineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimelineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimelineAdapter.TimelineViewHolder, position: Int) {
        val timeline = timelines[position]
        holder.binding.apply {
            intTime.text = timeline.intTime
            when(timeline.strTimeline)
            {
                "Goal" -> {
                    strTimeline.setImageResource(com.example.sportapp.R.drawable.goal_ic)
                    strPlayer.text = timeline.strPlayer
                    strAssist.text = timeline.strAssist
                }
                "subst" -> {
                    strTimeline.setImageResource(com.example.sportapp.R.drawable.subtitute_ic)
                    strPlayer.text = timeline.strPlayer
                    strAssist.text = timeline.strAssist
                }
                "Card" -> {
                    strTimeline.setImageResource(com.example.sportapp.R.drawable.yellowcard_ic)
                    strPlayer.text = timeline.strPlayer
                }
                "Var" -> {
                    strTimeline.setImageResource(com.example.sportapp.R.drawable.var_ic)
                    strPlayer.text = timeline.strPlayer
                }
            }
        }
    }

    override fun getItemCount() = timelines.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateTimeline(newStats: List<TIMELINE>) {
        timelines = newStats
        notifyDataSetChanged()
    }


}