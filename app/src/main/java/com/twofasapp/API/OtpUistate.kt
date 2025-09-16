package com.twofasapp.API

sealed class OtpUiState {
    object Loading : OtpUiState()          // loader while fetching OTP
    data class OtpReceived(val otp: String) : OtpUiState()
    object WaitingForUserOtp : OtpUiState() // show input box
    object Verifying : OtpUiState()        // loader while verifying
    object Verified : OtpUiState()
    data class Error(val message: String) : OtpUiState()
}
