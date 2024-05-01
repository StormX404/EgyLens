package com.abdroid.egylens.presentation.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.abdroid.egylens.R
import com.abdroid.egylens.presentation.common.CustomTopAppBar
import com.abdroid.egylens.presentation.common.EmptyScreen
import com.abdroid.egylens.presentation.common.StatueCard
import com.abdroid.egylens.presentation.navGraph.Route
import com.abdroid.egylens.presentation.onBoardingScreen.Dimens.ExtraSmallPadding2
import com.abdroid.egylens.presentation.onBoardingScreen.Dimens.MediumPadding1

@Composable
fun FavoritesScreen(
    state: FavoritesState,
    navController: NavController,
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
        if (state.statues.isEmpty()){
            EmptyScreen(rawResId = R.raw.empty)
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(
                count = state.statues.size,
            ) {
                state.statues[it].let { statue ->
                    StatueCard(statue = statue, onClick = {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            "statue",
                            statue
                        )
                        navController.navigate(
                            route = Route.DetailsScreen.route
                        )
                    })
                }
            }
        }
    }
}