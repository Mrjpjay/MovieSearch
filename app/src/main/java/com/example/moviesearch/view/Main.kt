package com.example.moviesearch.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.moviesearch.R
import com.example.moviesearch.api.data.Movie
import com.example.moviesearch.viewmodel.MovieViewModel

@Composable
fun MovieList() {
    val viewModel = viewModel(modelClass = MovieViewModel::class.java)
    val list by viewModel.list.collectAsState()
    LazyColumn {
        items(list) { movie: Movie ->
            MovieCard(movie)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieCard(movie: Movie) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .padding(1.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(12.dp)
        ) {
            GlideImage(
                model = movie.poster,
                contentDescription = "movie poster",
                modifier = Modifier
                    .wrapContentHeight()
            )

            Column {
                Text(
                    text = "${movie.title}",
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = "${movie.description}",
                    modifier = Modifier.padding(start = 8.dp),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview()
@Composable
fun Preview() {
    MovieCard(
        movie = Movie(
            "Pablito",
            "El Mejor",
            "${R.drawable.ic_launcher_background}"
        )
    )
}
