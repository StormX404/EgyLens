package com.abdroid.egylens.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.abdroid.egylens.R
import com.abdroid.egylens.domain.model.Statue
import com.abdroid.egylens.presentation.onBoardingScreen.Dimens
import com.abdroid.egylens.ui.theme.notoFont

@Composable
fun StatueCard(
    statue: Statue,
    onClick: () -> Unit,
) {
    Row (
        modifier = Modifier
            .clickable { onClick() }
            .border(
                1.dp,
                colorResource(id = R.color.text_field_border),
                shape = RoundedCornerShape(14.dp)
            )
            .padding(horizontal = 5.dp)
            .height(95.dp),
        verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.Start
    ){
        Image(
            painter = rememberAsyncImagePainter(statue.imageUrl),
            contentDescription = "Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(Dimens.ArticleCardSize)
                .clip(MaterialTheme.shapes.medium)
        )
        Spacer(modifier = Modifier.width(15.dp))
        Column {
            Text(
                text = statue.name,
                fontFamily = notoFont,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.main_text),
            )
            Text(
                text = statue.desc,
                fontFamily = notoFont,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                color = colorResource(id = R.color.second_text),
            )
        }
    }
}

