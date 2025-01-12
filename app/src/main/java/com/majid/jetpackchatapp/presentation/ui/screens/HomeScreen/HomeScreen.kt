package com.majid.jetpackchatapp.presentation.ui.screens.HomeScreen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.majid.jetpackchatapp.presentation.components.containers.AppScreen.AppScreen
import com.majid.jetpackchatapp.presentation.components.inputs.TextInput.TextInput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navigation: NavController) {

    AppScreen(navigation = navigation, title = "Home", isBack = false, content = {
        TextInput(
            placeHolder = "home screen",
            label = "home",
            onTextChange = { it -> print("we are here $it") })
    })
}