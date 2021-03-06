package com.example.movieapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.models.getMovies
import com.example.movieapp.navigation.MovieNavigation
import com.example.movieapp.screens.home.HomeScreen
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.viewmodel.FavouritesViewModel

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val vm: FavouritesViewModel by viewModels()

        setContent {
            MyApp {
                MovieNavigation()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MovieAppTheme {
        content()
    }
}


@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    //MovieAppTheme {
        //MyApp {

            //MainContent()
        //}
    //}
}
