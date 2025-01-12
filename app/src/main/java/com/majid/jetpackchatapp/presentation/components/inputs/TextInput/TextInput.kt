package com.majid.jetpackchatapp.presentation.components.inputs.TextInput

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(placeHolder: String, label: String, onTextChange: (value: String) -> Unit) {

    val inputValue = remember { mutableStateOf<String>("") }

    TextField(
        value = inputValue.value,
        label = {
            Text(text = label)
        },
        onValueChange = { it ->
            inputValue.value = it
            onTextChange(it)
        },
        placeholder = {
            Text(text = placeHolder)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .border(width = 1.dp, color = Color.DarkGray, shape = RoundedCornerShape(6.dp))
    )
}