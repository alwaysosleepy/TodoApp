package dev.dariakhartova.todoapp.ui.screens

data class TaskDetailState(
    val taskText: String?,
    val isDone: Boolean
)

class TaskDetailViewModel