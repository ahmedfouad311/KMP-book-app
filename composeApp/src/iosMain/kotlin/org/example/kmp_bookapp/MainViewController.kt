package org.example.kmp_bookapp

import androidx.compose.ui.window.ComposeUIViewController
import org.example.kmp_bookapp.app.App
import org.example.kmp_bookapp.di.initKoin

fun MainViewController() = ComposeUIViewController (
    configure = {
        initKoin()
    }
) { App() }