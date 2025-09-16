package com.twofasapp.API

class OtpResponse(val otp: String)
data class VerifyRequest(val otp: String)
data class VerifyResponse(val success: Boolean)