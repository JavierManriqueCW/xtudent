package com.jmp.xtudent.factory

import androidx.compose.ui.unit.dp
import com.jmp.common.ui.model.ComponentState
import com.jmp.commons.utils.model.Exam
import com.jmp.xtudent.state.MainState

object MainViewModelFactory {

    fun makeExamList(): List<Exam> =
        listOf(
            makeExam(),
            makeExam(),
            makeExam()
        )

    private fun makeExam(): Exam = Exam()

    fun makeInitialMainState(): MainState =
        MainState(
            componentState = ComponentState.Initialising,
            loading = true,
            hasExams = false,
            homeButtonSize = 28.dp,
            creationButtonSize = 18.dp,
            pagesCount = 2,
            homeScreenIndex = 0,
            creationScreenIndex = 1
        )

    fun makeLoadedMainState(): MainState =
        MainState(
            componentState = ComponentState.Success,
            loading = false,
            hasExams = true,
            homeButtonSize = 28.dp,
            creationButtonSize = 18.dp,
            pagesCount = 2,
            homeScreenIndex = 0,
            creationScreenIndex = 1
        )
}
