package dev.ymuratov.partnerkin_test.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import dev.ymuratov.partnerkin_test.R

val interFamily = FontFamily(
    Font(R.font.inter_light, FontWeight.Light),
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_semi_bold, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold),
)

@Immutable
data class PartnerkinTypography(
    val headlineLight: TextStyle = TextStyle(
        fontFamily = interFamily,
        fontWeight = FontWeight.Light,
        fontSize = 40.sp,
        lineHeight = TextUnit(1.4f, type = TextUnitType.Em),
    ),
    val h1: TextStyle = TextStyle(
        fontFamily = interFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = TextUnit(1.2f, type = TextUnitType.Em),
    ),
    val bodyRegular: TextStyle = TextStyle(
        fontFamily = interFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = TextUnit(1.6f, type = TextUnitType.Em),
    ),
    val labelRegular: TextStyle = TextStyle(
        fontFamily = interFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = TextUnit(1.4f, type = TextUnitType.Em),
    ),
    val labelSmall: TextStyle = TextStyle(
        fontFamily = interFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 11.sp,
        lineHeight = TextUnit(1.4f, type = TextUnitType.Em),
    ),
)

internal val LocalPartnerkinTypography = staticCompositionLocalOf { PartnerkinTypography() }