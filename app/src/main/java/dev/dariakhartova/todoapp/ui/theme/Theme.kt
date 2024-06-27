package dev.dariakhartova.todoapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun ToDoAppTheme(
    style: TodoAppStyle = TodoAppStyle.Blue,
    textSize: TodoAppSize = TodoAppSize.Small,
    paddingSize: TodoAppSize = TodoAppSize.Small,
    corners: TodoAppCorners = TodoAppCorners.Rounded,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = when (darkTheme) {
        true -> {
            when (style) {
                TodoAppStyle.Red -> redDarkPallet
                TodoAppStyle.Green -> greenDarkPallet
                TodoAppStyle.Blue -> blueDarkPallet
                TodoAppStyle.Gray -> grayDarkPallet
                TodoAppStyle.White -> whiteDarkPallet
                TodoAppStyle.LightGray -> lightGrayDarkPallet

            }
        }
        false -> {
            when (style) {
                TodoAppStyle.Red -> redLightPallet
                TodoAppStyle.Green -> greenLightPallet
                TodoAppStyle.Blue -> blueLightPallet
                TodoAppStyle.Gray -> grayLightPallet
                TodoAppStyle.White -> whiteLightPallet
                TodoAppStyle.LightGray -> lightGrayLightPallet
            }

        }
    }
}
