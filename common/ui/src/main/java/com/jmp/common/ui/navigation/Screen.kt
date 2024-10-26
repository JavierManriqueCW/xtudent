package com.jmp.common.ui.navigation

import com.jmp.common.ui.navigation.ScreenNavigationParameters.EXAM_ID_KEY

sealed class Screen(val route: String) {

    data object Onboarding : Screen("onboarding")

    data object Main : Screen("main")

    data object ExamDetail : Screen("examDetail/{$EXAM_ID_KEY}")
}

object ScreenNavigationParameters{
    const val EXAM_ID_KEY = "id"
}
