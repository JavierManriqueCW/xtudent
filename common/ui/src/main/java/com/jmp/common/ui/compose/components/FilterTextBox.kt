package com.jmp.common.ui.compose.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.jmp.common.ui.compose.components.state.TextBoxState
import com.jmp.common.ui.theme.SmallBodyTextSize
import com.jmp.common.ui.theme.TextBoxColors
import com.jmp.common.ui.theme.TextBoxColors.defaultTextSelectionColors

@Composable
fun FilterTextBox(
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    maxCharacters: Int = 300,
    textBoxState: TextBoxState,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    onImeButtonTapped: (() -> Unit)? = null,
    onTextChanged: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    CompositionLocalProvider(LocalTextSelectionColors provides defaultTextSelectionColors()) {
        TextField(
            modifier = modifier.fillMaxWidth(),
            value = textBoxState.value,
            onValueChange = {
                if (it.count() < maxCharacters) {
                    onTextChanged(it)
                }
            },
            keyboardActions = KeyboardActions {
                onImeButtonTapped?.invoke() ?: focusManager.moveFocus(FocusDirection.Down)
            },
            colors = TextBoxColors.defaultColors(),
            interactionSource = interactionSource,
            maxLines = maxLines,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                autoCorrectEnabled = false,
                keyboardType = KeyboardType.Password
            ),
            placeholder = {
                PlaceholderText(
                    text = stringResource(textBoxState.placeholder),
                    color = Color.Gray
                )
            }
        )
    }
}

@Composable
private fun PlaceholderText(
    modifier: Modifier = Modifier,
    text: String,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = SmallBodyTextSize,
        fontWeight = fontWeight,
        color = color
    )
}
