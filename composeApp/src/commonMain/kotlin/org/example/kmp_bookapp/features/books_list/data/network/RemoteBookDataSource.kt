package org.example.kmp_bookapp.features.books_list.data.network

import org.example.kmp_bookapp.core.domain.DataError
import org.example.kmp_bookapp.core.domain.Result
import org.example.kmp_bookapp.features.books_list.data.dto.SearchResponseDto

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, DataError.Remote>
}