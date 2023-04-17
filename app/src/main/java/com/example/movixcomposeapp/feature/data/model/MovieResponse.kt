package com.example.movixcomposeapp.feature.data.model

import com.example.movixcomposeapp.core.data.network.model.BaseNetworkResponse
import com.google.gson.annotations.SerializedName

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version MovieResponse, v 0.1 4/16/2023 5:31 AM by Rizky Alfikri Rachmat
 */

class MovieResponse(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<MovieResult>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
) : BaseNetworkResponse()

class MovieResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double
)