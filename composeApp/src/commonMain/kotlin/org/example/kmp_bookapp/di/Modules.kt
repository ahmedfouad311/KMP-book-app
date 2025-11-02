package org.example.kmp_bookapp.di

import org.example.kmp_bookapp.core.data.HttpClientFactory
import org.example.kmp_bookapp.features.books.data.network.KtorRemoteBookDataSource
import org.example.kmp_bookapp.features.books.data.network.RemoteBookDataSource
import org.example.kmp_bookapp.features.books.data.repository.BookRepositoryImp
import org.example.kmp_bookapp.features.books.domain.repository.BookRepository
import org.example.kmp_bookapp.features.books.presentation.book_details.BookDetailsViewModel
import org.example.kmp_bookapp.features.books.presentation.books_list.BookListViewModel
import org.example.kmp_bookapp.features.books.presentation.books_list.SelectedBookViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }

    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::BookRepositoryImp).bind<BookRepository>()

    viewModelOf(::BookListViewModel)
    viewModelOf(::BookDetailsViewModel)
    viewModelOf(::SelectedBookViewModel)
}

