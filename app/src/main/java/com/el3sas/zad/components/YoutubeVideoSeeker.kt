package com.el3sas.zad.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.el3sas.zad.utils.formatDuration
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YoutubeVideoSeeker(
    modifier: Modifier = Modifier,
    player: YouTubePlayer,
    playerState: PlayerConstants.PlayerState,
    currentSecond: Float,
    duration: Float,
) {
    var newCurrentSecond by remember { mutableFloatStateOf(currentSecond) }
    val scope = rememberCoroutineScope()
    var seekerJob: Job? = null

    LaunchedEffect(newCurrentSecond) {
        seekerJob?.cancel()
        seekerJob =
            scope.launch {
                delay(300)
                player.seekTo(newCurrentSecond)
                if (playerState != PlayerConstants.PlayerState.PAUSED) {
                    player.play()
                }
            }
    }

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Slider(
            modifier = Modifier.height(24.dp),
            value = newCurrentSecond,
            onValueChange = { newCurrentSecond = it },
            valueRange = 0f..duration,
            colors =
                SliderDefaults.colors(
                    thumbColor = Color.LightGray,
                    activeTrackColor = Color.Red, // اللون النشط
                    inactiveTrackColor = Color.Gray.copy(alpha = 0.3f), // اللون الغير نشط
                ),
            thumb = {
                Box(
                    modifier =
                        Modifier
                            .size(12.dp)
                            .background(Color.White, CircleShape),
                )
            },
            track = {
                SliderDefaults.Track(
                    sliderState = it,
                    modifier = Modifier.height(4.dp),
                    colors =
                        SliderDefaults.colors(
                            activeTrackColor = Color.Red,
                            inactiveTrackColor = Color.Gray.copy(alpha = 0.3f),
                        ),
                )
            },
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(modifier = Modifier, text = formatDuration(currentSecond))
    }
}
