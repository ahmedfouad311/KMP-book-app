package org.example.kmp_bookapp.features.books.data.mappers

import org.example.kmp_bookapp.features.books.data.database.BookEntity
import org.example.kmp_bookapp.features.books.data.dto.SearchedBookDto
import org.example.kmp_bookapp.features.books.domain.Book


fun SearchedBookDto.tooBook(): Book {
    return Book(
        id = id,
        title = title,
        imageUrl = if(coverKey != null){
            "https://covers.openlibrary.org/b/olid/${coverKey}-L.jpg"
        } else {
            "https://covers.openlibrary.org/b/olid/${coverAlternativeKey}-L.jpg"
        },
        authors = authorNames ?: emptyList(),
        description = null,
        languages = languages ?: emptyList(),
        firstPublishYear = firstPublishYear?.toString(),
        averageRating = ratingsAverage,
        ratingCount = ratingsCount,
        numPages = numPagesMedian,
        numEditions = numEditions ?: 0,
    )
}

fun Book.toBookEntity() : BookEntity {
    return BookEntity(
        id= id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        languages = languages,
        authors = authors,
        firstPublishYear = firstPublishYear,
        ratingAverage = averageRating,
        ratingCount = ratingCount,
        numPagesMedian = numPages,
        numEdition = numEditions
    )
}