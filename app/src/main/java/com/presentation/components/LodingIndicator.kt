package com.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myrecipeapp.ui.theme.MyOrange

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 4.dp
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MyOrange,
            strokeWidth = strokeWidth
        )
    }
}
