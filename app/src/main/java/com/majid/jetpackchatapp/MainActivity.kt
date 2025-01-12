package com.majid.jetpackchatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.majid.jetpackchatapp.presentation.navigation.AppNavigationHost
import com.majid.jetpackchatapp.ui.theme.JetPackChatAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackChatAppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val navigation = rememberNavController()
                    AppNavigationHost(navigation = navigation)
                }
            }
        }
    }
}
