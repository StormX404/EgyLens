package com.abdroid.egylens.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.abdroid.egylens.R

val notoFont=FontFamily(
    Font(R.font.noto_regular , FontWeight.Normal),
    Font(R.font.noto_light , FontWeight.Light),
    Font(R.font.noto_medium , FontWeight.Medium),
    Font(R.font.noto_bold , FontWeight.Bold),
    Font(R.font.noto_black , FontWeight.Black),
    Font(R.font.noto_semibold , FontWeight.SemiBold),
    Font(R.font.noto_thin , FontWeight.Thin),
)
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)