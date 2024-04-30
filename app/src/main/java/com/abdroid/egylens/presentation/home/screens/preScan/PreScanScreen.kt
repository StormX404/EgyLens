package com.abdroid.egylens.presentation.home.screens.preScan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.abdroid.egylens.R
import com.abdroid.egylens.presentation.navGraph.Route
import com.abdroid.egylens.ui.theme.notoFont
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun PreScanScreen(
    navController: NavHostController,
) {
    val imageRes = if (isSystemInDarkTheme()) R.drawable.face_scnner_for_dark else R.drawable.face_scanner_for_light
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.scanning))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
    )
    Column (
        Modifier
            .background(colorResource(id = R.color.background))
            .fillMaxSize()
            .statusBarsPadding()
            .systemBarsPadding()
            .padding(20.dp) ,
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.Top
    ){
        Row (modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ){
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .clip(shape = CircleShape)
                    .background(colorResource(id = R.color.text_field_bg))
                    .clickable(
                        onClick = {
                            navController.popBackStack()
                        },
                    ),
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "",
                tint = colorResource(id = R.color.main_text),
            )
        }
        Column(
            modifier = Modifier.fillMaxSize().padding(vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Let's search for the landmark you want.",
                fontFamily = notoFont,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.main_text),
            )
            Box(modifier = Modifier, contentAlignment = Alignment.Center){
                Image(
                    modifier = Modifier.size(260.dp),
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                )
                LottieAnimation(
                    composition = composition,
                    progress = { progress },
                    modifier = Modifier.width(220.dp)
                )
            }
            /*Spacer(modifier = Modifier.size(200.dp))*/
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clip(RoundedCornerShape(14.dp)),
                //.padding(8.dp),
                onClick = {
                    navController.navigate(Route.CameraScreen.route)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.main_button),
                    contentColor = colorResource(id = R.color.button_text)
                ),
                shape = RoundedCornerShape(size = 6.dp)
            ) {
                Text(
                    text = "Scan Landmark",
                    fontFamily = notoFont,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.button_text)
                )
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
private fun PreScanScreenPrev() {
    PreScanScreen(navController = rememberNavController())
}
