package org.android.cineverse.shared.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.android.cineverse.shared.domain.model.Movie

interface MovieRemoteDataSource {
    suspend fun getMovies(): List<Movie>
    suspend fun getMovie(id: String): Movie?
    suspend fun searchMovies(query: String): List<Movie>
}

class KtorMovieDataSource(private val client: HttpClient) : MovieRemoteDataSource {
    override suspend fun getMovies(): List<Movie> {
        return client.get("/movies").body()
    }

    override suspend fun getMovie(id: String): Movie? {
        // In a real API, this would be /movie/{id}
        // For our mock, we might just fetch all and find
        val movies = getMovies()
        return movies.find { it.id == id }
    }

    override suspend fun searchMovies(query: String): List<Movie> {
        val movies = getMovies()
        return movies.filter { it.title.contains(query, ignoreCase = true) }
    }
}
