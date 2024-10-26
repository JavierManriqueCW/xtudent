package com.jmp.common.ui.theme

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object TextBoxColors {

    @Composable
    fun defaultColors() =
        TextFieldDefaults.colors(
            cursorColor = Color.LightGray,
            focusedTextColor = Color.LightGray,
            selectionColors = defaultTextSelectionColors(),
            unfocusedTextColor = Color.Gray,
            disabledContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            disabledIndicatorColor = Color.Gray,
            unfocusedIndicatorColor = Color.Gray,
            focusedIndicatorColor = Color.LightGray,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.LightGray,
            errorContainerColor = Color.Transparent,
            errorIndicatorColor = Color.Red,
            errorLeadingIconColor = Color.Red
        )

    fun defaultTextSelectionColors(): TextSelectionColors =
        TextSelectionColors(
            handleColor = Color.White,
            backgroundColor = Color.LightGray.copy(alpha = 0.5f)
        )
}
