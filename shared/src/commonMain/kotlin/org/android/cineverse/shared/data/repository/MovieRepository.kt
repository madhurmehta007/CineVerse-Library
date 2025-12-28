package org.android.cineverse.shared.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import org.android.cineverse.shared.data.local.FavoritesLocalDataSource
import org.android.cineverse.shared.data.remote.MovieRemoteDataSource
import org.android.cineverse.shared.domain.model.Movie

class MovieRepository(
    private val remoteDataSource: MovieRemoteDataSource,
    private val favoritesDataSource: FavoritesLocalDataSource
) {
    
    // Cache APIs results
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    
    suspend fun fetchMovies() {
        try {
            val remoteMovies = remoteDataSource.getMovies()
            _movies.value = remoteMovies
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    fun getMovies(): Flow<List<Movie>> {
        return combine(_movies, favoritesDataSource.getAllFavoriteIds()) { movies, favorites ->
            movies.map { movie ->
                 movie.copy(isFavorite = favorites.contains(movie.id))
            }
        }
    }
    
    fun getFavorites(): Flow<List<Movie>> {
         return combine(_movies, favoritesDataSource.getAllFavoriteIds()) { movies, favorites ->
            movies.filter { favorites.contains(it.id) }
                .map { it.copy(isFavorite = true) }
        }
    }

    fun getMovie(id: String): Flow<Movie?> {
        return combine(_movies, favoritesDataSource.getAllFavoriteIds()) { movies, favorites ->
            val movie = movies.find { it.id == id }
            movie?.copy(isFavorite = favorites.contains(id))
        }
    }
    
    suspend fun toggleFavorite(movieId: String) {
        favoritesDataSource.toggleFavorite(movieId)
    }
    
    suspend fun searchMovies(query: String): List<Movie> {
         if (_movies.value.isEmpty()) fetchMovies()
         
         // We need current favorites for the search results
         var currentFavorites = emptySet<String>()
         favoritesDataSource.getAllFavoriteIds().collect { currentFavorites = it }
         
         return _movies.value.filter { 
             it.title.contains(query, ignoreCase = true) 
         }.map { 
             it.copy(isFavorite = currentFavorites.contains(it.id))
         }
    }
}
