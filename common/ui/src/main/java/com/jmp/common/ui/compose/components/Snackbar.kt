package com.jmp.common.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jmp.common.ui.compose.clickable
import com.jmp.common.ui.theme.GeneralPadding
import com.jmp.common.ui.theme.HugeGeneralPadding
import com.jmp.common.ui.theme.Shapes

@Composable
fun Snackbar(snackbarData: SnackbarData) {
    Box(
        modifier = Modifier
            .padding(bottom = GeneralPadding)
            .safeDrawingPadding()
    ) {
        Text(
            modifier = Modifier
                .clickable { snackbarData.dismiss() }
                .background(
                    color = Color.DarkGray,
                    shape = Shapes.extraLarge
                )
                .padding(
                    horizontal = HugeGeneralPadding,
                    vertical = GeneralPadding
                ),
            text = snackbarData.visuals.message
        )
    }
}
