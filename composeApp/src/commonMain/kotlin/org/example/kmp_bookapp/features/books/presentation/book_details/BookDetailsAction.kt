package org.example.kmp_bookapp.features.books.presentation.book_details

import org.example.kmp_bookapp.features.books.domain.Book

sealed interface BookDetailsAction {
    data object OnBackClick: BookDetailsAction
    data object OnFavouriteClick: BookDetailsAction
    data class OnSelectedBookChange(val book: Book) : BookDetailsAction
}