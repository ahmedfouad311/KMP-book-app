package org.example.kmp_bookapp.features.books_list.presentation

import org.example.kmp_bookapp.core.presentation.UiText
import org.example.kmp_bookapp.features.books_list.domain.Book

data class BookListState(
    val searchQuery: String = "",
    val searchResults: List<Book> = books,
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)


val books = (1 .. 100).map {
    Book(
        id = it.toString(),
        title = "Book Title $it",
        authors = listOf("Ahmed Fouad"),
        imageUrl = "https://test.com",
        description = "Description",
        averageRating = 4.6789,
        languages = emptyList(),
        firstPublishYear = "2020",
        numPages = 100,
        numEditions = 5,
        ratingCount = 1234
    )
}
