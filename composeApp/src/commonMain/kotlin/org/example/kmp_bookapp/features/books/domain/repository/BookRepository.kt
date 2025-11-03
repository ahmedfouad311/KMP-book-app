package org.example.kmp_bookapp.features.books.domain.repository

import org.example.kmp_bookapp.core.domain.DataError
import org.example.kmp_bookapp.core.domain.Result
import org.example.kmp_bookapp.features.books.domain.Book

interface BookRepository {
    suspend fun searchBooks(
        query: String
    ): Result<List<Book>, DataError.Remote>

    suspend fun getBookDescription(
        bookId: String
    ) : Result<String? , DataError>
}