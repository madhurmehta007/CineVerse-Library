package org.android.cineverse.shared.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.android.cineverse.shared.database.CineVerseDatabase

class FavoritesLocalDataSource(
    databaseDriverFactory: DatabaseDriverFactory
) {
    private val database = CineVerseDatabase(databaseDriverFactory.createDriver())
    private val queries = database.favoritesQueries
    
    /**
     * Get all favorite movie IDs as a Flow
     */
    fun getAllFavoriteIds(): Flow<Set<String>> {
        return queries.selectAll()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { it.toSet() }
    }
    
    /**
     * Add a movie to favorites
     */
    suspend fun addFavorite(movieId: String) {
        withContext(Dispatchers.IO) {
            queries.insert(movieId)
        }
    }
    
    /**
     * Remove a movie from favorites
     */
    suspend fun removeFavorite(movieId: String) {
        withContext(Dispatchers.IO) {
            queries.delete(movieId)
        }
    }
    
    /**
     * Check if a movie is in favorites
     */
    suspend fun isFavorite(movieId: String): Boolean {
        return withContext(Dispatchers.IO) {
            queries.exists(movieId).executeAsOne() > 0
        }
    }
    
    /**
     * Toggle favorite status and return new state
     */
    suspend fun toggleFavorite(movieId: String): Boolean {
        return withContext(Dispatchers.IO) {
            val currentlyFavorite = queries.exists(movieId).executeAsOne() > 0
            if (currentlyFavorite) {
                queries.delete(movieId)
                false
            } else {
                queries.insert(movieId)
                true
            }
        }
    }
}
