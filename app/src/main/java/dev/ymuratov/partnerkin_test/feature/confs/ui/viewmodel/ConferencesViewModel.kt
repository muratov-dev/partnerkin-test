package dev.ymuratov.partnerkin_test.feature.confs.ui.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ymuratov.partnerkin_test.core.ui.viewmodel.BaseViewModel
import dev.ymuratov.partnerkin_test.feature.confs.domain.repository.ConferencesRepository
import dev.ymuratov.partnerkin_test.feature.confs.ui.model.ConferencesAction
import dev.ymuratov.partnerkin_test.feature.confs.ui.model.ConferencesEvent
import dev.ymuratov.partnerkin_test.feature.confs.ui.model.ConferencesState
import kotlinx.coroutines.flow.collectLatest
import java.time.YearMonth
import javax.inject.Inject

@HiltViewModel
class ConferencesViewModel @Inject constructor(
    private val conferencesRepository: ConferencesRepository
) : BaseViewModel<ConferencesState, ConferencesEvent, ConferencesAction>(ConferencesState()) {

    override fun obtainEvent(viewEvent: ConferencesEvent) {
        when (viewEvent) {
            ConferencesEvent.RefreshConferences -> refreshConferences()
            ConferencesEvent.OnNavigateToInfo -> sendAction(ConferencesAction.NavigateToInfo)
        }
    }

    init {
        viewModelScoped {
            conferencesRepository.conferencesFlow.collectLatest { conferences ->
                val groupedConferences = conferences.sortedByDescending {
                    it.startDate
                }.groupBy {
                    YearMonth.of(it.startDate.year, it.startDate.month)
                }
                updateViewState { copy(groupedConferences = groupedConferences) }
            }
        }
    }

    private fun refreshConferences() = viewModelScoped {
        conferencesRepository.refreshConferences()
    }
}