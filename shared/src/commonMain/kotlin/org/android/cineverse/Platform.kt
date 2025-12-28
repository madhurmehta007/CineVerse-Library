package org.android.cineverse

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform