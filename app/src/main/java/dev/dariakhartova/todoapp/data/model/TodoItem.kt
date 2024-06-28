package dev.dariakhartova.todoapp.data.model

import java.time.LocalDate


data class TodoItem(
    val id: String,
    val description: String,
    val priority: Priority,
    val isDone: Boolean,
    val added: LocalDate? = null,
    val update: LocalDate? = null,
    val deadline: LocalDate? = null
)




