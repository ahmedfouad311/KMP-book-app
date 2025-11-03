package org.example.kmp_bookapp.features.books.data.repository

import org.example.kmp_bookapp.core.domain.DataError
import org.example.kmp_bookapp.core.domain.Result
import org.example.kmp_bookapp.core.domain.map
import org.example.kmp_bookapp.core.mappers.tooBook
import org.example.kmp_bookapp.features.books.data.network.RemoteBookDataSource
import org.example.kmp_bookapp.features.books.domain.Book
import org.example.kmp_bookapp.features.books.domain.repository.BookRepository

class BookRepositoryImp (
    private val remoteDataSource: RemoteBookDataSource
): BookRepository {
    override
    suspend fun searchBooks(
        query: String
    ): Result<List<Book>, DataError.Remote> {
        return remoteDataSource.searchBooks(query)
            .map { dto ->
                dto.results.map { it.tooBook() }
        }
    }

    override
    suspend fun getBookDescription(
        bookId: String
    ) : Result<String?, DataError> {
        return remoteDataSource.getBookDetails(bookId)
            .map { it.description }
    }
}