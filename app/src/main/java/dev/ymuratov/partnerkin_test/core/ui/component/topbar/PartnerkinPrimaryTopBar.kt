package dev.ymuratov.partnerkin_test.core.ui.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.ymuratov.partnerkin_test.core.ui.theme.PartnerkinTheme

@Composable
fun PartnerkinPrimaryTopBar(
    modifier: Modifier = Modifier,
    title: @Composable (BoxScope.() -> Unit)? = null,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 48.dp)
            .background(color = Color.Transparent)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val iconContainerModifier = Modifier
            .padding(horizontal = 8.dp)
            .size(36.dp)

        navigationIcon?.let {
            Box(modifier = iconContainerModifier, propagateMinConstraints = true) { navigationIcon() }
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .defaultMinSize(minHeight = 56.dp),
            propagateMinConstraints = true,
            contentAlignment = Alignment.CenterStart
        ) {
            title?.let {
                CompositionLocalProvider(
                    LocalTextStyle provides PartnerkinTheme.typography.bodyMedium,
                    LocalContentColor provides PartnerkinTheme.colors.textPrimary,
                ) {
                    title()
                }
            }
        }

        actions?.let {
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .wrapContentWidth(), propagateMinConstraints = true
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) { actions() }
            }
        }
    }
}