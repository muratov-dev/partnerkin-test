package dev.ymuratov.partnerkin_test.core.ui.theme

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun PartnerkinTheme(content: @Composable () -> Unit) {

    val partnerkinColors = PartnerkinColors.Light
    val partnerkinTypography = PartnerkinTypography()
    val defaultTextStyle = partnerkinTypography.bodyRegular

    CompositionLocalProvider(
        LocalPartkerkinColors provides partnerkinColors,
        LocalPartnerkinTypography provides partnerkinTypography,
        LocalTextStyle provides defaultTextStyle,
    ) {
        MaterialTheme(
            colorScheme = lightColorScheme(
                onSurface = PartnerkinTheme.colors.textPrimary,
                surface = PartnerkinTheme.colors.backgroundPrimary,
                background = PartnerkinTheme.colors.backgroundPrimary,
                primary = PartnerkinTheme.colors.buttonPrimaryDefault,
                onPrimary = PartnerkinTheme.colors.textPrimary,
            ), typography = Typography(), content = content
        )
    }
}

object PartnerkinTheme {
    val colors: PartnerkinColors
        @Composable get() = LocalPartkerkinColors.current

    val typography: PartnerkinTypography
        @Composable get() = LocalPartnerkinTypography.current
}