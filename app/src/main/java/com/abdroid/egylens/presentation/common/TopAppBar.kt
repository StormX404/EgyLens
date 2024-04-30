package com.abdroid.egylens.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abdroid.egylens.R
import com.abdroid.egylens.ui.theme.notoFont

@Composable
fun CustomHomeTopAppBar(
    name: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Hi , $name",
            fontFamily = notoFont,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = colorResource(id = R.color.main_text)
        )
        Icon(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = R.drawable.white_logo),
            contentDescription = null,
            tint = colorResource(id = R.color.main_text)
        )
    }
}
@Composable
fun CustomTopAppBar(
    text: String,
    height : Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(height.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            fontFamily = notoFont,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = colorResource(id = R.color.main_text)
        )
        Icon(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = R.drawable.white_logo),
            contentDescription = null,
            tint = colorResource(id = R.color.main_text)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeTopAppBarPrev() {
    CustomHomeTopAppBar(name = "Abdelrahman")
}

@Preview(showBackground = true)
@Composable
private fun ProfileTopAppBarPrev() {
    CustomTopAppBar(text = "Profile", height = 60)
}

@Preview(showBackground = true)
@Composable
private fun FavTopAppBarPrev() {
    CustomTopAppBar(text = "Favorites", height = 60)
}

@Preview(showBackground = true)
@Composable
private fun SearchTopAppBarPrev() {
    CustomTopAppBar(text = "Discover Statues", height = 60)
}