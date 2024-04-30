package com.abdroid.egylens.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.abdroid.egylens.R
import com.abdroid.egylens.domain.model.Statue
import com.abdroid.egylens.presentation.common.SearchBar
import com.abdroid.egylens.ui.theme.notoFont
import com.abdroid.egylens.presentation.common.CustomDotsIndicator
import com.abdroid.egylens.presentation.common.CustomHomeTopAppBar
import com.abdroid.egylens.presentation.navGraph.Route
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlin.math.abs


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    navigateToSearch:() -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    var statuesList by remember { mutableStateOf(emptyList<Statue>()) }

    LaunchedEffect(Unit) {
        val statuesRef = viewModel.database.child("Statues")
        statuesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val newStatuesList = snapshot.children.mapNotNull { snapshot ->
                    snapshot.getValue(Statue::class.java)
                }
                statuesList = newStatuesList
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    val pagerState = rememberPagerState(
        pageCount = { statuesList.size },
        initialPage = 1
    )
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    Column(
    Modifier
        .background(colorResource(id = R.color.background))
        .fillMaxSize()
        .statusBarsPadding(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Top
    ) {
        CustomHomeTopAppBar(name = viewModel.currentUser?.displayName ?: "")
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = colorResource(id = R.color.dvider)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SearchBar(
                text = "",
                readOnly = true,
                onValueChange = {},
                onSearch = {},
                onClick = { navigateToSearch() } ,
                weight = .8f// Correctly invoke the lambda
            )
            Button(
                onClick = {
                    navController.navigate(Route.PreScanScreen.route)
                },
                modifier = Modifier.size(50.dp),
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.scanner_button),
                    contentColor = colorResource(id = R.color.button_text)
                ),
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.scan),
                    contentDescription = null,
                    tint = colorResource(id = R.color.button_text)
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start)
            {
                Text(
                    text = "Popular Statues",
                    fontFamily = notoFont,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    color = colorResource(id = R.color.main_text),
                )
            }

            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 25.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)

            ) { page ->
                val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                val scaleFactor = 1f - 0.2f * abs(pageOffset)
                val alphaFactor = 0.5f + 0.5f * (1f - abs(pageOffset))
                Card (
                    modifier = Modifier
                        .graphicsLayer {
                            scaleX = scaleFactor.coerceIn(1f, 1f)
                            scaleY = scaleFactor.coerceIn(.9f, 1f)
                            alpha = alphaFactor.coerceIn(0.7f, 1f)
                        }
                        .fillMaxWidth()
                        .height(400.dp),
                shape = RoundedCornerShape(20.dp)

                ) {
                    val newStatues = statuesList[page]

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(colorResource(id = R.color.background))
                            .padding(horizontal = 8.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .clickable { navController.navigate("detailsScreen/${newStatues.name}/${newStatues.desc}") },
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(newStatues.imageUrl),
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Image(
                            painter = painterResource(id = R.drawable.overlay),
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize(),
                            alignment = Alignment.BottomCenter
                        )
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(15.dp)
                        ) {
                            Text(
                                text = newStatues.name,
                                fontFamily = notoFont,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = colorResource(id = R.color.slider_main_text),
                            )
                            Text(
                                text = newStatues.desc,
                                fontFamily = notoFont,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = colorResource(id = R.color.second_text),
                            )
                        }
                    }
                }
            }

            Surface(
                modifier = Modifier
                    .padding(bottom = 8.dp),
                shape = CircleShape,
                color = colorResource(id = R.color.main_text).copy(alpha = 0.8f)
            ) {
                CustomDotsIndicator(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                    totalDots = pagerState.pageCount,
                    selectedIndex = if (isDragged) pagerState.currentPage else pagerState.targetPage,
                    dotSize = 10.dp
                )
            }

        }

    }
}
