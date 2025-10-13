@file:OptIn(FlowPreview::class)

package org.example.kmp_bookapp.features.books_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.kmp_bookapp.core.domain.onError
import org.example.kmp_bookapp.core.domain.onSuccess
import org.example.kmp_bookapp.core.presentation.toUiText
import org.example.kmp_bookapp.features.books_list.domain.Book
import org.example.kmp_bookapp.features.books_list.domain.repository.BookRepository

class BookListViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {

    private var cachedBooks = emptyList<Book>()
    private var searchJob: Job? = null
    private val _state = MutableStateFlow(BookListState())
    val state =
        _state
            .onStart {
                if(cachedBooks.isEmpty()){
                    observeSearchQuery()
                }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000L),
                _state.value
            )

    fun onAction(action: BookListAction) {
        when (action) {
            is BookListAction.OnQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }
            is BookListAction.OnBookClick -> {
                // Handle book click
            }
            is BookListAction.OnTabSelected -> {
                _state.update {
                    it.copy(selectedTabIndex = action.index)
                }
            }
            is BookListAction.OnToggleFavoriteClick -> {
                // Handle toggle favorite
            }
        }
    }

    private fun observeSearchQuery() {
        state
            .map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update { it.copy(
                            errorMessage = null,
                            searchResults = cachedBooks
                        ) }
                    }
                    query.length >= 2 -> {
                        searchJob?.cancel()
                        searchJob = searchBooks(query)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun searchBooks(query: String) =
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
            ) }
            bookRepository
                .searchBooks(query)
                .onSuccess { searchResults ->
                    println("Search success: ${searchResults.size}")
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = null,
                            searchResults = searchResults
                    ) }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.toUiText(),
                            searchResults = emptyList()
                    ) }
                }
        }
}