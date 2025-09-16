package com.twofasapp.API

import com.twofasapp.API.ApiClient
import com.twofasapp.API.ApiService
import org.koin.dsl.module

val ApiModule = module {
    single<ApiService> { ApiClient.apiService }
}
