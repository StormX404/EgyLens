package com.abdroid.egylens.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdroid.egylens.domain.model.Statue
import com.abdroid.egylens.domain.usecases.statues.DeleteStatue
import com.abdroid.egylens.domain.usecases.statues.GetSavedStatue
import com.abdroid.egylens.domain.usecases.statues.UpsertStatue
import com.abdroid.egylens.util.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getSavedArticleUseCase: GetSavedStatue,
    private val deleteArticleUseCase: DeleteStatue,
    private val upsertArticleUseCase: UpsertStatue
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

    private suspend fun deleteArticle(statue: Statue) {
        deleteArticleUseCase(statue = statue)
        sideEffect = UIComponent.Toast("Article deleted")
    }

    private suspend fun upsertArticle(statue: Statue) {
        upsertArticleUseCase(statue = statue)
        sideEffect = UIComponent.Toast("Article Inserted")
    }

}