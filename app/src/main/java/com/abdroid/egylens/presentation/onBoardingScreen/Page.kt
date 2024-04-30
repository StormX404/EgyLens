package com.abdroid.egylens.presentation.onBoardingScreen

import androidx.annotation.DrawableRes
import com.abdroid.egylens.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
)

val pages = listOf(
    Page(
        title = "EXPLORE LANDMARKS",
        description = "through Sign Language and Audio Narration.",
        image = R.drawable.onboarding_1
    ),
    Page(
        title = "LANDMARK DETECTION",
        description = "scan and learn about landmarks that you are visiting.",
        image = R.drawable.onboarding_2
    ),
    Page(
        title = "INTERACTIVE EXPLANATION",
        description = "This feature brings statues to life , allowing users to explore their history.",
        image = R.drawable.onboarding_3
    )
)