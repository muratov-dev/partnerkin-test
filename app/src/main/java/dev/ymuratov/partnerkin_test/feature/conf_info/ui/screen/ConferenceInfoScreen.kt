package dev.ymuratov.partnerkin_test.feature.conf_info.ui.screen

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.ymuratov.partnerkin_test.R
import dev.ymuratov.partnerkin_test.core.ui.component.button.ParterkinIconButton
import dev.ymuratov.partnerkin_test.core.ui.component.image.PartnerkinAsyncImage
import dev.ymuratov.partnerkin_test.core.ui.component.topbar.PartnerkinPrimaryTopBar
import dev.ymuratov.partnerkin_test.core.ui.theme.PartnerkinTheme
import dev.ymuratov.partnerkin_test.core.ui.utils.collectFlowWithLifecycle
import dev.ymuratov.partnerkin_test.feature.conf_info.ui.model.ConferenceInfoAction
import dev.ymuratov.partnerkin_test.feature.conf_info.ui.model.ConferenceInfoEvent
import dev.ymuratov.partnerkin_test.feature.conf_info.ui.model.ConferenceInfoState
import dev.ymuratov.partnerkin_test.feature.conf_info.ui.viewmodel.ConferenceInfoViewModel
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

@Composable
fun ConferenceInfoContainer(
    modifier: Modifier = Modifier, viewModel: ConferenceInfoViewModel = hiltViewModel(), navigateBack: () -> Unit = {}
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    viewModel.viewActions.collectFlowWithLifecycle { action ->
        when (action) {
            ConferenceInfoAction.NavigateBack -> navigateBack()
            null -> {}
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> viewModel.obtainEvent(ConferenceInfoEvent.GetConferenceInfo)
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    ConferenceInfoContent(modifier = modifier, state = state, onEvent = viewModel::obtainEvent)
}

@Composable
private fun ConferenceInfoContent(
    modifier: Modifier = Modifier, state: ConferenceInfoState = ConferenceInfoState(), onEvent: (ConferenceInfoEvent) -> Unit = {}
) {

    val context = LocalContext.current

    Scaffold(modifier = modifier, topBar = {
        PartnerkinPrimaryTopBar(navigationIcon = {
            ParterkinIconButton(icon = R.drawable.ic_arrow_back) {
                onEvent(ConferenceInfoEvent.OnNavigateBack)
            }
        })
    }) { innerPadding ->
        state.conferenceInfo?.let { info ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = innerPadding.calculateTopPadding(), start = 16.dp, end = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(Modifier.size(12.dp))
                Text(
                    text = stringResource(R.string.conference_info_name_label),
                    style = PartnerkinTheme.typography.bodyRegular,
                    color = PartnerkinTheme.colors.textPrimary
                )
                Spacer(Modifier.size(4.dp))
                Text(text = info.name, style = PartnerkinTheme.typography.h1, color = PartnerkinTheme.colors.textPrimary)
                Spacer(Modifier.size(20.dp))
                PartnerkinAsyncImage(
                    data = info.imageUrl, modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Spacer(Modifier.size(20.dp))
                LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(info.categories) { category ->
                        Text(
                            text = category.name,
                            style = PartnerkinTheme.typography.labelSmall,
                            color = PartnerkinTheme.colors.textPrimary,
                            modifier = Modifier
                                .wrapContentSize()
                                .background(
                                    color = PartnerkinTheme.colors.backgroundCardDefault, shape = RoundedCornerShape(100.dp)
                                )
                                .padding(horizontal = 10.dp, vertical = (4.5).dp)
                        )
                    }
                }
                Spacer(Modifier.size(20.dp))
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_calendar),
                        tint = PartnerkinTheme.colors.accent,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )

                    val formattedDate = info.startDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("ru", "RU")))
                    val daysCount = ChronoUnit.DAYS.between(info.startDate, info.endDate) + 1
                    val daysText = when (daysCount) {
                        1L -> stringResource(R.string.conference_info_days_1_text)
                        in 2L..4L -> stringResource(R.string.conference_info_days_2_text)
                        else -> stringResource(R.string.conference_info_days_3_text)
                    }
                    Text(
                        text = "$formattedDate, $daysCount $daysText",
                        style = PartnerkinTheme.typography.bodyMedium,
                        color = PartnerkinTheme.colors.textPrimary
                    )
                }
                Spacer(Modifier.size(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_location),
                        tint = PartnerkinTheme.colors.accent,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "${info.city}, ${info.country}",
                        style = PartnerkinTheme.typography.bodyMedium,
                        color = PartnerkinTheme.colors.textPrimary
                    )
                }
                info.registerUrl?.let {
                    Spacer(Modifier.size(20.dp))
                    Button(
                        onClick = { context.startActivity(Intent(Intent.ACTION_VIEW, it.toUri())) },
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(vertical = 11.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.conference_info_register_button),
                            style = PartnerkinTheme.typography.bodyMedium,
                            color = PartnerkinTheme.colors.textTertiary
                        )
                    }
                }
                Spacer(Modifier.size(20.dp))
                Text(
                    text = stringResource(R.string.conference_info_about_label),
                    style = PartnerkinTheme.typography.h2,
                    color = PartnerkinTheme.colors.textPrimary
                )
                Spacer(Modifier.size(12.dp))
                Text(
                    text = info.about!!,
                    style = PartnerkinTheme.typography.bodyRegular,
                    color = PartnerkinTheme.colors.textPrimary
                )
            }
        } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(R.string.conference_info_loading_text),
                style = PartnerkinTheme.typography.bodyMedium,
                color = PartnerkinTheme.colors.textPrimary
            )
        }
    }
}