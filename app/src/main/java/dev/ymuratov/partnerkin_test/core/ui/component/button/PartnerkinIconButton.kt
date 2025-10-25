package dev.ymuratov.partnerkin_test.core.ui.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp

@Composable
fun ParterkinIconButton(
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    enabled: Boolean = true,
    iconSize: Dp = PartnerkinIconButtonDefaults.IconSize,
    colors: PartnerkinIconButtonColors = PartnerkinIconButtonDefaults.colors(),
    shape: Shape = PartnerkinIconButtonDefaults.Shape,
    minHeight: Dp = PartnerkinIconButtonDefaults.MinSize,
    minWidth: Dp = PartnerkinIconButtonDefaults.MinSize,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .width(IntrinsicSize.Min)
            .background(color = colors.containerColor(enabled), shape = shape)
            .clip(shape)
            .defaultMinSize(minHeight = minHeight, minWidth = minWidth), contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable(enabled = enabled, onClick = onClick, onClickLabel = contentDescription, role = Role.Button)
        )
        Box(modifier = Modifier.size(iconSize)) {
            Icon(
                painter = painterResource(icon),
                contentDescription = contentDescription,
                tint = colors.contentColor(enabled),
                modifier = Modifier.size(iconSize)
            )
        }
    }
}