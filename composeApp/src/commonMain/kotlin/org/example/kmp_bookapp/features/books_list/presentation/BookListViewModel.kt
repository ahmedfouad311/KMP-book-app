package org.example.kmp_bookapp.features.books_list.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BookListViewModel : ViewModel() {
    private val _state = MutableStateFlow(BookListState())
    val state: StateFlow<BookListState> = _state.asStateFlow()

    fun onAction(action: BookListAction) {
        when (action) {
            is BookListAction.OnQueryChange -> {
                _state.value = _state.value.copy(searchQuery = action.query)
            }
            is BookListAction.OnBookClick -> {
                // Handle book click
            }
            is BookListAction.OnTabSelected -> {
                _state.value = _state.value.copy(selectedTabIndex = action.index)
            }
            is BookListAction.OnToggleFavoriteClick -> {
                // Handle toggle favorite
            }
        }
    }
}