package com.abdroid.egylens.presentation.profile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.abdroid.egylens.R
import com.abdroid.egylens.presentation.common.CustomTopAppBar
import com.abdroid.egylens.presentation.profile.components.FaqCard

@Composable
fun FaqScreen() {
    val faqs = listOf(
        "1. What is the purpose of this Android application?" to "This Android application is designed to provide users with information about Egyptian statues through the power of artificial intelligence. By scanning a statue using the app, users can access detailed data and historical insights about the statue they're interested in.",
        "2. How does the scanning process work?" to "The scanning process utilizes advanced image recognition technology powered by artificial intelligence. Users simply need to point their device's camera at the Egyptian statue they want to learn more about, and the app will analyze the image to identify the statue and retrieve relevant information.",
        "3. What kind of data does the app provide about the statues?" to "The app provides a wide range of data about each scanned statue, including its name, historical significance, materials used in its construction, dimensions, time period, and any relevant historical anecdotes or stories associated with the statue.",
        "4. Is the information provided by the app accurate and reliable?" to "Yes, the information provided by the app is sourced from reputable historical and archaeological databases, ensuring accuracy and reliability. However, users should keep in mind that historical interpretations may vary, and new discoveries could potentially update our understanding of certain statues over time.",
        "5. Can the app recognize all types of Egyptian statues?" to "While the app is designed to recognize and provide information about a wide variety of Egyptian statues, its recognition capabilities may vary depending on factors such as lighting conditions, the angle of the statue, and the quality of the image captured by the device's camera.",
        "6. Does the app require an internet connection to work?" to "Yes, the app requires an internet connection to access its database of information about Egyptian statues. However, once the initial data is downloaded, certain features of the app may be available offline, such as viewing previously scanned statues and accessing cached information.",
        "7. Is the app available in multiple languages?" to "Yes, the app supports multiple languages to cater to users from diverse linguistic backgrounds. Users can easily switch between languages within the app's settings menu.",
        "8. How frequently is the app updated with new information?" to "The app is regularly updated with new information and features to enhance the user experience and ensure that users have access to the latest research and discoveries in the field of Egyptian archaeology and history.",
        "9. Can users contribute information or feedback to the app?" to "Yes, users are encouraged to contribute feedback and suggestions for improving the app's functionality and content. Additionally, users can submit information about newly discovered statues or corrections to existing data to help improve the accuracy and comprehensiveness of the app's database.",
        "10. Is the app free to download and use?" to "Yes, the app is available for free download on the Google Play Store, and there are no subscription fees or in-app purchases required to access its core features."
    )
    Column (
        Modifier
            .background(colorResource(id = R.color.background))
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.Top
    ){
        CustomTopAppBar(text = "FAQs", height = 60)
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = colorResource(id = R.color.divider)
        )
        LazyColumn(modifier = Modifier.padding(vertical = 10.dp , horizontal = 10.dp)) {
            items(faqs) { faq ->
                FaqCard(question = faq.first, answer = faq.second)
            }
        }

    }
}

@Preview
@Composable
private fun FaqScreenPrev() {
    FaqScreen()
}