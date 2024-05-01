package com.abdroid.egylens.presentation.favorites

import com.abdroid.egylens.domain.model.Statue

data class FavoritesState(
    val statues: List<Statue> = emptyList()
)