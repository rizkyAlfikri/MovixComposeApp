package com.example.movixcomposeapp.feature.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.movixcomposeapp.core.presentation.state.UiState
import com.example.movixcomposeapp.feature.presentation.components.RatingBarComponent
import com.example.movixcomposeapp.feature.presentation.components.ShimmerAnimationComponent
import com.example.movixcomposeapp.feature.presentation.model.MovieModel
import com.example.movixcomposeapp.feature.presentation.theme.BackgroundColor
import com.example.movixcomposeapp.feature.presentation.theme.MovixComposeAppTheme
import com.example.movixcomposeapp.feature.presentation.theme.RattingStartColor
import com.example.movixcomposeapp.feature.presentation.viewmodel.MovieViewModel
import com.example.movixcomposeapp.feature.presentation.viewmodel.MovieViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovixComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MoviePopularScreen(
                        onErrorCallback = {
                            Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviePopularScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel = viewModel(factory = MovieViewModelFactory.getInstance()),
    scrollState: ScrollState = rememberScrollState(),
    onErrorCallback: (String) -> Unit
) {
    Scaffold(
        topBar = { HeaderContent() }
    ) { innerPadding ->
        viewModel.moviesPopularState.collectAsState(initial = UiState.Empty).value.let { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    Column(
                        modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .verticalScroll(scrollState)
                            .background(BackgroundColor)
                    ) {
                        SearchContent()
                        MoviePopularContent(movies = uiState.data)
                    }
                }

                is UiState.Empty -> {
                    viewModel.getMoviesPopular()
                }

                is UiState.Error -> {
                    onErrorCallback(uiState.error)
                }

                is UiState.Loading -> {
                    Column(
                        modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .verticalScroll(scrollState)
                            .background(BackgroundColor)
                    ) {
                        SearchContent()
                        MoviePopularContentShimmer()
                    }
                }
            }
        }
    }
}

@Composable
private fun HeaderContent(modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color.White)
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = BackgroundColor),
            modifier = Modifier
                .clip(CircleShape)
                .size(42.dp)
                .padding(6.dp)
        ) {
            Image(
                painter = painterResource(com.example.movixcomposeapp.R.drawable.icon_movix),
                contentDescription = "Logo Icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier.clip(CircleShape)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(weight = 1F)) {
            Text(text = "Hello, Rizky Alfikri", style = MaterialTheme.typography.titleMedium)
            Text(
                text = "IDR 100.000", style = MaterialTheme.typography.titleMedium.copy(
                    color = RattingStartColor, fontSize = 14.sp
                )
            )
        }
    }
}

@Composable
private fun SearchContent(modifier: Modifier = Modifier) {
    Card(
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            Modifier
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = RectangleShape,
                colors = CardDefaults.cardColors(containerColor = BackgroundColor),
                modifier = Modifier
                    .height(28.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .weight(1F),
            ) {
                Row(modifier = Modifier.padding(start = 12.dp, top = 6.dp, bottom = 6.dp)) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "Search your movie here",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = Color(0x80000000), fontSize = 11.sp
                        ),
                    )
                }
            }

            Spacer(modifier = Modifier.width(20.dp))

            Card(
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = BackgroundColor),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(28.dp)
                    .background(BackgroundColor)
                    .padding(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite movie",
                    tint = Color(0xFFDF4759)
                )
            }
        }
    }
}

@Composable
private fun MoviePopularContent(movies: List<MovieModel>, modifier: Modifier = Modifier) {

    Column(modifier) {
        Text(
            text = "Popular Movie",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(start = 24.dp, top = 12.dp)
        )

        LazyRow(contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp)) {
            itemsIndexed(movies, key = { _, movie -> movie.id }) { index: Int, item: MovieModel ->
                MoviePopularCard(movie = item)
                if (index != movies.lastIndex) Spacer(modifier = Modifier.width(16.dp))
            }

        }

    }

}

@Composable
private fun MoviePopularCard(movie: MovieModel, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(210.dp)
            .height(140.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {

        AsyncImage(
            model = movie.backdropPath,
            contentDescription = movie.title,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .matchParentSize(),
            alignment = Alignment.Center
        )

        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.3F)),
            shape = RectangleShape,
            modifier = Modifier
                .clip(RoundedCornerShape(bottomEnd = 6.dp, bottomStart = 6.dp))
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),

            ) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.labelMedium.copy(lineHeight = 1.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 8.dp, start = 12.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.padding(start = 12.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RatingBarComponent(
                    modifier = Modifier.height(12.dp),
                    rating = movie.voteAverage / 2,
                    starsColor = RattingStartColor
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${movie.voteAverage / 2}/5",
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

@Composable
private fun MoviePopularContentShimmer(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {
    ShimmerAnimationComponent { brush ->
        Column(modifier) {
            Spacer(
                modifier = Modifier
                    .padding(start = 24.dp, top = 12.dp)
                    .height(18.dp)
                    .width(120.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(brush = brush)
            )

            Row(
                modifier = Modifier
                    .padding(PaddingValues(vertical = 16.dp))
                    .horizontalScroll(scrollState)
            ) {
                repeat(5) {
                    Spacer(
                        modifier = Modifier
                            .padding(start = 24.dp)
                            .width(210.dp)
                            .height(140.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(brush = brush)
                    )
                    if (it == 4) Spacer(modifier = Modifier.width(24.dp))
                }
            }
        }
    }
}