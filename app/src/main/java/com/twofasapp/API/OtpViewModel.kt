package com.twofasapp.API



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

//class OtpViewModel(private val api: ApiService) : ViewModel() {
//
//    private val _otpUiState = MutableStateFlow<OtpUiState>(OtpUiState.Loading)
//    val otpUiState: StateFlow<OtpUiState> = _otpUiState
//
//    init {
//        fetchOtp()
//    }
//
//    private fun fetchOtp() {
//        viewModelScope.launch {
//            try {
//                val response = api.getOtp(Any())
//                if (response.otp.length == 6) {
//                    verifyOtp(response.otp)
//                } else {
//                    _otpUiState.value = OtpUiState.WaitingForUserOtp
//                }
//            } catch (e: Exception) {
//                _otpUiState.value = OtpUiState.Error("Error fetching OTP")
//            }
//        }
//    }
//
//    fun verifyOtp(otp: String) {
//        viewModelScope.launch {
//            try {
//                _otpUiState.value = OtpUiState.Verifying
//                val response = api.verifyOtp(VerifyRequest(otp))
//                if (response.success) {
//                    _otpUiState.value = OtpUiState.Verified
//                } else {
//                    _otpUiState.value = OtpUiState.Error("Verification failed")
//                }
//            } catch (e: Exception) {
//                _otpUiState.value = OtpUiState.Error("Error verifying OTP")
//            }
//        }
//    }
//}

class OtpViewModel : ViewModel() {

    private val _otpUiState = MutableStateFlow<OtpUiState>(OtpUiState.Loading)
    val otpUiState: StateFlow<OtpUiState> = _otpUiState

    init {
        simulateOtpFlow()
    }

    private fun simulateOtpFlow() {
        viewModelScope.launch {
            // Show loader for 3 sec
            _otpUiState.value = OtpUiState.Loading
            delay(3000)

            // Show OTP input to user
            _otpUiState.value = OtpUiState.WaitingForUserOtp
        }
    }

    fun verifyOtp(otp: String) {
        viewModelScope.launch {
            // Show verifying loader
            _otpUiState.value = OtpUiState.Verifying

            // Simulate network delay of 15 sec
            delay(15000)

            // Hardcoded success
            _otpUiState.value = OtpUiState.Verified
        }
    }
}
