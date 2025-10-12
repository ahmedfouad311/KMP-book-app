package org.example.kmp_bookapp.features.books_list.presentation

import org.example.kmp_bookapp.features.books_list.domain.Book

interface BookListAction {
    data class OnQueryChange(val query: String) : BookListAction
    data class OnBookClick(val book: Book) : BookListAction
    data class OnTabSelected(val index: Int) : BookListAction
    data class OnToggleFavoriteClick(val book: Book) : BookListAction
}