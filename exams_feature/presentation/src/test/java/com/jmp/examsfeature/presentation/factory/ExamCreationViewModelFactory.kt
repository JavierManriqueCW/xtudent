package com.jmp.examsfeature.presentation.factory

import com.jmp.examsfeature.presentation.creation.state.ExamCreationState

class ExamCreationViewModelFactory : ViewModelFactory() {

    fun makeInitialExamCreationState(): ExamCreationState =
        ExamCreationState(
            firstIndication = 0,
            secondIndication = 0,
            thirdIndication = 0,
            firstIndicator = 0,
            secondIndicator = 0,
            thirdIndicator = 0,
            topStartFadingPoint = 0f,
            bottomStartFadingPoint = 0f,
            error = null,
            navigateBack = false
        )
}
