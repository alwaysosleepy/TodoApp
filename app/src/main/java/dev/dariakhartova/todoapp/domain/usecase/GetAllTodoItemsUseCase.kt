package dev.dariakhartova.todoapp.domain.usecase

import dev.dariakhartova.todoapp.data.repository.TodoItemRepository

class GetAllTodoItemsUseCase(private val repository: TodoItemRepository) {

    suspend operator fun invoke() = repository.getAllTodoItems()


}