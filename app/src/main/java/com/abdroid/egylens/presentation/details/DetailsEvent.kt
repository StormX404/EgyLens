package com.abdroid.egylens.presentation.details

import com.abdroid.egylens.domain.model.Statue


sealed class DetailsEvent {
    data class UpsertDeleteStatue(val statue: Statue) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()

}