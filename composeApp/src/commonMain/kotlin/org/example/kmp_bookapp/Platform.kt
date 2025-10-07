package org.example.kmp_bookapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform