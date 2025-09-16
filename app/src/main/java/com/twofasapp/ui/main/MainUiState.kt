package com.twofasapp.ui.main

import com.twofasapp.common.domain.SelectedTheme

data class MainUiState(
    val selectedTheme: SelectedTheme? = null,
    val startDestination: StartDestination? = null,
    val browserExtRequests: List<BrowserExtRequest> = emptyList(),
    val addServiceAdvancedExpanded: Boolean = false,
    val events: List<MainUiEvent> = emptyList(),
    val showOtpScreen: Boolean = false // adding here otp screen

) {
    enum class StartDestination {
        Home, Onboarding
    }
}


sealed interface MainUiEvent
