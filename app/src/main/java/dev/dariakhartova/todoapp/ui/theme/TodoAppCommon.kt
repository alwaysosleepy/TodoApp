package dev.dariakhartova.todoapp.ui.theme

import android.graphics.drawable.shapes.Shape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import java.time.format.TextStyle

data class TodoAppColors(
    val separator: Color,
    val overlay: Color,
    val primaryText: Color,
    val primaryBackground: Color,
    val secondaryText: Color,
    val secondaryBackground: Color,
    val tertiaryText: Color,
    val disable: Color,
    val elevated: Color,
    val tintColor: Color
)

data class TodoAppTypography(
    val largeTitle: TextStyle,
    val title: TextStyle,
    val button: TextStyle,
    val body: TextStyle,
    val subhead: TextStyle
)

data class TodoAppShape(
    val padding: Dp,
    val cornerStyle: Shape
)

object AppTheme {
    val colors: TodoAppColors
        @Composable
        get() = LocalTodoAppColors.current

    val typography: TodoAppTypography
        @Composable
        get() = LocalTodoAppTypogarphy.current

    val shape: TodoAppShape
        @Composable
        get() = LocalTodoAppShape.current
}

enum class TodoAppStyle {
    Red, Green, Blue, Gray, LightGray, White
}

enum class TodoAppSize {
    Small, Large
}

enum class TodoAppCorners {
    FLat, Rounded
}

val LocalTodoAppColors = staticCompositionLocalOf<TodoAppColors> {
    error("No colors provided")
}

val LocalTodoAppTypogarphy = staticCompositionLocalOf<TodoAppTypography> {
    error("No font provided")
}

val LocalTodoAppShape = staticCompositionLocalOf<TodoAppShape> {
    error("No shapes provided")
}