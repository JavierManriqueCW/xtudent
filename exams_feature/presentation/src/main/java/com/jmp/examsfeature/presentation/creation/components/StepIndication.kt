package com.jmp.examsfeature.presentation.creation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jmp.common.ui.compose.components.NumberIndicator
import com.jmp.common.ui.theme.MediumBodyTextSize
import com.jmp.common.ui.theme.Shapes
import com.jmp.common.ui.theme.TranslucentBoxColor

@Composable
fun StepIndication(
    modifier: Modifier = Modifier,
    step: String,
    content: String
) {
    Row(
        modifier = modifier
            .background(
                color = TranslucentBoxColor,
                shape = Shapes.medium
            )
    ) {
        NumberIndicator(
            modifier = modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterVertically),
            textContent = step,
            size = 48.dp,
            fontSize = 24.sp
        )

        Text(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 32.dp
                )
                .align(Alignment.CenterVertically),
            text = content,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Light,
            fontSize = MediumBodyTextSize,
            color = Color.White
        )
    }
}
