package dev.ymuratov.partnerkin_test.feature.conf_info.ui.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ymuratov.partnerkin_test.core.ui.viewmodel.BaseViewModel
import dev.ymuratov.partnerkin_test.feature.conf_info.domain.repository.ConferenceInfoRepository
import dev.ymuratov.partnerkin_test.feature.conf_info.ui.model.ConferenceInfoAction
import dev.ymuratov.partnerkin_test.feature.conf_info.ui.model.ConferenceInfoEvent
import dev.ymuratov.partnerkin_test.feature.conf_info.ui.model.ConferenceInfoState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ConferenceInfoViewModel @Inject constructor(
    private val conferenceInfoRepository: ConferenceInfoRepository
) : BaseViewModel<ConferenceInfoState, ConferenceInfoEvent, ConferenceInfoAction>(ConferenceInfoState()) {

    override fun obtainEvent(viewEvent: ConferenceInfoEvent) {
        when (viewEvent) {
            ConferenceInfoEvent.OnNavigateBack -> sendAction(ConferenceInfoAction.NavigateBack)
            ConferenceInfoEvent.GetConferenceInfo -> getConferenceInfo()
        }
    }

    private fun getConferenceInfo() = viewModelScoped {
        conferenceInfoRepository.getConferenceInfo().onSuccess { info ->
            updateViewState { copy(conferenceInfo = info) }
        }.onFailure { exception ->
            Timber.d(exception)
        }
    }
}