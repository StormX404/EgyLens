package com.abdroid.egylens.presentation.profile.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abdroid.egylens.R
import com.abdroid.egylens.ui.theme.notoFont


/*@Composable
 fun FaqCard(question: String , answer: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.background)),
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .border(
                1.dp,
                colorResource(id = R.color.text_field_border),
                shape = RoundedCornerShape(14.dp)
            )
        )
    {
        CardContent(question = question , answer = answer)
    }
}*/

@Composable
 fun FaqCard(
    question: String,
    answer : String,
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.background)),
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .border(
                width = 1.dp,
                color = if (expanded) colorResource(id = R.color.scanner_button) else colorResource(id = R.color.text_field_border),
                shape = RoundedCornerShape(14.dp)
            )
    )
    {
        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 10.dp)
                .animateContentSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = question,
                    fontFamily = notoFont,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.main_text),
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    modifier = Modifier
                        .background(
                            color = if (expanded) colorResource(id = R.color.scanner_button) else colorResource(id = R.color.text_field_bg),
                            shape = CircleShape
                        )
                        .padding(4.dp)
                        .size(12.dp),
                    onClick = { expanded = !expanded }
                ){
                    Icon(
                        modifier = Modifier,
                        painter = painterResource(id = if (expanded) R.drawable.arrow_up else R.drawable.arrow_down),
                        contentDescription = if (expanded) "show less" else "show more",
                        tint = if (expanded) colorResource(id = R.color.button_text) else colorResource(id = R.color.main_text)
                    )
                }
            }
            if (expanded) {
                Text(
                    text = answer,
                    fontFamily = notoFont,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Start,
                    color = colorResource(id = R.color.second_text),
                )
            }

        }
    }

}