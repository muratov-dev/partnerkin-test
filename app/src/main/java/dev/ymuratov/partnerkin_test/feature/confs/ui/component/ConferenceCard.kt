package dev.ymuratov.partnerkin_test.feature.confs.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import dev.ymuratov.partnerkin_test.R
import dev.ymuratov.partnerkin_test.core.ui.component.image.PartnerkinAsyncImage
import dev.ymuratov.partnerkin_test.core.ui.theme.PartnerkinTheme
import dev.ymuratov.partnerkin_test.feature.confs.domain.model.ConferenceInfoModel
import dev.ymuratov.partnerkin_test.feature.confs.domain.model.ConferenceStatusModel

@Composable
fun ConferenceCard(info: ConferenceInfoModel, modifier: Modifier = Modifier, onCardClick: () -> Unit = {}) {
    val bgColor = when (info.status) {
        ConferenceStatusModel.PUBLISH -> PartnerkinTheme.colors.backgroundCardDefault
        ConferenceStatusModel.CANCELED -> PartnerkinTheme.colors.backgroundCardCanceled
    }
    val bgShape = RoundedCornerShape(16.dp)

    val imageBgColor = when (info.status) {
        ConferenceStatusModel.PUBLISH -> PartnerkinTheme.colors.accent
        ConferenceStatusModel.CANCELED -> PartnerkinTheme.colors.error
    }
    val imageBgShape = RoundedCornerShape(12.dp)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = bgColor, shape = bgShape)
            .clip(bgShape)
            .clickable(onClick = onCardClick)
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 24.dp,
                top = if (info.status == ConferenceStatusModel.CANCELED) 10.dp else 24.dp
            )
    ) {
        Column {
            if (info.status == ConferenceStatusModel.CANCELED) {
                CompositionLocalProvider(LocalContentColor provides PartnerkinTheme.colors.error) {
                    Row(
                        modifier = Modifier
                            .wrapContentWidth()
                            .border(width = 1.dp, color = PartnerkinTheme.colors.error, shape = RoundedCornerShape(100.dp))
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_lightning),
                            contentDescription = null,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(text = info.statusTitle, style = PartnerkinTheme.typography.labelSmall)
                    }
                }
                Spacer(Modifier.size(24.dp))
            }

            Text(
                text = info.name,
                style = PartnerkinTheme.typography.h1,
                color = PartnerkinTheme.colors.textPrimary,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.size(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(104.dp)
                    .background(color = imageBgColor.copy(alpha = 0.04f), shape = imageBgShape)
                    .clip(imageBgShape)
            ) {
                PartnerkinAsyncImage(
                    data = info.imageUrl, modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.Top
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = info.startDate.dayOfMonth.toString(),
                            style = PartnerkinTheme.typography.headlineLight,
                            color = PartnerkinTheme.colors.textPrimary
                        )
                        Text(
                            text = info.startDate.month.name.format("MMM"),
                            style = PartnerkinTheme.typography.labelRegular,
                            color = PartnerkinTheme.colors.textPrimary.copy(alpha = 0.6f)
                        )
                    }
                    if (info.startDate != info.endDate) {
                        Text(
                            text = "-",
                            style = PartnerkinTheme.typography.headlineLight,
                            color = PartnerkinTheme.colors.textPrimary
                        )
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = info.endDate.dayOfMonth.toString(),
                                style = PartnerkinTheme.typography.headlineLight,
                                color = PartnerkinTheme.colors.textPrimary
                            )
                            Text(
                                text = info.endDate.month.name.format("MMM"),
                                style = PartnerkinTheme.typography.labelRegular,
                                color = PartnerkinTheme.colors.textPrimary.copy(alpha = 0.6f)
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.size(24.dp))
            LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(info.categories) { category ->
                    Text(
                        text = category.name,
                        style = PartnerkinTheme.typography.labelSmall,
                        color = PartnerkinTheme.colors.textPrimary,
                        modifier = Modifier
                            .wrapContentSize()
                            .background(color = PartnerkinTheme.colors.backgroundPrimary, shape = RoundedCornerShape(100.dp))
                            .padding(horizontal = 10.dp, vertical = (4.5).dp)
                    )
                }
            }
            Spacer(Modifier.size(16.dp))
            CompositionLocalProvider(LocalContentColor provides PartnerkinTheme.colors.textPrimary) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_location),
                        contentDescription = null,
                        modifier = Modifier.size(12.dp)
                    )
                    Text(text = "${info.city}, ${info.country}", style = PartnerkinTheme.typography.bodyRegular)
                }
            }
        }
    }
}