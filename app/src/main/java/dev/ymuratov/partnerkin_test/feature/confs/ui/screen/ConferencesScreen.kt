package dev.ymuratov.partnerkin_test.feature.confs.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.ymuratov.partnerkin_test.core.ui.utils.collectFlowWithLifecycle
import dev.ymuratov.partnerkin_test.feature.confs.ui.component.ConferenceCard
import dev.ymuratov.partnerkin_test.feature.confs.ui.model.ConferencesAction
import dev.ymuratov.partnerkin_test.feature.confs.ui.model.ConferencesEvent
import dev.ymuratov.partnerkin_test.feature.confs.ui.model.ConferencesState
import dev.ymuratov.partnerkin_test.feature.confs.ui.viewmodel.ConferencesViewModel
import java.time.YearMonth

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

    val grouped = state.conferences.sortedByDescending {
        it.startDate
    }.groupBy {
        YearMonth.of(it.startDate.year, it.startDate.month)
    }

    Scaffold(modifier = modifier) { innerPadding ->
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
                grouped.forEach { (date, itemsForDate) ->
                    item {
                        Text(text = date.toString())
                    }
                    items(itemsForDate) { item ->
                        ConferenceCard(info = item) {
                            onEvent(ConferencesEvent.OnNavigateToInfo)
                        }
                    }
                }
            }
        }
    }
}