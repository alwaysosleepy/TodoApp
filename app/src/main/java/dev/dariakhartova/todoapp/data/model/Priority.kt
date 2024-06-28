package dev.dariakhartova.todoapp.data.model

import dev.dariakhartova.todoapp.R


enum class Priority(val value: Int){
    HIGH (R.string.priority_high),
    ORDINARY(R.string.priority_ordinary),
    LOW(R.string.priority_low)
}
