package com.majid.jetpackchatapp.presentation.ui.screens.LoginScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.majid.jetpackchatapp.presentation.components.containers.AppScreen.AppScreen
import com.majid.jetpackchatapp.presentation.components.inputs.TextInput.TextInput
import com.majid.jetpackchatapp.presentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navigation: NavController) {

    AppScreen(navigation = navigation, title = "Login", isBack = false, content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextInput(placeHolder = "email", label = "enter your email", onTextChange = { it ->
                print("this is your email")
            })
            Spacer(modifier = Modifier.height(22.dp))
            TextInput(
                placeHolder = "password",
                label = "enter your password",
                onTextChange = { it ->
                    print("this is your email")
                })
        }
    },
        secondaryButtonText = "create account",
        onSecondaryClick = {
            navigation.navigate(Screens.Signup.route)
        },
        primaryButtonText = "Login",
        onPrimaryClick = {
            navigation.navigate(Screens.Home.route)
        })
}