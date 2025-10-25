package dev.ymuratov.partnerkin_test.core.ui.component.button

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.ymuratov.partnerkin_test.core.ui.theme.PartnerkinTheme

object PartnerkinIconButtonDefaults {

    val IconSize = 24.dp
    val MinSize = 40.dp
    val Shape = CircleShape

    @Composable
    @Stable
    fun colors(
        containerColor: Color = PartnerkinTheme.colors.iconButtonPrimaryDefault,
        disabledContainerColor: Color = Color.Transparent,
        contentColor: Color = PartnerkinTheme.colors.iconButtonOnPrimaryDefault,
        disabledContentColor: Color = PartnerkinTheme.colors.iconButtonOnPrimaryDefault,
        rippleColor: Color = Color.Unspecified
    ): PartnerkinIconButtonColors = PartnerkinIconButtonColors(
        containerColor = containerColor,
        disabledContainerColor = disabledContainerColor,
        contentColor = contentColor,
        disabledContentColor = disabledContentColor,
        rippleColor = rippleColor
    )
}

data class PartnerkinIconButtonColors(
    val containerColor: Color = Color.Unspecified,
    val disabledContainerColor: Color = Color.Unspecified,
    val contentColor: Color = Color.Unspecified,
    val disabledContentColor: Color = Color.Unspecified,
    val rippleColor: Color = Color.Unspecified,
) {
    @Stable
    fun containerColor(enabled: Boolean): Color = if (enabled) containerColor else disabledContainerColor

    @Stable
    fun contentColor(enabled: Boolean): Color = if (enabled) contentColor else disabledContentColor
}