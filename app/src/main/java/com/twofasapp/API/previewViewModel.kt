package com.twofasapp.API


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// Fake ViewModel for preview purposes
class PreviewOtpViewModel(
    initialState: OtpUiState = OtpUiState.WaitingForUserOtp
) : ViewModel() {
    private val _otpUiState = MutableStateFlow(initialState)
    val otpUiState: StateFlow<OtpUiState> = _otpUiState

    // For preview, do nothing on verify
    fun verifyOtp(otp: String) {
        // no-op
    }

    // Optional: helper to change state for different previews
    fun setState(state: OtpUiState) {
        _otpUiState.value = state
    }
}