package com.example.sportapp.presentation.main.fragment.home.matchdetail.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportapp.R
import com.example.sportapp.data.api.apiService
import com.example.sportapp.data.model.STATS
import com.example.sportapp.data.model.TIMELINE
import com.example.sportapp.data.repository.MatchRepository
import com.example.sportapp.databinding.FragmentStatsBinding
import com.example.sportapp.domain.MatchViewModel
import com.example.sportapp.presentation.main.adapter.StatAdapter
import com.example.sportapp.presentation.main.adapter.TimelineAdapter
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class StatsFragment : Fragment() {

    private var _binding: FragmentStatsBinding? = null
    private val binding get() = _binding!!

    private val matchViewModel: MatchViewModel by activityViewModels {
        MatchViewModel.Factory(MatchRepository(apiService))
    }

    private lateinit var statAdapter: StatAdapter
    private lateinit var timelineAdapter: TimelineAdapter

    private var youTubePlayerView: YouTubePlayerView? = null
    private var youTubePlayer: YouTubePlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatsBinding.inflate(inflater, container, false)

        statAdapter = StatAdapter { onStatClick(it) }
        timelineAdapter = TimelineAdapter { onTimelineClick(it) }

        binding.matchStatsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = statAdapter
        }

        binding.matchTimelineRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = timelineAdapter
        }

        youTubePlayerView = binding.youtubePlayerView
        lifecycle.addObserver(youTubePlayerView!!)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        matchViewModel.matchStats.observe(viewLifecycleOwner, Observer { stats ->
            if(stats.isNotEmpty())
            {
                statAdapter.updateStats(stats)
            }
            else
            {
                statAdapter.updateStats(emptyList())
            }
        })

        matchViewModel.matchTimeline.observe(viewLifecycleOwner, Observer { timeLine ->
            if(timeLine.isNotEmpty())
            {
                timelineAdapter.updateTimeline(timeLine)
            }
            else
            {
                timelineAdapter.updateTimeline(emptyList())
            }
        })

        matchViewModel.matchHighlights.observe(viewLifecycleOwner, Observer { highlights ->
            if(highlights.isNotEmpty())
            {
                val highlight = highlights[0]
                val videoId = extractYoutubeVideoId(highlight.strVideo)
                videoId?.let {
                    youTubePlayer?.loadVideo(it, 0f)
                }
            }
        })

        youTubePlayerView?.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                this@StatsFragment.youTubePlayer = youTubePlayer
            }
        })

        matchViewModel.fetchMatchStats(matchViewModel.idEventRemember.value.toString())
        matchViewModel.fetchMatchTimeline(matchViewModel.idEventRemember.value.toString())
        matchViewModel.fetchMatchHighlights(matchViewModel.idEventRemember.value.toString())
    }

    private fun extractYoutubeVideoId(strVideo: String): String? {
        val regex = Regex("(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?v%3D|watch\\?vi=|watch\\?vi%3D|%2Fvideos%2F|embed%2F|youtu.be%2F|\\/v%2F)[^#&?\\n]*")
        val matchResult = regex.find(strVideo)
        return matchResult?.value
    }

    private fun onStatClick(stat: STATS) {

    }

    private fun onTimelineClick(timeline: TIMELINE) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}