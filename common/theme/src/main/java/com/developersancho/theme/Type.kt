package com.developersancho.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val DarkTypography = Typography(
    h1 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Bold,
        color = White,
        fontSize = 28.sp
    ),
    h2 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Bold,
        color = White,
        fontSize = 21.sp
    ),
    h3 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.SemiBold,
        color = White,
        fontSize = 18.sp
    ),
    h4 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Medium,
        color = White,
        fontSize = 15.sp
    ),
    h5 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Medium,
        color = White,
        fontSize = 18.sp
    ),
    h6 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Bold,
        color = White,
        fontSize = 16.sp
    ),
    body1 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Normal,
        color = White,
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Bold,
        color = White,
        fontSize = 14.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Medium,
        color = White,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        color = White,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)

// set of light material typography styles to start with.
val LightTypography = Typography(
    h1 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Bold,
        color = Black,
        fontSize = 28.sp
    ),
    h2 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Bold,
        color = Black,
        fontSize = 21.sp
    ),
    h3 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.SemiBold,
        color = Black,
        fontSize = 18.sp
    ),
    h4 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Medium,
        color = Black,
        fontSize = 15.sp
    ),
    h5 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Medium,
        color = Black,
        fontSize = 18.sp
    ),
    h6 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Bold,
        color = Black,
        fontSize = 15.sp
    ),
    body1 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Normal,
        color = Black,
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Bold,
        color = Black,
        fontSize = 14.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = RalewayFonts,
        fontWeight = FontWeight.Medium,
        color = Black,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        color = Black,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        color = Black,
        fontSize = 12.sp
    )
)