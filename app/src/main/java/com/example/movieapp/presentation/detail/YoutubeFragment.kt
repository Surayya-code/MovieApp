package com.example.movieapp.presentation.detail
import androidx.navigation.fragment.navArgs
import com.example.movieapp.common.base.BaseFragment
import com.example.movieapp.databinding.FragmentYoutubeBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YoutubeFragment
    : BaseFragment<FragmentYoutubeBinding>(FragmentYoutubeBinding::inflate) {

    private val args: YoutubeFragmentArgs by navArgs()

    override fun onViewCreateFinish() {
        lifecycle.addObserver(binding.youtubePlayerView)

        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                youTubePlayer.loadVideo(args.movieId, 0f)
            }
        })
    }

    override fun observeEvents() {

    }
}