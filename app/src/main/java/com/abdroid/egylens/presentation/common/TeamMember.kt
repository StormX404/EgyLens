package com.abdroid.egylens.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.abdroid.egylens.R
import com.abdroid.egylens.ui.theme.notoFont

@Composable
fun TeamMember(
    icon : Int,
    name : String,
    title : String,
) {
    Row (modifier = Modifier
        .fillMaxWidth()
        .border(
            1.dp,
            colorResource(id = R.color.text_field_border),
            shape = RoundedCornerShape(14.dp)
        )
        .clip(RoundedCornerShape(14.dp))
        .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(15.dp))
        Column (
            modifier = Modifier ,
            verticalArrangement = Arrangement.Center ,
            horizontalAlignment = Alignment.Start){
            Text(
                text = name,
                fontFamily = notoFont,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.main_text)
            )
            Text(
                text = title,
                fontFamily = notoFont,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.main_text)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TeamMemberPrev() {
    TeamMember(icon = R.drawable.android ,name =  "Abdelrahman Yasser" , title = "Back End Developer | Team Leader")
}