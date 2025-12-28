package org.android.cineverse.shared.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.android.cineverse.shared.data.local.DatabaseDriverFactory
import org.android.cineverse.shared.data.local.FavoritesLocalDataSource
import org.android.cineverse.shared.data.remote.KtorMovieDataSource
import org.android.cineverse.shared.data.remote.MovieRemoteDataSource
import org.android.cineverse.shared.data.remote.mockMovies
import org.android.cineverse.shared.data.repository.MovieRepository
import org.android.cineverse.shared.presentation.MoviesViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(sharedModule)
}

val sharedModule = module {
    single {
        val mockEngine = MockEngine { _ ->
            respond(
                content = Json.encodeToString(mockMovies),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                })
            }
        }
    }
    
    single<MovieRemoteDataSource> { KtorMovieDataSource(get()) }
    
    // Database - FavoritesLocalDataSource depends on DatabaseDriverFactory which is platform-specific
    single { FavoritesLocalDataSource(get()) }
    
    // Repository now depends on FavoritesLocalDataSource
    single { MovieRepository(get(), get()) }
    
    factory { MoviesViewModel(get()) }
}
