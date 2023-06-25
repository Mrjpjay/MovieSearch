package com.example.moviesearch.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
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
    val loading by viewModel.loading.collectAsState()
    MyProgressBar(loading)
    Column {
        MyTextField(viewModel)
        LazyColumn(
            contentPadding = PaddingValues(6.dp)
        ) {
            items(list) { movie: Movie ->
                MovieCard(movie)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(vModel: MovieViewModel) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 6.dp, end = 6.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        TextField(
            value = text,
            placeholder = { Text(text = stringResource(R.string.search_for_movies))},
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            onValueChange = {
                text = it
                vModel.clearList()
                vModel.searchMovie(it.text)
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledLabelColor = Color.Transparent,
            )
        )
    }
}

@Composable
fun MyProgressBar(loading: Boolean) {
    if (loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
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
            .padding(bottom = 6.dp)
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
}
