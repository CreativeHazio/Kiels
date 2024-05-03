package com.timeless.kiels.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.timeless.kiels.R

val RobotoFamily = FontFamily(
    Font(R.font.roboto_black, FontWeight.ExtraBold),
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_thin, FontWeight.Thin),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_italic, FontWeight.Normal, FontStyle.Italic)
)

val Typography = Typography(

    bodyLarge = TextStyle(
        fontFamily = RobotoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = RobotoFamily,
        fontWeight = FontWeight.Black,
        fontSize = 28.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = RobotoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = RobotoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 9.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)