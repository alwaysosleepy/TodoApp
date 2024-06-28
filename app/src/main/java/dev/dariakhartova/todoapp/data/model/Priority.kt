package dev.dariakhartova.todoapp.data.model

import androidx.annotation.StringRes
import dev.dariakhartova.todoapp.R


enum class Priority(val value: String){
    HIGH ("priority_high"),
    ORDINARY("priority_ordinary"),
    LOW("priority_low")
}
