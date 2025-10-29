package org.example.kmp_bookapp.features.books_list.presentation

import org.example.kmp_bookapp.core.presentation.UiText
import org.example.kmp_bookapp.features.books_list.domain.Book

data class BookListState(
    val searchQuery: String = "",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = true,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)
