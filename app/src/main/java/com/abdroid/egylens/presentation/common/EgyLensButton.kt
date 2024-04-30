package com.abdroid.egylens.presentation.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdroid.egylens.R
import com.abdroid.egylens.ui.theme.notoFont

@Composable
fun EgyLensButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
) {

    Button(
        modifier = Modifier.padding(vertical = 5.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.second_button),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(size = 6.dp)
    ) {
        Text(
            text = text,
            fontFamily = notoFont,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

@Composable
fun EgyLensTextButton(
    text: String,
    onClick: () -> Unit,
) {
    TextButton(
        modifier = Modifier,
        onClick = onClick,
        shape = RoundedCornerShape(size = 6.dp)
    ) {
        Text(
            text = text,
            fontFamily = notoFont,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun ButtonPrev() {
    EgyLensButton(
        modifier = Modifier,
        text = "Next",
        onClick = {}
    )
}
@Preview
@Composable
fun TextButtonPrev() {
    EgyLensTextButton(
        text = "Back",
        onClick = {}
    )
}