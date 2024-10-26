package com.jmp.xtudent.components

import androidx.compose.foundation.background
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jmp.common.ui.theme.ApplicationTheme
import com.jmp.common.ui.theme.BackgroundColor

@Composable
fun ApplicationSurface(content: @Composable () -> Unit) {
    ApplicationTheme {
        Surface(
            modifier = Modifier.background(
                color = BackgroundColor
            ),
            color = Color.Transparent,
            content = content
        )
    }
}
