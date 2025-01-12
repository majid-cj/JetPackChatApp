package com.majid.jetpackchatapp.di

import com.majid.jetpackchatapp.data.repository.AuthenticationRepository
import com.majid.jetpackchatapp.presentation.ui.screens.SignupScreen.SignupViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val authenticationModule = module {
    single { AuthenticationRepository(get()) }

    viewModel { SignupViewModel(get()) }
}

val appModule = module { }