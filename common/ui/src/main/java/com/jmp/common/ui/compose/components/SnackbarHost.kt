package com.jmp.common.ui.compose.components

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable

@Composable
fun SnackbarHost(state: SnackbarHostState) {
    androidx.compose.material3.SnackbarHost(state) { Snackbar(it) }
}
