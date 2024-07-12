package dev.dariakhartova.todoapp.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import dev.dariakhartova.todoapp.data.model.Converters
import dev.dariakhartova.todoapp.data.model.Importance
import java.time.LocalDate

@TypeConverters(Converters::class)
@Entity(tableName = "todo_items")
data class TodoItemEntity(
    @PrimaryKey
    val id: String,
    val description: String,
    val importance: Importance,
    val isDone: Boolean,
    val createdAt: Long? = null,
    val changedAt: Long? = null,
    val deadline: Long? = null
) {

}
