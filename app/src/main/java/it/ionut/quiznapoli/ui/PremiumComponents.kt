package it.ionut.quiznapoli.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Canvas

@Composable
fun NapoliPremiumBackground(modifier: Modifier = Modifier) {
    // Glow animato “Champions Night”
    val infinite = rememberInfiniteTransition(label = "bg")
    val t by infinite.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(4200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "t"
    )

    val deep = Color(0xFF001B3A)     // blu notte
    val navy = Color(0xFF002A5C)     // blu Napoli scuro
    val azzurro = Color(0xFF00A7E1)  // azzurro Napoli “neon”
    val gold = Color(0xFFFFD56A)     // oro morbido

    Box(modifier = modifier.fillMaxSize().background(deep)) {
        // Layer gradiente principale
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(navy, deep),
                        center = Offset(600f * t + 120f, 250f),
                        radius = 1200f
                    )
                )
        )

        // Glow azzurro
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(azzurro.copy(alpha = 0.22f), Color.Transparent),
                        center = Offset(250f, 650f * (1f - t) + 120f),
                        radius = 900f
                    )
                )
                .blur(40.dp)
        )

        // Glow oro leggero
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(gold.copy(alpha = 0.12f), Color.Transparent),
                        center = Offset(950f, 250f * t + 80f),
                        radius = 700f
                    )
                )
                .blur(48.dp)
        )
    }
}

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    corner: Dp = 22.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    val shape = RoundedCornerShape(corner)
    Column(
        modifier = modifier
            .clip(shape)
            .background(Color.White.copy(alpha = 0.10f))
            .border(1.dp, Color.White.copy(alpha = 0.16f), shape)
            .padding(18.dp),
        content = content
    )
}

@Composable
fun PremiumGradientButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    val shape = RoundedCornerShape(16.dp)
    val azzurro = Color(0xFF00A7E1)
    val navy = Color(0xFF002A5C)

    androidx.compose.material3.Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.height(56.dp),
        shape = shape,
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.White.copy(alpha = 0.10f)
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape)
                .background(
                    if (enabled) Brush.horizontalGradient(listOf(azzurro, navy))
                    else Brush.horizontalGradient(listOf(Color.Gray, Color.DarkGray))
                )
                .padding(horizontal = 18.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, content = content)
        }
    }
}

@Composable
fun TimerRing(
    secondsLeft: Int,
    totalSeconds: Int,
    modifier: Modifier = Modifier,
    size: Dp = 46.dp
) {
    val progress = (secondsLeft.coerceAtLeast(0)).toFloat() / totalSeconds.toFloat()
    val animated by animateFloatAsState(progress, label = "ring")

    val azzurro = Color(0xFF00A7E1)
    val track = Color.White.copy(alpha = 0.18f)
    val danger = Color(0xFFFF4D4D)
    val color = if (secondsLeft <= 5) danger else azzurro

    Box(modifier = modifier.size(size), contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxSize()) {
            val stroke = Stroke(width = 10f, cap = StrokeCap.Round)
            drawArc(track, startAngle = -90f, sweepAngle = 360f, useCenter = false, style = stroke)
            drawArc(color, startAngle = -90f, sweepAngle = 360f * animated, useCenter = false, style = stroke)
        }
    }
}

@Composable
fun PremiumPulse(modifier: Modifier = Modifier, active: Boolean) {
    val scale by animateFloatAsState(
        targetValue = if (active) 1.02f else 1f,
        animationSpec = tween(280, easing = FastOutSlowInEasing),
        label = "pulse"
    )
    Box(modifier.scale(scale))
}
