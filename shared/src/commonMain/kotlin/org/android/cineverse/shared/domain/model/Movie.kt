package org.android.cineverse.shared.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: String,
    val title: String,
    val posterUrl: String,
    val backdropUrl: String,
    val rating: Double,
    val releaseDate: String,
    val duration: String,
    val synopsis: String,
    val director: String,
    val cast: List<String>,
    val genres: List<String>,
    var isFavorite: Boolean = false
)
