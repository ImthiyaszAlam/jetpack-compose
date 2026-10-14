package com.kaamai.app.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val KaamBlue = Color(0xFF0866F5)

private val DarkColorScheme = darkColorScheme(
    primary = KaamBlue,
    onPrimary = Color.White,
    background = Color(0xFF111827),
    onBackground = Color(0xFFF9FAFB),
    surface = Color(0xFF1F2937),
    onSurface = Color(0xFFF9FAFB),
    surfaceVariant = Color(0xFF374151),
    onSurfaceVariant = Color(0xFF9CA3AF),
    outline = Color(0xFF4B5563)
)

private val LightColorScheme = lightColorScheme(
    primary = KaamBlue,
    onPrimary = Color.White,
    background = Color(0xFFF7F9FC),
    onBackground = Color(0xFF17243A),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF17243A),
    surfaceVariant = Color(0xFFEAF3FF),
    onSurfaceVariant = Color(0xFF78869B),
    outline = Color(0xFFE4EAF2)
)

@Composable
fun KaamAITheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
