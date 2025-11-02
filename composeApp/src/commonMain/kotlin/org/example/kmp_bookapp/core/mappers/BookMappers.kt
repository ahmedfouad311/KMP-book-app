package org.example.kmp_bookapp.core.mappers

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