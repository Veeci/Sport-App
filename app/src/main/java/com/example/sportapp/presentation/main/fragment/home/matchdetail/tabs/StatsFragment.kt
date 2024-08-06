package com.example.sportapp.presentation.main.fragment.home.matchdetail.tabs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.sportapp.R
import com.example.sportapp.data.api.apiService
import com.example.sportapp.data.model.STATS
import com.example.sportapp.data.model.TIMELINE
import com.example.sportapp.data.repository.MatchRepository
import com.example.sportapp.databinding.FragmentStatsBinding
import com.example.sportapp.domain.MatchViewModel
import com.example.sportapp.presentation.main.adapter.StatAdapter
import com.example.sportapp.presentation.main.adapter.TimelineAdapter
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
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
    private var videoId: String? = null
    private var strThumb: String? = null

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
            statAdapter.updateStats(stats.ifEmpty { emptyList() })
        })

        matchViewModel.matchTimeline.observe(viewLifecycleOwner, Observer { timeLine ->
            timelineAdapter.updateTimeline(timeLine.ifEmpty { emptyList() })
        })

        matchViewModel.matchHighlights.observe(viewLifecycleOwner, Observer { highlights ->
            if (highlights.isNotEmpty()) {
                val highlight = highlights[0]
                videoId = extractYoutubeVideoId(highlight.strVideo)
                strThumb = highlight.strThumb
                Log.d("StatsFragment", "Video ID extracted: $videoId")
                loadVideoIfReady()
            }
        })

        youTubePlayerView?.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                Log.d("StatsFragment", "YouTubePlayer is ready")
                this@StatsFragment.youTubePlayer = youTubePlayer
                loadVideoIfReady()
            }

            override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
                Log.e("StatsFragment", "YouTubePlayer error: $error")
                displayThumbnail()
            }
        })

        // Fetch data
        matchViewModel.fetchMatchStats(matchViewModel.idEventRemember.value.toString())
        matchViewModel.fetchMatchTimeline(matchViewModel.idEventRemember.value.toString())
        matchViewModel.fetchMatchHighlights(matchViewModel.idEventRemember.value.toString())
    }

    private fun loadVideoIfReady() {
        if (youTubePlayer != null && videoId != null) {
            Log.d("StatsFragment", "Loading video with ID: $videoId")
            youTubePlayer?.loadVideo(videoId!!, 0f)
        } else {
            Log.d("StatsFragment", "YouTubePlayer or videoId is null")
        }
    }

    private fun extractYoutubeVideoId(strVideo: String): String? {
        val regex = Regex("(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?v%3D|watch\\?vi=|watch\\?vi%3D|%2Fvideos%2F|embed%2F|youtu.be%2F|\\/v%2F)[^#&?\\n]*")
        val matchResult = regex.find(strVideo)
        return matchResult?.value
    }

    private fun displayThumbnail() {
        if(strThumb != null)
        {
            binding.strThumb.visibility = View.VISIBLE
            binding.matchPosterTV.visibility = View.VISIBLE
            youTubePlayerView?.visibility = View.GONE
            binding.matchHighlightsTV.visibility = View.GONE
            Glide.with(this).load(strThumb).into(binding.strThumb)
        }
    }

    private fun onStatClick(stat: STATS) {
        // Handle stat click
    }

    private fun onTimelineClick(timeline: TIMELINE) {
        // Handle timeline click
    }

    override fun onDestroyView() {
        super.onDestroyView()
        youTubePlayerView?.release()
        _binding = null
    }
}
