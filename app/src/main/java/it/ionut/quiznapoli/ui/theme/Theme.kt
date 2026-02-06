package it.ionut.quiznapoli.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = NapoliAzure,
    onPrimary = NapoliWhite,
    secondary = NapoliBlue,
    onSecondary = NapoliWhite,
    background = NapoliDarkBackground,
    surface = NapoliBlue,
    onSurface = NapoliWhite
)

private val LightColorScheme = lightColorScheme(
    primary = NapoliBlue, // Blu scuro per i testi importanti su sfondo chiaro
    onPrimary = NapoliWhite,
    secondary = NapoliAzure,
    onSecondary = NapoliWhite,
    background = NapoliWhite,
    surface = NapoliWhite,
    onSurface = NapoliBlue
)

@Composable
fun QuizNapoliTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Disattiviamo i colori dinamici di Android per forzare i colori del Napoli
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Barra di stato colorata come il Napoli
            window.statusBarColor = NapoliBlue.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}