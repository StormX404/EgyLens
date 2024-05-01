package com.abdroid.egylens.presentation.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.abdroid.egylens.R
import com.abdroid.egylens.domain.model.Statue
import com.abdroid.egylens.presentation.common.CustomTopAppBar
import com.abdroid.egylens.presentation.common.StatueCard
import com.abdroid.egylens.presentation.navGraph.Route
import com.abdroid.egylens.presentation.onBoardingScreen.Dimens.ExtraSmallPadding2
import com.abdroid.egylens.presentation.onBoardingScreen.Dimens.MediumPadding1

@Composable
fun FavoritesScreen(
    state: FavoritesState,
    onClick: (Statue) -> Unit
) {
    Column (
        Modifier
            .background(colorResource(id = R.color.background))
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.Top
    ){
        CustomTopAppBar(text = "Favorites", height = 60)
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = colorResource(id = R.color.dvider)
        )
    }
    ArticlesList(
        statues = state.statues,
        onClick = onClick
    )
}

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    statues: List<Statue>,
    onClick: (Statue) -> Unit
) {
    if (statues.isEmpty()){
        //
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        contentPadding = PaddingValues(all = ExtraSmallPadding2)
    ) {
        items(
            count = statues.size,
        ) {
            statues[it]?.let { statue ->
                StatueCard(statue = statue, onClick = { onClick(statue) })
            }
        }
    }
}
@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    statues: LazyPagingItems<Statue>,
    onClick: (Statue) -> Unit
) {

    val handlePagingResult = handlePagingResult(statues)


    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(
                count = statues.itemCount,
            ) {
                statues[it]?.let { statue ->
                    StatueCard(statue = statue, onClick = { onClick(statue) })
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(statues: LazyPagingItems<Statue>): Boolean {
    val loadState = statues.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            //ShimmerEffect()
            false
        }

        error != null -> {
            //EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}