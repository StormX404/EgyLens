package com.abdroid.egylens.presentation.onBoardingScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abdroid.egylens.R
import com.abdroid.egylens.presentation.onBoardingScreen.Page
import com.abdroid.egylens.ui.theme.EgyLensTheme
import com.abdroid.egylens.ui.theme.notoFont

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page,
) {
    Box(modifier = modifier) {
        Image(
            modifier = Modifier,
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Image(
            modifier = Modifier,
            painter = painterResource(id = R.drawable.overlay),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column (
            modifier = modifier.fillMaxSize().padding(bottom = 100.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom
        )
        {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = page.title,
                fontSize = 22.sp,
                fontFamily = notoFont,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Start
            )
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = page.description,
                fontSize = 14.sp,
                fontFamily = notoFont,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                textAlign = TextAlign.Start
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingPagePreview() {
    EgyLensTheme {
        OnBoardingPage(
            page = Page(
                title = "INTERACTIVE EXPLANATION",
                description = "through Sign Language and Audio Narration.",
                image = R.drawable.onboarding_1
            )
        )
    }
}