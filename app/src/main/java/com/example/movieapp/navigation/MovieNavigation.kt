package com.example.movieapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.screens.detail.DetailScreen
import com.example.movieapp.screens.favourites.FavouriteScreen
import com.example.movieapp.screens.home.HomeScreen
import com.example.movieapp.viewmodel.FavouritesViewModel

@ExperimentalAnimationApi
@Composable
fun MovieNavigation() {

    val navController = rememberNavController()
    val favouritesViewModel: FavouritesViewModel = viewModel()

    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name) {
        composable(MovieScreens.HomeScreen.name) {
            HomeScreen(navController = navController, viewModel = favouritesViewModel)
        }

        composable(MovieScreens.DetailScreen.name + "/{movie}",
            arguments = listOf(navArgument("movie") {
                type = NavType.StringType
            })) {
            backStackEntry ->

            DetailScreen(navController = navController, movieId = backStackEntry.arguments?.getString("movie"), viewModel = favouritesViewModel)
        }
        // add more routes and screens here

        composable(route = MovieScreens.FavouriteScreen.name) {
            FavouriteScreen(navController = navController, viewModel = favouritesViewModel)
        }

        }
    }
