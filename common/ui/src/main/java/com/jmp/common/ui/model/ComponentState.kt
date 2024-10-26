package com.jmp.common.ui.model

import com.jmp.commons.utils.types.Failure

sealed class ComponentState  {

    data object Initialising: ComponentState()

    data object Success : ComponentState()

    data class Error(val error: Failure) : ComponentState()
}
