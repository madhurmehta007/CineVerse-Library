package org.android.cineverse.shared.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.android.cineverse.shared.data.repository.MovieRepository
import org.android.cineverse.shared.domain.model.Movie

class MoviesViewModel(
    private val repository: MovieRepository,
    private val scope: CoroutineScope? = null 
) {
    private val viewModelScope = scope ?: CoroutineScope(Dispatchers.Main)

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    val movies: StateFlow<List<Movie>> = combine(
        repository.getMovies(),
        _searchQuery
    ) { movies, query ->
        if (query.isBlank()) {
            movies
        } else {
            movies.filter { it.title.contains(query, ignoreCase = true) }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val favorites: StateFlow<List<Movie>> = repository.getFavorites()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        viewModelScope.launch {
            repository.fetchMovies()
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun toggleFavorite(movieId: String) {
        viewModelScope.launch {
            repository.toggleFavorite(movieId)
        }
    }
}
