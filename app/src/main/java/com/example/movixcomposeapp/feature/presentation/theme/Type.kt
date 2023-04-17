package com.example.movixcomposeapp.feature.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val ralewayMediumFont = FontFamily(Font(com.example.movixcomposeapp.R.font.raleway_medium))

// Set of Material typography styles to start with
val Typography = Typography(
    titleMedium = TextStyle(
        fontFamily = ralewayMediumFont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 21.sp,
        letterSpacing = 0.5.sp,
        color = Color.Black
    ),
    headlineMedium = TextStyle(
        fontFamily = ralewayMediumFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 19.sp,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
    labelMedium = TextStyle(
        fontFamily = ralewayMediumFont,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        color = Color.White
    )
)