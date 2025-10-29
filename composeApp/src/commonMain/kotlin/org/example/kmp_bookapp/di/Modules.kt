package org.example.kmp_bookapp.di

import org.example.kmp_bookapp.core.data.HttpClientFactory
import org.example.kmp_bookapp.features.books_list.data.network.KtorRemoteBookDataSource
import org.example.kmp_bookapp.features.books_list.data.network.RemoteBookDataSource
import org.example.kmp_bookapp.features.books_list.data.repository.BookRepositoryImp
import org.example.kmp_bookapp.features.books_list.domain.repository.BookRepository
import org.example.kmp_bookapp.features.books_list.presentation.BookListViewModel
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
}

