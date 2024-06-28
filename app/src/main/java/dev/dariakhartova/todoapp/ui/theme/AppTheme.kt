package dev.dariakhartova.todoapp.ui.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val darkColorScheme = AppColorScheme(
    separator = Color(0x33FFFFFF),
    overlay = Color(0x52000000),
    primaryBackground = Color(0xFF161618),
    labelPrimary = Color(0xFFFFFFFF),
    secondaryBackground = Color(0xFF252528),
    labelSecondary = Color(0x99FFFFFF),
    labelTertiary = Color(0x66FFFFFF),
    disable = Color(0x26FFFFFF),
    elevated = Color(0xFF3C3C3F),
    red = Color(0xFFFF453A),
    green = Color(0xFF32D74B),
    blue = Color(0xFF0A84FF),
    gray = Color(0xFF8E8E93),
    lightGray = Color(0xFF48484A),
    white = Color(0xFFFFFFFF)
)

private val lightColorScheme = AppColorScheme(
    separator = Color(0x33000000),
    overlay = Color(0x0F000000),
    primaryBackground = Color(0xFFF7F6F2),
    labelPrimary = Color(0xFF000000),
    secondaryBackground = Color(0xFFFFFFFF),
    labelSecondary = Color(0x99000000),
    labelTertiary = Color(0x4D000000),
    disable = Color(0x26000000),
    elevated = Color(0xFFFFFFFF),
    red = Color(0xFFFF3B30),
    green = Color(0xFF34C759),
    blue = Color(0xFF007AFF),
    gray = Color(0xFF8E8E93),
    lightGray = Color(0xFFD1D1D6),
    white = Color(0xFFFFFFFF)
)

private fun typography(colorScheme: AppColorScheme): AppTypography {
    return AppTypography(
        largeTitle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.Default,
            fontSize = 32.sp,
            lineHeight = 38.sp,
            letterSpacing = 0.5.sp,
            color = colorScheme.labelPrimary
        ),
        title = TextStyle(
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.Default,
            fontSize = 20.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.5.sp,
            color = colorScheme.labelPrimary
        ),
        button = TextStyle(
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.Default,
            fontSize = 14.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.16.sp,
            color = colorScheme.labelPrimary
        ),
        body = TextStyle(
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Default,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.5.sp,
            color = colorScheme.labelPrimary
        ),
        subhead = TextStyle(
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Default,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.5.sp,
            color = colorScheme.labelPrimary
        )
    )
}

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){
    val colorScheme = if (isDarkTheme) darkColorScheme else lightColorScheme
    val rippleIndication = rememberRipple()
    CompositionLocalProvider(
        LocalAppColorScheme provides colorScheme,
        LocalAppTypography provides typography(colorScheme),
        LocalIndication provides rippleIndication,
        content = content
    )

}

object AppTheme {

    val colorScheme: AppColorScheme
        @Composable get() = LocalAppColorScheme.current

    val typography: AppTypography
        @Composable get() = LocalAppTypography.current
}
