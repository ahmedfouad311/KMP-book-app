package org.example.kmp_bookapp.features.books_list.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.kmp_bookapp.core.data.safeCall
import org.example.kmp_bookapp.core.domain.DataError
import org.example.kmp_bookapp.core.domain.Result
import org.example.kmp_bookapp.features.books_list.data.dto.SearchResponseDto

private const val BASE_URL = "https://openlibrary.org"

class KtorRemoteBookDataSource (
    private val client: HttpClient
) : RemoteBookDataSource {
    override suspend fun searchBooks(
        query: String,
        resultLimit: Int?
    ): Result<SearchResponseDto, DataError.Remote>{
        return safeCall {
            client.get(
                urlString = "$BASE_URL/search.json"
            ){
                parameter("q", query)
                parameter("limit", resultLimit)
                parameter("language", "eng")
                parameter("fields", "key,title,author_name,author_key,cover_edition_key,cover_i,ratings_average,ratings_count,first_publish_year,language,number_of_pages_median,edition_count")
            }
        }
    }
}