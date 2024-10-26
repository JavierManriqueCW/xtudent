package com.jmp.examsfeature.presentation.list.viewmodel

sealed class ExamsIntent {

    data object Load : ExamsIntent()

    data class ChangeFilterText(val filter: String) : ExamsIntent()

    data object FetchQuotes : ExamsIntent()
}
