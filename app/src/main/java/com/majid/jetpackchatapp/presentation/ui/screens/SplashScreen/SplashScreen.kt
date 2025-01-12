package com.majid.jetpackchatapp.presentation.ui.screens.SplashScreen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.majid.jetpackchatapp.presentation.components.containers.AppScreen.AppScreen
import com.majid.jetpackchatapp.presentation.navigation.Screens
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen(navigation: NavController) {

    LaunchedEffect(Unit) {
        delay(3000)
        navigation.navigate(Screens.Login.route)
    }

    AppScreen(navigation = navigation, title = "", isBack = false, content = {})
}