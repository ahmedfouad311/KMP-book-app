package org.example.kmp_bookapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.example.kmp_bookapp.features.books.domain.Book
import org.example.kmp_bookapp.features.books.presentation.books_list.BookListScreen
import org.example.kmp_bookapp.features.books.presentation.books_list.BookListState
import org.example.kmp_bookapp.features.books.presentation.books_list.components.BookSearchBar


@Preview
@Composable
private fun BookSearchBarPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray)
    ) {
        BookSearchBar(
            query = "",
            onQueryChange = {},
            onImeSearchAction = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

private val books = (1 .. 100).map {
    Book(
        id = it.toString(),
        title = "Book Title $it",
        authors = listOf("Ahmed Fouad"),
        imageUrl = "https://test.com",
        description = "Description",
        averageRating = 4.6789,
        languages = emptyList(),
        firstPublishYear = "2020",
        numPages = 100,
        numEditions = 5,
        ratingCount = 1234
    )
}

@Preview
@Composable
private fun BookListScreenPreview() {
    BookListScreen(
        state = BookListState(
            searchResults = books
        ),
        onAction = {}
    )
}