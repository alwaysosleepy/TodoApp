package dev.dariakhartova.todoapp.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

data class AppColorScheme(
    val separator: Color,
    val overlay: Color,
    val labelPrimary: Color,
    val primaryBackground: Color,
    val labelSecondary: Color,
    val secondaryBackground: Color,
    val labelTertiary: Color,
    val disable: Color,
    val elevated: Color,
    val red: Color,
    val green: Color,
    val blue: Color,
    val gray: Color,
    val lightGray: Color,
    val white: Color
)

data class AppTypography(
    val largeTitle: TextStyle,
    val title: TextStyle,
    val button: TextStyle,
    val body: TextStyle,
    val subhead: TextStyle
)

val LocalAppColorScheme = staticCompositionLocalOf{
    AppColorScheme(
        separator = Color.Unspecified,
        overlay = Color.Unspecified,
        labelPrimary = Color.Unspecified,
        primaryBackground = Color.Unspecified,
        labelSecondary = Color.Unspecified,
        secondaryBackground = Color.Unspecified,
        labelTertiary = Color.Unspecified,
        disable = Color.Unspecified,
        elevated = Color.Unspecified,
        red = Color.Unspecified,
        blue = Color.Unspecified,
        green = Color.Unspecified,
        gray = Color.Unspecified,
        lightGray = Color.Unspecified,
        white = Color.Unspecified
    )
}

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        largeTitle = TextStyle.Default,
        title = TextStyle.Default,
        button = TextStyle.Default,
        body = TextStyle.Default,
        subhead = TextStyle.Default
    )
}
