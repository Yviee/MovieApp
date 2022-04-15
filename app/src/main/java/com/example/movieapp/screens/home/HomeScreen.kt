package com.example.movieapp.screens.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.viewmodel.FavouritesViewModel
import com.example.movieapp.widgets.FavouriteIcon
import com.example.movieapp.widgets.MovieRow

@ExperimentalAnimationApi
@Composable
fun HomeScreen(navController: NavController = rememberNavController(), viewModel: FavouritesViewModel){

    MovieAppTheme {
        var showMenu by remember {
            mutableStateOf(false)
        }
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Movies") },
                    actions = {
                        IconButton(onClick = { showMenu = !showMenu }) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "more")
                        }
                        DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                            DropdownMenuItem(onClick = {
                            navController.navigate(route = MovieScreens.FavouriteScreen.name)
                            /*TODO*/ }) {
                                Row {
                                    Icon(imageVector = Icons.Default.Favorite,
                                        contentDescription = "favourites",
                                        modifier = Modifier.padding(4.dp))
                                    Text(text = "Favourites",
                                        modifier = Modifier
                                            .padding(4.dp)
                                            .width(100.dp))
                                }
                            }
                        }
                    }
                )
            }
        ) {
            MainContent(navController = navController, viewModel)
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun MainContent(navController: NavController, viewModel: FavouritesViewModel, movies: List<Movie> = getMovies()){

    LazyColumn {
        items(movies) { movie ->
            MovieRow(
                name = movie,
                onItemClick = { movieId ->
                    navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId")
                }
            ) {
                FavouriteIcon(isFavourite = viewModel.isFavourite(movie),
                    addToFavourite = { viewModel.addMovie(movie) },
                    removeFromFavourite = { viewModel.removeMovie(movie) })
            }
        }
    }
}