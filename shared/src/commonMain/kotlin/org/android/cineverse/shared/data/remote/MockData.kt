package org.android.cineverse.shared.data.remote

import org.android.cineverse.shared.domain.model.Movie

val mockMovies = listOf(
    Movie(
        id = "1",
        title = "Quantum Fury",
        posterUrl = "https://image.tmdb.org/t/p/w500/1E5baAaEse26fej7uHcjOgEE2t2.jpg", // Placeholder or local logic
        backdropUrl = "https://image.tmdb.org/t/p/w1280/1E5baAaEse26fej7uHcjOgEE2t2.jpg",
        rating = 8.5,
        releaseDate = "2024-03-15",
        duration = "2h 18m",
        synopsis = "In a world where time itself has become a weapon, elite operative Marcus Kane must navigate through fracturing realities to prevent a catastrophic temporal collapse. With stunning action sequences and mind-bending physics, Quantum Fury redesigns the modern action thriller.",
        director = "Sarah Mitchell",
        cast = listOf("Chris Harrison", "Maya Chen", "David Rodriguez"),
        genres = listOf("Action", "Sci-Fi"),
        isFavorite = false
    ),
    Movie(
        id = "2",
        title = "Civil War",
        posterUrl = "https://image.tmdb.org/t/p/w500/sh7Rg8Er3tFcN9BpKIPOMvALgZd.jpg", 
        backdropUrl = "https://image.tmdb.org/t/p/w1280/sh7Rg8Er3tFcN9BpKIPOMvALgZd.jpg",
        rating = 8.8,
        releaseDate = "2024-04-12",
        duration = "1h 49m",
        synopsis = "A journey across a dystopian future America, following a team of military-embedded journalists as they race against time to reach DC before rebel factions descend upon the White House.",
        director = "Alex Garland",
        cast = listOf("Kirsten Dunst", "Wagner Moura", "Cailee Spaeny"),
        genres = listOf("Action", "Thriller"),
        isFavorite = false
    ),
    Movie(
        id = "3",
        title = "Godzilla x Kong: The New Empire",
        posterUrl = "https://image.tmdb.org/t/p/w500/tM26baT1t9Z2zL6qVl7DOq4t7.jpg",
        backdropUrl = "https://image.tmdb.org/t/p/w1280/tM26baT1t9Z2zL6qVl7DOq4t7.jpg",
        rating = 9.1,
        releaseDate = "2024-03-29",
        duration = "1h 55m",
        synopsis = "Following their explosive showdown, Godzilla and Kong must reunite against a colossal undiscovered threat hidden within our world, challenging their very existence – and our own.",
        director = "Adam Wingard",
        cast = listOf("Rebecca Hall", "Brian Tyree Henry", "Dan Stevens"),
        genres = listOf("Action", "Sci-Fi", "Adventure"),
        isFavorite = false
    ),
    Movie(
        id = "4",
        title = "Dune: Part Two",
        posterUrl = "https://image.tmdb.org/t/p/w500/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg",
        backdropUrl = "https://image.tmdb.org/t/p/w1280/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg",
        rating = 8.3,
        releaseDate = "2024-03-01",
        duration = "2h 46m",
        synopsis = "Paul Atreides unites with Chani and the Fremen while on a warpath of revenge against the conspirators who destroyed his family. Facing a choice between the love of his life and the fate of the known universe, he endeavors to prevent a terrible future only he can foresee.",
        director = "Denis Villeneuve",
        cast = listOf("Timothée Chalamet", "Zendaya", "Rebecca Ferguson"),
        genres = listOf("Sci-Fi", "Adventure"),
        isFavorite = false
    ),
     Movie(
        id = "5",
        title = "Kung Fu Panda 4",
        posterUrl = "https://image.tmdb.org/t/p/w500/kDp1vUBnMpe8ak4rjgl3cLELqjU.jpg",
        backdropUrl = "https://image.tmdb.org/t/p/w1280/kDp1vUBnMpe8ak4rjgl3cLELqjU.jpg",
        rating = 7.1,
        releaseDate = "2024-03-08",
        duration = "1h 34m",
        synopsis = "Po is gearing up to become the spiritual leader of his Valley of Peace, but also needs someone to take his place as Dragon Warrior. As such, he will train a new kung fu practitioner for the spot and will encounter a villain called the Chameleon who conjures villains from the past.",
        director = "Mike Mitchell",
        cast = listOf("Jack Black", "Awkwafina", "Viola Davis"),
        genres = listOf("Animation", "Action", "Family"),
        isFavorite = false
    )
)
