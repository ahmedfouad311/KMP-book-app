package org.example.kmp_bookapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.example.kmp_bookapp.features.books_list.presentation.BookListScreen
import org.example.kmp_bookapp.features.books_list.presentation.BookListState
import org.example.kmp_bookapp.features.books_list.presentation.books
import org.example.kmp_bookapp.features.books_list.presentation.components.BookSearchBar

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