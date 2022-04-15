package com.example.movieapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.movieapp.models.Movie

class FavouritesViewModel: ViewModel() {

    private val _favouriteMovies = mutableStateListOf<Movie>()
    val favouriteMovies: List <Movie>
    get() = _favouriteMovies

    fun addMovie(movie: Movie){
        if(!exists(movie = movie))
        _favouriteMovies.add(movie)
    }

    fun removeMovie(movie: Movie){
        _favouriteMovies.remove(movie)
    }

    fun getAllMovies(): List<Movie> {
        return _favouriteMovies
    }

    private fun exists(movie: Movie): Boolean {
        return _favouriteMovies.any { m -> m.id == movie.id }
    }

    fun isFavourite (movie: Movie): Boolean {
        return(exists(movie))
    }
}