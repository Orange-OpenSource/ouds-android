package com.orange.ouds.app.ui.components.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ButtonExample() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(modifier = Modifier.align(Alignment.CenterHorizontally), text = "// Put here the Button component example //", textAlign = TextAlign.Center)
    }
}