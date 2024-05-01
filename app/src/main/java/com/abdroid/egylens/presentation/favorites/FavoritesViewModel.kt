package com.abdroid.egylens.presentation.favorites

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdroid.egylens.domain.usecases.statues.GetSavedStatue
import com.abdroid.egylens.domain.usecases.statues.GetSavedStatues
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getSavedStatuesUseCase: GetSavedStatues
) : ViewModel() {

    private val _state = mutableStateOf(FavoritesState())
    val state: State<FavoritesState> = _state

    init {
        getArticles()
    }

    private fun getArticles() {
        getSavedStatuesUseCase().onEach {
            _state.value = _state.value.copy(statues = it)
        }.launchIn(viewModelScope)
    }
}