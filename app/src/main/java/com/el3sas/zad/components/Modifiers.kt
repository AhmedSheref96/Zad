package com.el3sas.zad.components

import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import kotlin.math.max

@Stable
fun Modifier.circleGradientBackground(
    radius: Float = 0f,
    colors: List<Color> = listOf(Color.Transparent, Color.Gray.copy(alpha = 0.9f)),
) = this.graphicsLayer().drawWithContent {
    drawContent()
    drawRect(
        brush =
            Brush.radialGradient(
                colors = colors,
                center = Offset(size.width / 2, size.height / 2),
                radius = radius.takeIf { f -> f != 0f } ?: (max(size.height, size.width) / 2),
            ),
        size = size,
    )
}

@Stable
fun Modifier.linearGradientBackground(
    colors: List<Color> =
        listOf(
            Color.Transparent,
            Color.Black.copy(alpha = 0.3f),
        ),
) = this.graphicsLayer().drawWithContent {
    drawContent()
    drawRect(
        brush =
            Brush.verticalGradient(
                colors = colors,
            ),
        size = size,
    )
}
