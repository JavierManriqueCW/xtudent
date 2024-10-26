package com.jmp.storage.preferences.domain.repository

interface PreferencesRepository {

    fun shouldShowOnboarding(): Boolean

    fun saveShouldShowOnboarding(shouldShow: Boolean)
}
