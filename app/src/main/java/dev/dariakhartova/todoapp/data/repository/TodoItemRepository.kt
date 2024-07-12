package dev.dariakhartova.todoapp.data.repository

import dev.dariakhartova.todoapp.data.model.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoItemRepository {

    suspend fun getAllTodoItems(): Flow<List<TodoItem>>

    suspend fun addTodoItem(todo: TodoItem)

    suspend fun updateTodoItem(todo: TodoItem)

    suspend fun deleteTodoItem(todoItemId: TodoItem)

}