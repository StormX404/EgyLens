package com.abdroid.egylens.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abdroid.egylens.R
import com.abdroid.egylens.presentation.common.ThemeSwitcher
import com.abdroid.egylens.ui.theme.notoFont

@Composable
fun ProfileCard(
    icon : Painter,
    text : String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .border(
                1.dp,
                colorResource(id = R.color.text_field_border),
                shape = RoundedCornerShape(14.dp)
            )
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClick() }
            .background(colorResource(id = R.color.background))
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween ,
        verticalAlignment = Alignment.CenterVertically )
    {
        Row ( modifier = Modifier
            ,horizontalArrangement = Arrangement.SpaceBetween ,
            verticalAlignment = Alignment.CenterVertically ){
            Icon(
                modifier = Modifier.size(24.dp),
                painter = icon,
                contentDescription = null,
                tint = colorResource(id = R.color.scanner_button)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = text,
                fontFamily = notoFont,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = colorResource(id = R.color.main_text)
            )
        }
        Icon(
            modifier = Modifier.size(18.dp),
            painter = painterResource(id = R.drawable.arrow_right),
            contentDescription = null,
            tint = colorResource(id = R.color.text_field_icon)
        )
    }
}

@Composable
fun ThemeProfileCard(
    icon : Painter,
    text : String,
    onThemeUpdated: () -> Unit,
    darkTheme: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .border(
                1.dp,
                colorResource(id = R.color.text_field_border),
                shape = RoundedCornerShape(14.dp)
            )
            .clip(RoundedCornerShape(14.dp))
            .background(colorResource(id = R.color.background))
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween ,
        verticalAlignment = Alignment.CenterVertically )
    {
        Row ( modifier = Modifier
            ,horizontalArrangement = Arrangement.SpaceBetween ,
            verticalAlignment = Alignment.CenterVertically ){
            Icon(
                modifier = Modifier.size(24.dp),
                painter = icon,
                contentDescription = null,
                tint = colorResource(id = R.color.scanner_button)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = text,
                fontFamily = notoFont,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = colorResource(id = R.color.main_text)
            )
        }

        ThemeSwitcher(
            darkTheme = darkTheme,
            size = 30.dp,
            padding = 5.dp,
            onClick = onThemeUpdated
        )
    }
}



@Preview
@Composable
private fun ProfileCardPrev() {
    ProfileCard(icon = painterResource(id = R.drawable.profile), text =  "Edit Profile" , onClick = {})
}