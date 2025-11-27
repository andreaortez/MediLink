package com.example.medilink.ui.theme

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

private val DarkColorScheme = darkColorScheme(
    primary = Azul,
    onPrimary = Color.White,
    primaryContainer = AzulOscuro,
    onPrimaryContainer = Color.White,
    secondary = CelesteVivido,
    onSecondary = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = Azul,
    onPrimary = Color.White,
    primaryContainer = CelesteClaro,
    onPrimaryContainer = AzulOscuro,
    secondary = CelesteVivido,
    onSecondary = AzulNegro,
    surface = Color.White,
    onSurface = AzulNegro,
)

@Composable
fun MediLinkTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
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