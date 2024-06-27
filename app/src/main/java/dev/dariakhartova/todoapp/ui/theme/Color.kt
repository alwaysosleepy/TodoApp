package dev.dariakhartova.todoapp.ui.theme

import androidx.compose.ui.graphics.Color

val baseLightPallet = TodoAppColors(
    separator = Color(0x33000000),
    overlay = Color(0x0F000000),
    primaryBackground = Color(0xFFF7F6F2),
    primaryText = Color(0x00000000),
    secondaryBackground = Color(0xFFFFFFFF),
    secondaryText = Color(0x99000000),
    tertiaryText = Color(0x4D000000),
    disable = Color(0x26000000),
    elevated = Color(0xFFFFFFFF),
    tintColor = Color.Magenta,
)

val baseDarkPallet = TodoAppColors(
    separator = Color(0x33FFFFFF),
    overlay = Color(0x52000000),
    primaryBackground = Color(0xFF161618),
    primaryText = Color(0xFFFFFFFF),
    secondaryBackground = Color(0xFF252528),
    secondaryText = Color(0x99FFFFFF),
    tertiaryText = Color(0x66FFFFFF),
    disable = Color(0x26FFFFFF),
    elevated = Color(0xFF3C3C3F),
    tintColor = Color.Magenta,
)

val redLightPallet = baseLightPallet.copy(
    tintColor = Color(0xFFFF3B30)
)

val redDarkPallet = baseDarkPallet.copy(
    tintColor = Color(0xFFFF453A)
)

val greenLightPallet = baseLightPallet.copy(
    tintColor = Color(0xFF34C759)
)

val greenDarkPallet = baseDarkPallet.copy(
    tintColor = Color(0xFF32D74B)
)

val blueLightPallet = baseLightPallet.copy(
    tintColor = Color(0xFF007AFF)
)

val blueDarkPallet = baseDarkPallet.copy(
    tintColor = Color(0xFF0A84FF)
)

val grayLightPallet = baseLightPallet.copy(
    tintColor = Color(0xFF8E8E93)
)

val grayDarkPallet = baseDarkPallet.copy(
    tintColor = Color(0xFF8E8E93)
)

val lightGrayLightPallet = baseLightPallet.copy(
    tintColor = Color(0xFFD1D1D6)
)

val lightGrayDarkPallet = baseDarkPallet.copy(
    tintColor = Color(0xFF48484A)
)

val whiteLightPallet = baseLightPallet.copy(
    tintColor = Color(0xFFFFFFFF)
)

val whiteDarkPallet = baseDarkPallet.copy(
    tintColor = Color(0xFFFFFFFF)
)






