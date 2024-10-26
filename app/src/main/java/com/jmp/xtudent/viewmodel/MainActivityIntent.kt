package com.jmp.xtudent.viewmodel

sealed class MainActivityIntent {
    data object Load : MainActivityIntent()
    data class ChangePage(val page: Int) : MainActivityIntent()
}
