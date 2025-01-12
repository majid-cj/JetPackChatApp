package com.majid.jetpackchatapp.presentation.ui.screens.SignupScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.majid.jetpackchatapp.data.model.Authorization
import com.majid.jetpackchatapp.data.model.generateMD5
import com.majid.jetpackchatapp.data.repository.AuthenticationRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class SignUpUIEvent {
    data class OnUserName(val username: String) : SignUpUIEvent()
    data class OnEmail(val email: String) : SignUpUIEvent()
    data class OnPassword(val password: String) : SignUpUIEvent()
    data class OnSignUp(val navigate: () -> Unit) : SignUpUIEvent()
}

data class SignUpUIState(
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val error: String = "",
    val loading: Boolean = false,
)

class SignupViewModel(private val repository: AuthenticationRepository) : ViewModel() {

    private val _state = MutableStateFlow<SignUpUIState>(SignUpUIState())
    val state: StateFlow<SignUpUIState> = _state.asStateFlow()

    fun signUp(navigate: () -> Unit) {
        val payload = Authorization(
            email = _state.value.email,
            displayName = _state.value.username,
            password = _state.value.password,
            uniqueId = generateMD5("${_state.value.email}-${_state.value.password}")
        )
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(loading = true)
                val response = async {
                    repository.signUp(payload)
                }.await()
                _state.value = state.value.copy(loading = false)

                if (response.isSuccess) {
                    navigate()
                }
                if (response.isFailure) {
                    print("we are here response $response")
                }
            } catch (error: Exception) {
                _state.value = state.value.copy(loading = false, error = error.toString())
            }
        }
    }

    fun onEvent(event: SignUpUIEvent) {
        when (event) {
            is SignUpUIEvent.OnUserName -> {
                _state.value = state.value.copy(username = event.username)
            }

            is SignUpUIEvent.OnEmail -> {
                _state.value = state.value.copy(email = event.email)
            }

            is SignUpUIEvent.OnPassword -> {
                _state.value = state.value.copy(password = event.password)
            }

            is SignUpUIEvent.OnSignUp -> {
                signUp(event.navigate)
            }
        }
    }
}