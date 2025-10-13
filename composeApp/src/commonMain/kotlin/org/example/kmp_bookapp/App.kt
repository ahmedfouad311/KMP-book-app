package org.example.kmp_bookapp

import androidx.compose.runtime.*
import io.ktor.client.engine.HttpClientEngine
import org.example.kmp_bookapp.core.data.HttpClientFactory
import org.example.kmp_bookapp.features.books_list.data.network.KtorRemoteBookDataSource
import org.example.kmp_bookapp.features.books_list.data.repository.BookRepositoryImp
import org.example.kmp_bookapp.features.books_list.presentation.BookListScreenRoot
import org.example.kmp_bookapp.features.books_list.presentation.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(engine: HttpClientEngine) {
    BookListScreenRoot(
        viewModel = remember {
            BookListViewModel(
                bookRepository = BookRepositoryImp(
                    remoteDataSource = KtorRemoteBookDataSource(
                        client = HttpClientFactory.create(
                            engine = engine
                        )
                    )
                )
            )
        },
        onBookClicked = { /* TODO */ }
    )
}