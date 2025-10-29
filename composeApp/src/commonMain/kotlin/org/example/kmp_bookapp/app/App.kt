package org.example.kmp_bookapp.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.example.kmp_bookapp.features.books_list.presentation.BookListScreenRoot
import org.example.kmp_bookapp.features.books_list.presentation.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost (
            navController = navController,
            startDestination = Route.BookGraph
        ) {
            navigation<Route.BookGraph>(
                startDestination = Route.BookList
            ) {
                composable<Route.BookList> {
                    val viewModel = koinViewModel<BookListViewModel>()
                    BookListScreenRoot(
                        viewModel = viewModel,
                        onBookClicked = { book ->
                            navController.navigate(Route.BookDetails(book.id))
                        }
                    )
                }
                composable<Route.BookDetails> { entry ->
                    val args = entry.toRoute<Route.BookDetails>()

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("This is Details Screen fo.r book id: ${args.bookId}")
                    }
                }
            }
        }
    }
}