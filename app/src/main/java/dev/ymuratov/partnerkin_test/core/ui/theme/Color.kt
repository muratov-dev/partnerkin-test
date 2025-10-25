package dev.ymuratov.partnerkin_test.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class PartnerkinColors(
    val backgroundPrimary: Color = Color.Unspecified,
    val backgroundCardDefault: Color = Color.Unspecified,
    val backgroundCardCanceled: Color = Color.Unspecified,
    val buttonPrimaryDefault: Color = Color.Unspecified,
    val iconButtonPrimaryDefault: Color = Color.Unspecified,
    val iconButtonOnPrimaryDefault: Color = Color.Unspecified,
    val textPrimary: Color = Color.Unspecified,
    val textSecondary: Color = Color.Unspecified,
    val accent: Color = Color.Unspecified,
    val error: Color = Color.Unspecified,
) {

    companion object {
        @Stable
        val Light = PartnerkinColors(
            backgroundPrimary = Color(0xFFFFFFFF),
            backgroundCardDefault = Color(0xFFEFF2F9),
            backgroundCardCanceled = Color(0x1AFF3333),
            buttonPrimaryDefault = Color(0xFF456AEF),
            iconButtonPrimaryDefault = Color(0x00FFFFFF),
            iconButtonOnPrimaryDefault = Color(0xFF0E1234),
            textPrimary = Color(0xFF0E1234),
            textSecondary = Color(0x59060A3C),
            accent = Color(0xFF456AEF),
            error = Color(0xFFFF3333),
        )
    }
}

internal val LocalPartkerkinColors = staticCompositionLocalOf { PartnerkinColors() }