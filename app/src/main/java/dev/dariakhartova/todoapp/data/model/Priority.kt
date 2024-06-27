package dev.dariakhartova.todoapp.data.model

import androidx.annotation.StringRes
import dev.dariakhartova.todoapp.R


enum class Priority(@StringRes val priorityRes: Int){
    HIGH (R.string.priority_high),
    ORDINARY(R.string.priority_ordinary),
    LOW(R.string.priority_low)
}
