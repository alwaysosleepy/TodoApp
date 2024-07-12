package dev.dariakhartova.todoapp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.dariakhartova.todoapp.data.model.TodoItem
import dev.dariakhartova.todoapp.data.repository.TodoItemRepository
import dev.dariakhartova.todoapp.data.repository.TodoItemRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/** Home Screen state */
data class HomeState(
    val todoList: List<TodoItem>,
    val isError: Boolean = false
)

/** ViewModel for Home Screen */
class HomeViewModel(private val repository: TodoItemRepository = TodoItemRepositoryImpl()) :
    ViewModel() {
    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState(emptyList()))
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllTodoItems().flowOn(Dispatchers.IO).catch {
                _state.update { currentState ->
                    currentState.copy(isError = true)
                }
            }.collect {
                _state.update { currentState ->
                    currentState.copy(todoList = it)
                }
            }
        }
    }

    fun onSnackBarShown() {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(isError = false)
            }
        }
    }
}
