package com.example.movixcomposeapp.feature.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.floor

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version RatingBarComponent, v 0.1 4/16/2023 8:56 AM by Rizky Alfikri Rachmat
 */
@Composable
fun RatingBarComponent(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Yellow
) {
    val filledStar = floor(rating).toInt()
    val unfilledStar = stars - filledStar

    Row(modifier = modifier) {
        repeat(filledStar) {
            Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = starsColor)
        }

        repeat(unfilledStar) {
            Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = Color.Gray)
        }
    }
}

@Preview
@Composable
fun RatingBarPreview() {
    RatingBarComponent(
        rating = 4.5
    )
}