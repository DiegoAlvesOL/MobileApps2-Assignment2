package com.diegoalves.stateauthapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val NavyColorScheme = lightColorScheme(
    primary = NavyMid,
    onPrimary = SurfaceWhite,
    primaryContainer = NavyLight,
    onPrimaryContainer = NavyDark,
    secondary = NavyDark,
    onSecondary = SurfaceWhite,
    tertiary = SuccessGreen,
    onTertiary = SurfaceWhite,
    background = SandBackground,
    onBackground = NavyDark,
    surface = SurfaceWhite,
    onSurface = NavyDark,
    surfaceVariant = NavyLight,
    onSurfaceVariant = TextDim,
    outline = BorderGray,
    error = ErrorRed,
    onError = SurfaceWhite,
    errorContainer = ErrorLight,
    onErrorContainer = ErrorRed
)


@Composable
fun StateAuthAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = NavyColorScheme,
        typography = Typography,
        content = content
    )
}