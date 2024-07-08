package dev.dariakhartova.todoapp.data.model

import java.time.LocalDate

/** Implementation of model for UI */
data class TodoItem(
    val id: String,
    val description: String,
    val importance: Importance,
    val isDone: Boolean,
    val createdAt: LocalDate? = null,
    val changedAt: LocalDate? = null,
    val deadline: LocalDate? = null
)




