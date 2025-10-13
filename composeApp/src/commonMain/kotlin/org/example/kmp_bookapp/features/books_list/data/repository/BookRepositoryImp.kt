package org.example.kmp_bookapp.features.books_list.data.repository

import org.example.kmp_bookapp.core.domain.DataError
import org.example.kmp_bookapp.core.domain.Result
import org.example.kmp_bookapp.core.domain.map
import org.example.kmp_bookapp.core.mappers.tooBook
import org.example.kmp_bookapp.features.books_list.data.network.RemoteBookDataSource
import org.example.kmp_bookapp.features.books_list.domain.Book
import org.example.kmp_bookapp.features.books_list.domain.repository.BookRepository

class BookRepositoryImp (
    private val remoteDataSource: RemoteBookDataSource
): BookRepository {
    override
    suspend fun searchBooks(
        query: String
    ): Result<List<Book>, DataError.Remote>{
        return remoteDataSource.searchBooks(query)
            .map { dto ->
                dto.results.map { it.tooBook() }
        }
    }
}