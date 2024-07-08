package dev.dariakhartova.todoapp.data.repository

import dev.dariakhartova.todoapp.data.model.TodoItem
import dev.dariakhartova.todoapp.data.model.Importance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**  Implementation for [TodoItemRepository] */
class TodoItemRepositoryImpl: TodoItemRepository {

    private val todoItemList = getTodoItemsMock()

    override suspend fun getAllTodoItems(): Flow<List<TodoItem>> = flow {
        emit(getTodoItemsMock())
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

    private fun getTodoItemList(): List<TodoItem> {
        return listOf(

        )
    }


    private fun getTodoItemsMock(): List<TodoItem>{
        return listOf(
            TodoItem(
                id = "1",
                description = "Сделать что-то",
                importance = Importance.LOW,
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
                importance = Importance.ORDINARY,
                isDone = true
            ),
            TodoItem(
                id = "1",
                description = "Захватить мир",
                importance = Importance.HIGH,
                isDone = true
            ),
            TodoItem(
                id = "1",
                description = "Сделать что-то",
                importance = Importance.ORDINARY,
                isDone = false
            ),
            TodoItem(
                id = "1",
                description = "Купить что-то",
                importance = Importance.ORDINARY,
                isDone = false
            ),
            TodoItem(
                id = "1",
                description = "Пожить",
                importance = Importance.LOW,
                isDone = false
            ),
            TodoItem(
                id = "1",
                description = "Длинный текст Длинный текст Длинный текст Длинный текст " +
                        "Длинный текст Длинный текст Длинный текст",
                importance = Importance.LOW,
                isDone = false
            ),
            TodoItem(
                id = "1",
                description = "Сделать что-то",
                importance = Importance.HIGH,
                isDone = true
            ),
            TodoItem(
                id = "1",
                description = "Сделать что-то",
                importance = Importance.ORDINARY,
                isDone = false
            ),
            TodoItem(
                id = "1",
                description = "Сделать что-то",
                importance = Importance.HIGH,
                isDone = true
            ),
            TodoItem(
                id = "1",
                description = "Длинный текст Длинный текст Длинный текст " +
                        "Длинный текст Длинный текст Длинный текст Длинный текст Длинный текст " +
                        "Длинный текст Длинный текст Длинный текст Длинный текст Длинный текст " +
                        "Длинный текст Длинный текст Длинный текст Длинный текст",
                importance = Importance.LOW,
                isDone = false
            ),
            TodoItem(
                id = "1",
                description = "Сделать что-то",
                importance = Importance.ORDINARY,
                isDone = true
            ),
            TodoItem(
                id = "1",
                description = "Сделать что-то",
                importance = Importance.ORDINARY,
                isDone = true
            )

        )

    }
}