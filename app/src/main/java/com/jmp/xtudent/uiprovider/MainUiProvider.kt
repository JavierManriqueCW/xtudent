package com.jmp.xtudent.uiprovider

import androidx.compose.ui.unit.dp
import com.jmp.common.ui.model.ComponentState
import com.jmp.xtudent.state.MainState
import javax.inject.Inject

open class MainUiProvider @Inject constructor() {

    open fun provide(): MainState =
        MainState(
            componentState = ComponentState.Initialising,
            homeButtonSize = 18.dp,
            creationButtonSize = 18.dp,
            homeScreenIndex = HOME_SCREEN_INDEX,
            creationScreenIndex = CREATION_SCREEN_INDEX,
            pagesCount = PAGES_COUNT
        )

    private companion object {
        private const val PAGES_COUNT = 2
        private const val HOME_SCREEN_INDEX = 0
        private const val CREATION_SCREEN_INDEX = 1
    }
}
