package com.majid.jetpackchatapp.data.repository

import com.majid.jetpackchatapp.data.model.Authentication
import com.majid.jetpackchatapp.data.model.Authorization
import com.majid.jetpackchatapp.di.APIManager

class AuthenticationRepository(private val apiManager: APIManager) {

    suspend fun signUp(data: Authorization): Result<Authentication> {
        return apiManager.POST<Authorization, Authentication>("/sign-up", data)
    }

    suspend fun signIn(data: Authorization): Result<Authentication> {
        return apiManager.POST<Authorization, Authentication>("/sign-in", data)
    }
}