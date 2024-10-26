package com.jmp.common.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.jmp.common.ui.theme.IndicatorNumberBackgroundColor

@Composable
fun NumberIndicator(
    modifier: Modifier = Modifier,
    textContent: String,
    size: Dp,
    fontSize: TextUnit
) {
    Box(
        modifier = modifier
            .size(size)
            .background(
                color = IndicatorNumberBackgroundColor,
                shape = CircleShape
            )
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = textContent,
            textAlign = TextAlign.Center,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}
