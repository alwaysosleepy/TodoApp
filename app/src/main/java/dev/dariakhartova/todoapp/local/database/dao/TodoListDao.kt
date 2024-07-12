package dev.dariakhartova.todoapp.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.dariakhartova.todoapp.data.model.TodoItem
import dev.dariakhartova.todoapp.local.database.entity.TodoItemEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoListDao {

    @Query("SELECT * FROM todo_items")
    fun getAllTodoItems(): Flow<List<TodoItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTodoItem(todoItemEntity: TodoItemEntity)

    @Query("DELETE FROM todo_items WHERE id=:todoItemId")
    fun deleteTodoItem(todoItemId: String)

    @Query("SELECT * FROM todo_items WHERE id=:todoItemId LIMIT 1")
    fun getTodoItem(todoItemId: String): TodoItemEntity
}