package dev.dariakhartova.todoapp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.dariakhartova.todoapp.data.model.TodoItem
import dev.dariakhartova.todoapp.data.repository.TodoItemRepository
import dev.dariakhartova.todoapp.data.repository.TodoItemRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class State(val todoList: List<TodoItem>)

class HomeViewModel(private val repository: TodoItemRepository = TodoItemRepositoryImpl()) : ViewModel()  {
    private val _state: MutableStateFlow<State> = MutableStateFlow(State(emptyList()))
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllTodoItems().collect{
                _state.update { currentState ->
                    currentState.copy(todoList = it)
                }
            }
        }
    }

}
