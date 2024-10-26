package com.jmp.common.ui.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jmp.common.ui.theme.LoadingIndicatorBackgroundColor

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    error: String,
    onRetry: () -> Unit
) {

    Surface(
        modifier = modifier,
        color = LoadingIndicatorBackgroundColor,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = error
            )
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White),
                onClick = onRetry
            ) {
                Icon(
                    imageVector = Icons.Outlined.Refresh,
                    contentDescription = null
                )
            }
        }
    }
}
