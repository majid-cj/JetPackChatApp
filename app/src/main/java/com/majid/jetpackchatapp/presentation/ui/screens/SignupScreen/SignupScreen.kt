package com.majid.jetpackchatapp.presentation.ui.screens.SignupScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.majid.jetpackchatapp.presentation.components.ScreenLoader.ScreenLoader
import com.majid.jetpackchatapp.presentation.components.containers.AppScreen.AppScreen
import com.majid.jetpackchatapp.presentation.components.inputs.TextInput.TextInput
import com.majid.jetpackchatapp.presentation.navigation.Screens
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    navigation: NavController,
    viewModel: SignupViewModel = koinViewModel()
) {

    val state = viewModel.state.collectAsState()

    if (state.value.loading) {
        ScreenLoader()
    } else {
        AppScreen(navigation = navigation, title = "Sign up", isBack = true, content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextInput(
                    placeHolder = "nickname",
                    label = "enter your nickname",
                    onTextChange = { viewModel.onEvent(SignUpUIEvent.OnUserName(it)) })
                Spacer(modifier = Modifier.height(22.dp))
                TextInput(
                    placeHolder = "email",
                    label = "enter your email",
                    onTextChange = { viewModel.onEvent(SignUpUIEvent.OnEmail(it)) })
                Spacer(modifier = Modifier.height(22.dp))
                TextInput(
                    placeHolder = "password",
                    label = "enter your password",
                    onTextChange = { viewModel.onEvent(SignUpUIEvent.OnPassword(it)) })
            }
        },
            secondaryButtonText = "back to login",
            onSecondaryClick = {
                navigation.navigateUp()
            },
            primaryButtonText = "sign up",
            onPrimaryClick = {
                viewModel.onEvent(SignUpUIEvent.OnSignUp(fun() {
                    navigation.navigate(Screens.Home.route)
                }))
            })
    }
}