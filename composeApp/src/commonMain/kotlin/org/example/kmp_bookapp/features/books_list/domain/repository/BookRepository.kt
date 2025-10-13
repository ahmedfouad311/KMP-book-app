package org.example.kmp_bookapp.features.books_list.domain.repository

import org.example.kmp_bookapp.core.domain.DataError
import org.example.kmp_bookapp.core.domain.Result
import org.example.kmp_bookapp.features.books_list.domain.Book

interface BookRepository {
    suspend fun searchBooks(
        query: String
    ): Result<List<Book>, DataError.Remote>
}