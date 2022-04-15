package com.example.movieapp.screens.detail

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.viewmodel.FavouritesViewModel
import com.example.movieapp.widgets.FavouriteIcon
import com.example.movieapp.widgets.HorizontalScrollableImageView
import com.example.movieapp.widgets.MovieRow

@ExperimentalAnimationApi
@Composable
fun DetailScreen(
    navController: NavController = rememberNavController(), viewModel: FavouritesViewModel,
    movieId: String? = "tt0499549"
    ){

    val movie = filterMovie(movieId = movieId)

    Scaffold(
        topBar = {
            TopAppBar(elevation = 3.dp) {
                Row {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()        // go back to last screen
                        })

                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = movie.title)
                }
            }
        }
    ) {
        MainContent(movie = movie, viewModel)
    }
}

@ExperimentalAnimationApi
@Composable
fun MainContent(movie: Movie, viewModel: FavouritesViewModel) {
    
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Column {
            MovieRow(name = movie) {
                FavouriteIcon(isFavourite = viewModel.isFavourite(movie),
                    addToFavourite = {viewModel.addMovie(movie)},
                    removeFromFavourite = {viewModel.removeMovie(movie)})
            }

            Spacer(modifier = Modifier.height(8.dp))

            Divider()

            Text(text = movie.title, style = MaterialTheme.typography.h5)

            HorizontalScrollableImageView(movie = movie)

        }
    }
}

fun filterMovie(movieId: String?): Movie {

    return getMovies().filter { movie -> movie.id == movieId}[0]
}