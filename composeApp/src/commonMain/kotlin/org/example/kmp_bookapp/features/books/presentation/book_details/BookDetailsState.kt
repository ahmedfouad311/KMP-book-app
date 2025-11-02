package org.example.kmp_bookapp.features.books.presentation.book_details

import org.example.kmp_bookapp.features.books.domain.Book

data class BookDetailsState (
    val isLoading: Boolean = true,
    val isFavourite: Boolean = false,
    val book: Book? = null,
)