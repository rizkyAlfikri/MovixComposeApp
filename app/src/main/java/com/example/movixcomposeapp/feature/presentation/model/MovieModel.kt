package com.example.movixcomposeapp.feature.presentation.model

import com.example.movixcomposeapp.BuildConfig
import com.example.movixcomposeapp.feature.data.model.MovieResult

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version MovieModel, v 0.1 4/16/2023 6:17 AM by Rizky Alfikri Rachmat
 */
class MovieModel(
    val id: Int,
    val backdropPath: String,
    val title: String,
    val voteAverage: Double
)

fun MovieResult.toMovieModel(): MovieModel = MovieModel(
    id = this.id,
    backdropPath = BuildConfig.IMAGE_URL_BASE_PATH + this.backdropPath,
    title = this.title,
    voteAverage = this.voteAverage,
)