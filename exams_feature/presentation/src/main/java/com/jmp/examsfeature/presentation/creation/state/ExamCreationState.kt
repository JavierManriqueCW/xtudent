package com.jmp.examsfeature.presentation.creation.state

data class ExamCreationState(
    val firstIndicator: Int,
    val firstIndication: Int,
    val secondIndicator: Int,
    val secondIndication: Int,
    val thirdIndicator: Int,
    val thirdIndication: Int,
    val topStartFadingPoint: Float,
    val bottomStartFadingPoint: Float,
    val error: Int? = null,
    val navigateBack: Boolean = false
)
