package dev.dariakhartova.todoapp.data.repository

import dev.dariakhartova.todoapp.data.model.TodoItem
import dev.dariakhartova.todoapp.data.model.Priority
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TodoItemRepositoryImpl: TodoItemRepository {

    private val todoItemList = getTodoList()

    override suspend fun getAllTodoItems(): Flow<List<TodoItem>> = flow {
        emit(getTodoList())
    }

    override suspend fun addTodoItem(todo: TodoItem){
        TODO()
    }

    override suspend fun deleteTodoItem(todo: TodoItem){
        TODO()
    }

    override suspend fun updateTodoItem(todo: TodoItem) {
        TODO()
    }


    private fun getTodoList(): List<TodoItem>{
        return listOf(
            TodoItem(
                id = "1",
                description = "Сделать что-то",
                priority = Priority.LOW,
                isDone = true
            ),
            TodoItem(
                id = "1",
                description = "Длинный текст Длинный текст Длинный текст " +
                        "Длинный текст Длинный текст Длинный текст Длинный текст Длинный текст " +
                        "Длинный текст Длинный текст Длинный текст Длинный текст Длинный текст " +
                        "Длинный текст Длинный текст Длинный текст Длинный текст " +
                        "Длинный текст Длинный текст Длинный текст Длинный текст Длинный текст " +
                        "Длинный текст Длинный текст Длинный текст Длинный текст Длинный текст " +
                        "Длинный текст Длинный текст Длинный текст Длинный текст Длинный текст ",
                priority = Priority.ORDINARY,
                isDone = true
            ),
            TodoItem(
                id = "1",
                description = "Захватить мир",
                priority = Priority.HIGH,
                isDone = true
            ),
            TodoItem(
                id = "1",
                description = "Сделать что-то",
                priority = Priority.ORDINARY,
                isDone = false
            ),
            TodoItem(
                id = "1",
                description = "Купить что-то",
                priority = Priority.ORDINARY,
                isDone = false
            ),
            TodoItem(
                id = "1",
                description = "Пожить",
                priority = Priority.LOW,
                isDone = false
            ),
            TodoItem(
                id = "1",
                description = "Длинный текст Длинный текст Длинный текст Длинный текст " +
                        "Длинный текст Длинный текст Длинный текст",
                priority = Priority.LOW,
                isDone = false
            ),
            TodoItem(
                id = "1",
                description = "Сделать что-то",
                priority = Priority.HIGH,
                isDone = true
            ),
            TodoItem(
                id = "1",
                description = "Сделать что-то",
                priority = Priority.ORDINARY,
                isDone = false
            ),
            TodoItem(
                id = "1",
                description = "Сделать что-то",
                priority = Priority.HIGH,
                isDone = true
            ),
            TodoItem(
                id = "1",
                description = "Длинный текст Длинный текст Длинный текст " +
                        "Длинный текст Длинный текст Длинный текст Длинный текст Длинный текст " +
                        "Длинный текст Длинный текст Длинный текст Длинный текст Длинный текст " +
                        "Длинный текст Длинный текст Длинный текст Длинный текст",
                priority = Priority.LOW,
                isDone = false
            ),
            TodoItem(
                id = "1",
                description = "Сделать что-то",
                priority = Priority.ORDINARY,
                isDone = true
            ),
            TodoItem(
                id = "1",
                description = "Сделать что-то",
                priority = Priority.ORDINARY,
                isDone = true
            )

        )

    }
}