package com.twofasapp.API

import com.google.mlkit.vision.barcode.common.Barcode.Phone
import org.koin.core.logger.MESSAGE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


// ApiService.kt
interface ApiService {
    @POST("api1")   // replace with your real endpoint
    suspend fun getOtp(@Body request: Any): OtpResponse

    @POST("api2")   // replace with your real endpoint
    suspend fun verifyOtp(@Body request: VerifyRequest): VerifyResponse
}

