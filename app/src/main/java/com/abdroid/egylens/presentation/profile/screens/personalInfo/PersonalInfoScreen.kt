package com.abdroid.egylens.presentation.profile.screens.personalInfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.abdroid.egylens.R
import com.abdroid.egylens.presentation.common.CustomTopAppBar
import com.abdroid.egylens.ui.theme.notoFont

@Composable
fun PersonalInfoScreen(
    viewModel: PersonalInfoViewModel = hiltViewModel()
    ) {
    Column (
        Modifier
            .background(colorResource(id = R.color.background))
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.Top
    ){
        CustomTopAppBar(text = "Personal Information", height = 60)
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = colorResource(id = R.color.divider)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box {
            if (viewModel.currentUser?.photoUrl != null) {
                AsyncImage(
                    model = viewModel.currentUser?.photoUrl,
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(10.dp))
            } else {
                Image(
                    modifier = Modifier.size(120.dp),
                    painter = painterResource(id = R.drawable.profile_screen_icon),
                    contentDescription = null,
                )
            }

            Button(
                onClick = {
                   // navController.navigate(Route.PreScanScreen.route)
                },
                modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.BottomEnd),
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.scanner_button),
                    contentColor = colorResource(id = R.color.button_text)
                ),
            ) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.edit_2),
                    contentDescription = null,
                    tint = colorResource(id = R.color.button_text)
                )
            }

        }

        Spacer(modifier = Modifier.height(15.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = colorResource(id = R.color.divider)
        )
        //Spacer(modifier = Modifier.height(15.dp))
        InfoRow(text = "Your Name", info = viewModel.currentUser?.displayName?: "" )
        InfoRow(text = "Your Email", info = viewModel.currentUser?.email?: "" )
        InfoRow(text = "Phone Number", info = "Null" )
        InfoRow(text = "Gender", info = "Prefer not to say" )
        Button(
            modifier = Modifier
                .fillMaxWidth().padding(horizontal = 20.dp, vertical = 20.dp)
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
                text = "Edit Profile",
                fontFamily = notoFont,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.button_text)
            )
        }
    }
}

@Composable
fun InfoRow(text:String , info:String) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Text(
                text = text,
                fontFamily = notoFont,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                color = colorResource(id = R.color.second_text),
            )
            Text(
                text = info,
                fontFamily = notoFont,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                color = colorResource(id = R.color.main_text),
            )
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = colorResource(id = R.color.divider)
        )
    }

}

@Preview
@Composable
private fun PersonalInfoPrev() {
    PersonalInfoScreen()
}