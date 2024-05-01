package com.abdroid.egylens.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdroid.egylens.domain.manager.LocalUserManager
import com.abdroid.egylens.domain.model.Statue
import com.abdroid.egylens.domain.usecases.statues.DeleteStatue
import com.abdroid.egylens.domain.usecases.statues.GetSavedStatue
import com.abdroid.egylens.domain.usecases.statues.UpsertStatue
import com.abdroid.egylens.util.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getSavedArticleUseCase: GetSavedStatue,
    private val deleteArticleUseCase: DeleteStatue,
    private val upsertArticleUseCase: UpsertStatue,
    private val userManager: LocalUserManager
) : ViewModel() {

    var sideEffect by mutableStateOf<UIComponent?>(null)
        private set


    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteStatue -> {
                viewModelScope.launch {
                    val statue = getSavedArticleUseCase(url = event.statue.imageUrl)
                    if (statue == null){
                        upsertArticle(statue = event.statue)
                    }else{
                        deleteArticle(statue = event.statue)
                    }
                }
            }
            is DetailsEvent.RemoveSideEffect ->{
                sideEffect = null
            }
        }
    }

    fun isArticleBookmarked(statueUrl: String): Flow<Boolean> {
        return userManager.readIsBookmarked(statueUrl)
    }

    fun toggleBookmark(statueUrl: String) {
        viewModelScope.launch {
            val isBookmarked = userManager.readIsBookmarked(statueUrl).first()
            if (isBookmarked) {
                userManager.saveIsBookmarked(statueUrl, false)
            } else {
                userManager.saveIsBookmarked(statueUrl, true)
            }
        }
    }

    private suspend fun deleteArticle(statue: Statue) {
        deleteArticleUseCase(statue = statue)
        sideEffect = UIComponent.Toast("Statues deleted from favorites successfully")

    }

    private suspend fun upsertArticle(statue: Statue) {
        upsertArticleUseCase(statue = statue)
        sideEffect = UIComponent.Toast("Statues added to favorites successfully")
    }

}