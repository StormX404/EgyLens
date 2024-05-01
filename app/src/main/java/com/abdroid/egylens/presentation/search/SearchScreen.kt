package com.abdroid.egylens.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.abdroid.egylens.R
import com.abdroid.egylens.presentation.common.CustomTopAppBar
import com.abdroid.egylens.presentation.common.SearchBar
import com.abdroid.egylens.presentation.common.ShimmerEffectList
import com.abdroid.egylens.presentation.common.StatueCard
import com.abdroid.egylens.presentation.navGraph.Route
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@Composable
fun SearchScreen(navController: NavController , viewModel: SearchViewModel = hiltViewModel()) {
    var search by rememberSaveable { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }


    val statuesRef = Firebase.database.reference.child("Statues")

        viewModel.fetchData(search, statuesRef)

    Column (
        Modifier
            .background(colorResource(id = R.color.background))
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        CustomTopAppBar(text = "Discover Statues", height = 60)
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = colorResource(id = R.color.dvider)
        )
        Row(modifier = Modifier
            .focusRequester(focusRequester)
            .padding(horizontal = 20.dp, vertical = 10.dp)) {
            SearchBar(
                text = search,
                readOnly = false,
                onValueChange = { newSearch -> search = newSearch },
                onSearch = {  },
                weight = 1f
            )
        }

        if (search.isNotBlank()) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize().padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(viewModel.statuesList) { statue ->
                    StatueCard(
                        statue,
                        onClick = {
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
        }else{
            ShimmerEffectList()
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}
