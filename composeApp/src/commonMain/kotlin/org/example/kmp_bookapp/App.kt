package org.example.kmp_bookapp

import androidx.compose.runtime.*
import org.example.kmp_bookapp.features.books_list.presentation.BookListScreenRoot
import org.example.kmp_bookapp.features.books_list.presentation.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    BookListScreenRoot(
        viewModel = remember { BookListViewModel() },
        onBookClicked = { /* TODO */ }
    )
}