package com.abdroid.egylens.presentation.common

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayer(videoUrl: String, isPlaying: Boolean) {
    var lifecycle by remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }
    val context = LocalContext.current

    val mediaItem =
        MediaItem.fromUri(
            videoUrl
        )

    // progressive video:
    val mediaSource: MediaSource =
        ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
            .createMediaSource(mediaItem)


    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaSource(mediaSource)
            prepare()
            playWhenReady = true
            repeatMode = ExoPlayer.REPEAT_MODE_ALL // Set repeat mode
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            exoPlayer.release()
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val playerView = remember { PlayerView(context) }
    playerView.useController = false // Hide playback controls

    Box(
        modifier = Modifier
            .height(440.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center // Change alignment as needed
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                playerView.player = exoPlayer
                playerView
            },
            update = {
                val simpleExoPlayerView = it
                when {
                    isPlaying && lifecycle == Lifecycle.Event.ON_RESUME -> {
                        simpleExoPlayerView.onResume()
                        simpleExoPlayerView.player?.play()
                    }
                    !isPlaying && lifecycle == Lifecycle.Event.ON_RESUME -> {
                        simpleExoPlayerView.onPause()
                        simpleExoPlayerView.player?.pause()
                    }
                    else -> Unit
                }
                // Set content scale
                simpleExoPlayerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
            }
        )
    }
}
