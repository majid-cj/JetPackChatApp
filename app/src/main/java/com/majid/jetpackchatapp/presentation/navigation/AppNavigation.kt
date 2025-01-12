package com.majid.jetpackchatapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.majid.jetpackchatapp.presentation.ui.screens.HomeScreen.HomeScreen
import com.majid.jetpackchatapp.presentation.ui.screens.LoginScreen.LoginScreen
import com.majid.jetpackchatapp.presentation.ui.screens.SignupScreen.SignupScreen
import com.majid.jetpackchatapp.presentation.ui.screens.SplashScreen.SplashScreen

sealed class Screens(val route: String) {
    object Splash : Screens("splash_screen")
    object Login : Screens("login_screen")
    object Signup : Screens("signup_screen")
    object Home : Screens("home_screen")
    object Detail : Screens("detail_screen/{alpha}/{id}") {
        fun createRoute(alpha: String, id: Int) = "detail_screen/$alpha/$id"
        const val ALPHA_ARG = "alpha"
        const val ID_ARG = "id"
    }
}


@Composable
fun AppNavigationHost(navigation: NavHostController) {
    NavHost(navController = navigation, startDestination = Screens.Splash.route) {
        composable(route = Screens.Splash.route) {
            SplashScreen(navigation)
        }
        composable(route = Screens.Login.route) {
            LoginScreen(navigation)
        }
        composable(route = Screens.Signup.route) {
            SignupScreen(navigation)
        }
        composable(route = Screens.Home.route) {
            HomeScreen(navigation)
        }
        composable(
            route = Screens.Detail.route,
            arguments = listOf(
                navArgument(Screens.Detail.ALPHA_ARG) { type = NavType.StringType },
                navArgument(Screens.Detail.ID_ARG) { type = NavType.IntType })
        ) { it ->
            it.arguments?.getString(Screens.Detail.ALPHA_ARG) ?: ""
            it.arguments?.getInt(Screens.Detail.ID_ARG) ?: 0
        }
    }
}