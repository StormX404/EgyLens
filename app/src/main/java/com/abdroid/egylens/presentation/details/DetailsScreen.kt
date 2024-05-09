package com.abdroid.egylens.presentation.details

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.abdroid.egylens.R
import com.abdroid.egylens.domain.model.Statue
import com.abdroid.egylens.presentation.common.VideoPlayer
import com.abdroid.egylens.ui.theme.notoFont


@Composable
fun DetailsScreen(
    navController: NavController,
    statue: Statue,
    event: (DetailsEvent) -> Unit,
    viewModel: DetailsViewModel = hiltViewModel()

    ) {

    val isBookmarked by viewModel.isArticleBookmarked(statue.name).collectAsState(initial = false)

    Box(
        Modifier
            .background(colorResource(id = R.color.background))
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        //var iconTint by remember { mutableStateOf(Color.White) }

        val scrollState = rememberScrollState()
        val scrolled by remember {
            derivedStateOf {
                scrollState.value != 0
            }
        }
        val clickedState = remember { mutableStateOf(false) }

        val topPadding by animateDpAsState(if (scrolled || clickedState.value) 100.dp else 360.dp,
            label = ""
        )


        /*Image(
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth(),
            painter = rememberAsyncImagePainter(statue.imageUrl),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            alignment = Alignment.TopCenter
        )*/
        val isPlaying = remember { mutableStateOf(false) }
        VideoPlayer( statue.videoUrl , isPlaying.value)
        Image(
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.overlay_2),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            alignment = Alignment.TopCenter
        )
        Row(
            Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .systemBarsPadding()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween , verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(shape = CircleShape)
                    .background(Color.Black.copy(alpha = .3f))
                    .clickable {
                        navController.popBackStack()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(id = R.drawable.arrow_left),
                    contentDescription = "",
                    tint = Color.White,
                )
            }
            Text(
                text = "Details",
                fontFamily = notoFont,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.main_text),
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(shape = CircleShape)
                    .background(Color.Black.copy(alpha = .3f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    painter = painterResource(id = R.drawable.outlined_logo),
                    contentDescription = "",
                    tint = Color.White,

                    )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = topPadding, bottom = 80.dp)
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(colorResource(id = R.color.background)),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .clickable { clickedState.value = !clickedState.value },
                    painter = painterResource(id = R.drawable.dvider),
                    contentDescription = "",
                    alignment = Alignment.Center
                )
            }

            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Row(modifier = Modifier.fillMaxWidth() , horizontalArrangement = Arrangement.SpaceBetween , verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = statue.name,
                        fontFamily = notoFont,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        color = colorResource(id = R.color.main_text),
                    )
                    Button(
                        modifier = Modifier,
                        onClick = { isPlaying.value = !isPlaying.value
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.main_button),
                            contentColor = colorResource(id = R.color.button_text)
                        ),
                        shape = CircleShape
                    ) {

                        val text = if (isPlaying.value) "Pause" else "Play"
                        val painterId = if (isPlaying.value) R.drawable.pause else R.drawable.play
                        Row (
                            horizontalArrangement = Arrangement.Center ,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(
                                modifier = Modifier.size(10.dp),
                                painter = painterResource(id =painterId),
                                contentDescription = "",
                                tint = colorResource(id = R.color.button_text)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = text,
                                fontFamily = notoFont,
                                fontWeight = FontWeight.Medium,
                                color = colorResource(id = R.color.button_text)
                            )
                        }

                    }

                }

                Text(
                    text = statue.desc,
                    fontFamily = notoFont,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.second_text),
                )
                Text(
                    text = "About Statue",
                    fontFamily = notoFont,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.main_text),
                )
                Column(modifier = Modifier.verticalScroll(scrollState)) {
                    Text(
                        text = statue.about,
                        fontFamily = notoFont,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.second_text),
                    )
                }
            }
        }
        Column {
            Row (modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween ,
                verticalAlignment = Alignment.Bottom
            ){
                Button(
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .height(52.dp)
                        .clip(RoundedCornerShape(14.dp)),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.main_button),
                        contentColor = colorResource(id = R.color.button_text)
                    ),
                    shape = RoundedCornerShape(size = 6.dp)
                ) {
                    Text(
                        text = "See on the map",
                        fontFamily = notoFont,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.button_text)
                    )
                }
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(shape = CircleShape)
                        .background(Color.Black.copy(alpha = .3f))
                        .clickable {
                            event(DetailsEvent.UpsertDeleteStatue(statue))
                            viewModel.toggleBookmark(statue.name)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = R.drawable.heart_bold),
                        contentDescription = "",
                        tint = if (isBookmarked) Color.Red else Color.White
                    )
                }
            }
        }


    }
}

