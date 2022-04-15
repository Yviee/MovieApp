package com.example.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.ui.theme.Teal200
import com.example.movieapp.viewmodel.FavouritesViewModel

@ExperimentalAnimationApi
@Composable
fun MovieRow(
    name: Movie,
    onItemClick: (String) -> Unit = {},
    content: @Composable () -> Unit = {}
) {

    var toggleArrow by remember {
        mutableStateOf(false)
    }

    Card(modifier = Modifier
        .padding(6.dp)
        .fillMaxWidth()
        //.fillMaxHeight()
        //.height(130.dp)
        .clickable {
            onItemClick(name.id)
        },
        shape = RoundedCornerShape(corner = CornerSize(6.dp)),
        elevation = 10.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            //horizontalArrangement = Arrangement.Start
        ) {
            Surface(modifier = Modifier
                .padding(12.dp)
                .size(100.dp)
                //.shadow(10.dp)
            ) {
                //Icon(imageVector = Icons.Default.AccountBox, contentDescription = "AccountImage")
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data = name.images[0])
                        .crossfade(true)
                        .build(),
                    contentDescription = "Movie poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)
                )
            }

            Column(modifier = Modifier.padding(4.dp)) {
                Text(
                    name.title,
                    style = MaterialTheme.typography.h6)
                Text("Director: ${name.director}")
                Text("Released:  ${name.year}")

                AnimatedVisibility(visible = toggleArrow) {

                    Column(modifier = Modifier.padding(start = 10.dp)) {
                        Text("Plot: ${name.plot}")

                        Canvas(modifier = Modifier.fillMaxWidth()) {
                            val canvasWidth = size.width

                            drawLine(
                                start = Offset(x = canvasWidth, y = 0f),
                                end = Offset(x = 0f, y = 0f),
                                color = Color.Gray
                            )
                        }

                        Text("Genre: ${name.genre}")
                        Text("Actors: ${name.actors}")
                        Text("Rating: ${name.rating}")
                    }
                }
                Icon(imageVector = if (toggleArrow) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "DownArrow",
                    modifier = Modifier.clickable { toggleArrow = !toggleArrow })

            }
            content()
        }
    }
}

@Composable
fun FavouriteIcon(
    isFavourite: Boolean = false,
    addToFavourite:() -> Unit = {},
    removeFromFavourite:() -> Unit = {},
) {

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.End) {
        if (!isFavourite) {
            IconButton(
                onClick = { addToFavourite() }) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favourite",
                    tint = Teal200)
            }
        } else {
            IconButton(
                onClick = { removeFromFavourite() }) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Non Favourite",
                    tint = Teal200)
            }
        }
    }
}

@Composable
fun HorizontalScrollableImageView(movie: Movie = getMovies()[0]) {
    LazyRow {
        items(movie.images) {
            image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = 4.dp
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data = image)
                        .build(),
                    contentDescription = "Movie image"
                )
            }
        }
    }
}