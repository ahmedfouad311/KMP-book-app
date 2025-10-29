package org.example.kmp_bookapp.app

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object BookGraph: Route

    @Serializable
    data object BookList: Route

    @Serializable
    data class BookDetails(val bookId: String): Route
}