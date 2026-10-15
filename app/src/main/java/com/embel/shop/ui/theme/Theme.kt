package com.embel.shop.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val EmbelColorScheme = lightColorScheme(
    primary = EmbelPrimary,
    onPrimary = Color.White,
    primaryContainer = EmbelPrimary,
    onPrimaryContainer = Color.White,
    secondary = EmbelSecondary,
    onSecondary = Color.White,
    tertiary = EmbelAccent,
    onTertiary = Color.White,
    background = EmbelBackground,
    onBackground = EmbelTextPrimary,
    surface = EmbelBackground,
    onSurface = EmbelTextPrimary,
    surfaceVariant = EmbelSurface,
    onSurfaceVariant = EmbelTextSecondary,
    outline = EmbelBorder,
    outlineVariant = EmbelBorderLight,
    error = EmbelDangerText,
    onError = Color.White,
    errorContainer = EmbelDanger,
    onErrorContainer = EmbelDangerText,
)

@Composable
fun EmbelTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = EmbelColorScheme,
        typography = EmbelTypography,
        shapes = EmbelShapes,
        content = content
    )
}
