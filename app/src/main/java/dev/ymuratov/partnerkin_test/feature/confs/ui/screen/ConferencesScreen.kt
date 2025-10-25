package dev.ymuratov.partnerkin_test.feature.confs.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.ymuratov.partnerkin_test.R
import dev.ymuratov.partnerkin_test.core.ui.component.button.ParterkinIconButton
import dev.ymuratov.partnerkin_test.core.ui.component.topbar.PartnerkinPrimaryTopBar
import dev.ymuratov.partnerkin_test.core.ui.theme.PartnerkinTheme
import dev.ymuratov.partnerkin_test.core.ui.utils.collectFlowWithLifecycle
import dev.ymuratov.partnerkin_test.core.ui.utils.shimmerOnContentLoadingAnimation
import dev.ymuratov.partnerkin_test.feature.confs.ui.component.ConferenceCard
import dev.ymuratov.partnerkin_test.feature.confs.ui.model.ConferencesAction
import dev.ymuratov.partnerkin_test.feature.confs.ui.model.ConferencesEvent
import dev.ymuratov.partnerkin_test.feature.confs.ui.model.ConferencesState
import dev.ymuratov.partnerkin_test.feature.confs.ui.viewmodel.ConferencesViewModel
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun ConferencesContainer(
    modifier: Modifier = Modifier, viewModel: ConferencesViewModel = hiltViewModel(), navigateToInfo: () -> Unit = {}
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    viewModel.viewActions.collectFlowWithLifecycle { action ->
        when (action) {
            ConferencesAction.NavigateToInfo -> navigateToInfo()
            null -> {}
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> viewModel.obtainEvent(ConferencesEvent.RefreshConferences)
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    ConferencesContent(modifier = modifier, state = state, onEvent = viewModel::obtainEvent)
}

@Composable
private fun ConferencesContent(
    modifier: Modifier = Modifier, state: ConferencesState = ConferencesState(), onEvent: (ConferencesEvent) -> Unit = {}
) {
    Scaffold(modifier = modifier, topBar = {
        PartnerkinPrimaryTopBar(
            navigationIcon = { ParterkinIconButton(icon = R.drawable.ic_arrow_back) },
            actions = { ParterkinIconButton(icon = R.drawable.ic_support) },
            title = {
                Text(
                    text = stringResource(R.string.conferences_title),
                    style = PartnerkinTheme.typography.bodyMedium,
                    color = PartnerkinTheme.colors.textPrimary,
                    modifier = Modifier.wrapContentSize(Alignment.Center)
                )
            })
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                if (state.groupedConferences.isEmpty()) {
                    items(3) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .shimmerOnContentLoadingAnimation(backgroundColor = PartnerkinTheme.colors.backgroundCardDefault)
                                .clip(RoundedCornerShape(12.dp))
                        )
                    }
                } else {
                    state.groupedConferences.forEach { (date, itemsForDate) ->
                        val month = date.month.getDisplayName(TextStyle.FULL_STANDALONE, Locale("ru"))
                        val headerText = "$month, ${date.year}"
                        item {
                            Text(
                                text = headerText,
                                style = PartnerkinTheme.typography.h2,
                                color = PartnerkinTheme.colors.textPrimary,
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .fillMaxWidth()
                            )
                        }
                        items(itemsForDate) { item ->
                            ConferenceCard(info = item) { onEvent(ConferencesEvent.OnNavigateToInfo) }
                        }
                    }
                }
            }
        }
    }
}