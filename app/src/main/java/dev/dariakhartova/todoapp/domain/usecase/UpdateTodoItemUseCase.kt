package dev.dariakhartova.todoapp.domain.usecase

import dev.dariakhartova.todoapp.data.model.TodoItem
import dev.dariakhartova.todoapp.data.repository.TodoItemRepository

class UpdateTodoItemUseCase(private val repository: TodoItemRepository) {

    suspend operator fun invoke(todoItem: TodoItem) = repository.updateTodoItem(todoItem)
}