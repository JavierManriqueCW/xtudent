package com.jmp.examsfeature.presentation.creation.uiprovider

import com.jmp.examsfeature.presentation.R
import com.jmp.examsfeature.presentation.creation.state.ExamCreationState
import javax.inject.Inject

open class ExamCreationUiProvider @Inject constructor() {

    fun provide(): ExamCreationState = ExamCreationState(
        firstIndicator = R.string.exam_creation_first_indicator,
        firstIndication = R.string.exam_creation_first_indication,
        secondIndicator = R.string.exam_creation_second_indicator,
        secondIndication = R.string.exam_creation_second_indication,
        thirdIndicator = R.string.exam_creation_third_indicator,
        thirdIndication = R.string.exam_creation_third_indication,
        topStartFadingPoint = 0f,
        bottomStartFadingPoint = 0.8f
    )
}
