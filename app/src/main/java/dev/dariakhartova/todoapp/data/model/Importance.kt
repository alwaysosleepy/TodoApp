package dev.dariakhartova.todoapp.data.model

import androidx.room.TypeConverter
import dev.dariakhartova.todoapp.R

/** List of available priority values */
enum class Importance(val value: Int){
    HIGH (R.string.priority_high),
    ORDINARY(R.string.priority_ordinary),
    LOW(R.string.priority_low)
}

class Converters {

    @TypeConverter
    fun toImportance(value: String) = enumValueOf<Importance>(value)

    @TypeConverter
    fun fromImportance(value: Importance) = value.name
}
