package com.abdroid.egylens.presentation.details

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.abdroid.egylens.R
import com.abdroid.egylens.domain.model.Statue
import com.abdroid.egylens.ui.theme.notoFont


@Composable
fun DetailsScreen(
    navController: NavController,
    statueName: String,
    statueDesc: String,
    ) {


    Box(
        Modifier
            .background(colorResource(id = R.color.background))
            .fillMaxSize()
    ) {

        var iconTint by remember { mutableStateOf(Color.White) }
        val scrollState = rememberScrollState()
        val scrolled by remember {
            derivedStateOf {
                scrollState.value != 0
            }
        }
        val clickedState = remember { mutableStateOf(false) }

        val topPadding by animateDpAsState(if (scrolled || clickedState.value) 100.dp else 300.dp,
            label = ""
        )


        Image(
            modifier = Modifier
                .height(350.dp)
                .fillMaxWidth(),
            painter = rememberAsyncImagePainter("https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/GD-EG-Caire-Mus%C3%A9e061.JPG/330px-GD-EG-Caire-Mus%C3%A9e061.JPG"),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            alignment = Alignment.TopCenter
        )
        Image(
            modifier = Modifier
                .height(350.dp)
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
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(shape = CircleShape)
                    .background(Color.Black.copy(alpha = .3f))
                    .clickable { navController.popBackStack() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(id = R.drawable.arrow_left),
                    contentDescription = "",
                    tint = Color.White,
                )
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(shape = CircleShape)
                    .background(Color.Black.copy(alpha = .3f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.bookmark_outlined),
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
                Text(
                    text = "Akhenaton",
                    fontFamily = notoFont,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.main_text),
                )
                Text(
                    text = "was a king (c. 1353–36 BCE) of ancient Egypt of the 18th dynasty",
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
                        text = "Arguably the most famous of the New Kingdom pharaohs, Ramses II succeeded his father Seti I, and became king at the age of between 25 and 30. He enjoyed a long reign, ruling for 67 years, and left a well-recorded legacy.  He had many queens and sired around 100 children. His Great Royal Wife was Nefertari, for whom he built a temple near his own in Nubia, at Abu Simbel. Her tomb in the Valley of the Queens is perhaps the most beautiful sepulcher in the Theban necropolis. Ramses II also married at least one of his daughters, Meritamun.  Ramses II is remembered as a great warrior and recorded his Year 5 Battle of Kadesh, in which he fought against the Hittites. Although the actual outcome of the battle was a draw, the king was excessively proud of his personal bravery and military prowess, bragging that he had singlehandedly saved Egypt from what might have been a terrible defeat. He continued to skirmish with the Hittites for many years, but eventually signed a peace treaty—the first known in history—with their king, and married his daughter to seal the newly-founded alliance. The tomb of the ambassador who delivered the peace treaty has been found at Saqqara.  This pharaoh built temples almost everywhere in Egypt, as well as in Nubia. The most famous of his projects are Abu Simbel, the Ramesseum (dedicated to his mortuary cult), and his additions to Luxor Temple. He also founded a new capital, Pi-Ramses, in the Delta. For his own glory and the glory of Egypt, he erected many statues of himself, a great number of which he usurped from earlier kings. Ramses II was originally buried in KV 7, but his body was moved to the Deir el-Bahari cache to protect it from looting. In the late 20th century, the mummy was sent to the Musée de l’Homme in Paris for study and restoration, as it was in poor condition. In his official travel document, his occupation was listed as “King (deceased)”.",
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
                            iconTint = if (iconTint == Color.White) Color.Red else Color.White
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = R.drawable.heart_bold),
                        contentDescription = "",
                        tint = animateColorAsState(iconTint, label = "").value,
                    )
                }
            }
        }


    }
}

