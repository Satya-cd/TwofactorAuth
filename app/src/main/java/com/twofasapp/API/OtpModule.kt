package com.twofasapp.API

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val OtpModule = module {
//    viewModel { OtpViewModel(api = get<ApiService>()) }
    viewModel { OtpViewModel() }

}