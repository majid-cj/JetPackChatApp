package com.majid.jetpackchatapp.presentation.components.AppToolBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolBar(navigation: NavController, title: String, isBack: Boolean) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = title, modifier = Modifier.padding(8.dp))
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.primary),
        navigationIcon = {
            if (isBack) {
                IconButton(onClick = { navigation.navigateUp() }) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack, "backIcon")
                }
            }
        }
    )
}