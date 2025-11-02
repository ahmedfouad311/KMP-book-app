package org.example.kmp_bookapp.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.example.kmp_bookapp.features.books.presentation.book_details.BookDetailsAction
import org.example.kmp_bookapp.features.books.presentation.book_details.BookDetailsScreenRoot
import org.example.kmp_bookapp.features.books.presentation.book_details.BookDetailsViewModel
import org.example.kmp_bookapp.features.books.presentation.books_list.BookListScreenRoot
import org.example.kmp_bookapp.features.books.presentation.books_list.BookListViewModel
import org.example.kmp_bookapp.features.books.presentation.books_list.SelectedBookViewModel
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
                    val selectedBookViewModel = it.sharedKoinViewModel<SelectedBookViewModel>(navController)

                    LaunchedEffect(true) {
                        selectedBookViewModel.onSelectedBook(null)
                    }

                    BookListScreenRoot(
                        viewModel = viewModel,
                        onBookClicked = { book ->
                            selectedBookViewModel.onSelectedBook(book)
                            navController.navigate(Route.BookDetails(book.id))
                        }
                    )
                }
                composable<Route.BookDetails> {
                    val selectedBookViewModel = it.sharedKoinViewModel<SelectedBookViewModel>(navController)
                    val bookDetailsViewModel = koinViewModel<BookDetailsViewModel>()

                    val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()

                    LaunchedEffect(selectedBook){
                        selectedBook?.let {
                            bookDetailsViewModel.onAction(BookDetailsAction.OnSelectedBookChange(book = it))
                        }
                    }

                    BookDetailsScreenRoot(
                        viewModel = bookDetailsViewModel,
                        onBackClick = {
                            navController.navigateUp()
                        }
                    )
                }
            }
        }
    }
}


@Composable
private inline fun <reified T: ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}