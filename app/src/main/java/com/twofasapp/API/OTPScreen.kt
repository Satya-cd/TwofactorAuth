package com.twofasapp.API

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import com.twofasapp.ui.main.MainViewModel
import org.koin.androidx.compose.koinViewModel

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.twofasapp.ui.main.MainScreen
import org.koin.androidx.compose.koinViewModel
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
@Composable
fun OtpScreen(viewModel: OtpViewModel = koinViewModel()) {
    val otpState by viewModel.otpUiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (otpState) {
            is OtpUiState.Loading -> CircularProgressIndicator()

            is OtpUiState.WaitingForUserOtp -> {
                OtpInputBox { otp ->
                    if (otp.length == 6) {
                        viewModel.verifyOtp(otp)
                    }
                }
            }

            is OtpUiState.Verifying -> CircularProgressIndicator()

            is OtpUiState.Verified -> Text("OTP Verified ")

            is OtpUiState.Error -> Text((otpState as OtpUiState.Error).message)

            is OtpUiState.OtpReceived -> {
                Text("Received OTP: ${(otpState as OtpUiState.OtpReceived).otp}")
            }
        }
    }
}

@Composable
fun OtpInputBox(
    otpLength: Int = 6,
    onOtpComplete: (String) -> Unit
) {
    var otp by remember { mutableStateOf("") }

    // List of FocusRequesters for each box
    val focusRequesters = List(otpLength) { FocusRequester() }

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        repeat(otpLength) { index ->
            val char = otp.getOrNull(index)?.toString() ?: ""
            OutlinedTextField(
                value = char,
                onValueChange = { value ->
                    if (value.length <= 1 && value.all { it.isDigit() }){
                        val newOtp = StringBuilder(otp).apply {
                            if (index < otp.length) setCharAt(index, value.firstOrNull() ?: ' ')
                            else if (value.isNotEmpty()) append(value)
                        }.toString().trim()
                        otp = newOtp
                        // Move focus to next field
                        if (value.isNotEmpty() && index < otpLength - 1) {
                            focusRequesters[index + 1].requestFocus()
                        }

                        if (otp.length == otpLength) onOtpComplete(otp)
                    }
                },
                modifier = Modifier
                    .width(50.dp)
                    .focusRequester(focusRequesters[index]),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }

    // Auto focus on first box when composable enters composition
    LaunchedEffect(Unit) {
        focusRequesters.first().requestFocus()
    }
}

@Preview
@Composable
private fun Preview() {
    OtpScreen()
}